import {Component, Input, OnInit} from "@angular/core";
import {ChatService} from "../services/chat.service";
import {WebSocketService} from "../services/web-socket.service";
import {Chat} from "../models/chat";
import {Message} from "../models/message";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  public chatId: number;

  public chat: Chat;

  public message: string;

  public myId = 1;
  public consultantId = 2;

  constructor(private chatService: ChatService, private wsService: WebSocketService, private route: ActivatedRoute) {
  }

  public ngOnInit(): void {
    this.chatId = Number(this.route.snapshot.paramMap.get('id'));

    this.chatService.getChat(this.chatId).subscribe((chat: Chat) => {
      console.warn(chat);
      this.chat = chat;

      this.wsService.createWebSocketConnection(this.chat.id);
    });

    this.wsService.messages.subscribe((message: Message) => {
      this.chat.messages.push(message);
      console.error(this.chat.messages);
    });
  }

  public sendMessage(): void {
    const message = {
      senderId: 1,
      receiverId: 2,
      text: this.message
    } as Message;

    this.message = "";

    this.wsService.sendMessage(message, this.chat.id);
  }

  public sendMessage2(): void {
    const message = {
      chatId: this.chat.id,
      senderId: 2,
      receiverId: 1,
      text: this.message
    } as Message;

    this.message = "";

    this.wsService.sendMessage(message, this.chat.id);
  }
}
