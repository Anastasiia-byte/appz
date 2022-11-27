import {Component, OnInit} from "@angular/core";
import {ChatService} from "../services/chat.service";
import {ChatUser} from "../models/chat-user";

@Component({
  selector: 'app-chats',
  templateUrl: './chats.component.html',
  styleUrls: ['./chats.component.css']
})
export class ChatsComponent implements OnInit {
  public userId: number;
  public loading: boolean;

  public chats: ChatUser[];

  constructor(private chatService: ChatService) {
  }

  ngOnInit(): void {
    this.loading = true;
    this.userId = Number(localStorage.getItem('userId'));

    this.chatService.getAllForUser(this.userId).subscribe(chats => {
      this.chats = chats;
      this.loading = false;
    });
  }

}
