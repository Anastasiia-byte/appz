import {
  AfterContentInit,
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ElementRef,
  Input, OnDestroy,
  OnInit,
  ViewChild
} from "@angular/core";
import {ChatService} from "../services/chat.service";
import {WebSocketService} from "../services/web-socket.service";
import {Chat} from "../models/chat";
import {Message} from "../models/message";
import {ActivatedRoute} from "@angular/router";
import {Subject, takeUntil} from "rxjs";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {
  public chatId: number;
  public receiverId: number;
  public senderId: number;
  public loading: boolean;

  public chat: Chat;

  public message: string;

  public scrollTop: number;

  @ViewChild("chatContainer")
  private chatContainer: ElementRef;

  private destroy$ = new Subject();

  constructor(private chatService: ChatService, private wsService: WebSocketService, private route: ActivatedRoute, private changeDetectorRef: ChangeDetectorRef) {
  }

  public ngOnInit(): void {
    this.loading = true;
    this.chatId = Number(this.route.snapshot.paramMap.get('id'));
    this.senderId = Number(localStorage.getItem('userId'));

    this.route.queryParams.subscribe(params => {
      this.receiverId = params.receiverId;
    });

    this.chatService.getChat(this.chatId)
      .pipe(takeUntil(this.destroy$))
      .subscribe((chat: Chat) => {
      this.chat = chat;

      this.wsService.createWebSocketConnection(this.chat.id);
      this.loading = false;
    });

    this.wsService.messages
      .pipe(takeUntil(this.destroy$))
      .subscribe((message: Message) => {
      this.chat.messages = [...this.chat.messages, message];

      setTimeout(() => {
        this.scrollChatToBottom();
      }, 10);
    });

    setTimeout(() => {
      this.scrollChatToBottom();
    }, 20);
  }

  private scrollChatToBottom(): void {
    this.scrollTop = this.chatContainer.nativeElement.scrollHeight;
  }

  public sendMessage(): void {
    const message = {
      senderId: this.senderId,
      receiverId: this.receiverId,
      text: this.message
    } as Message;

    this.message = "";

    this.wsService.sendMessage(message, this.chat.id);
  }

  public ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
