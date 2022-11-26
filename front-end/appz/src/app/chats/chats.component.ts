import {Component, OnInit} from "@angular/core";
import {ChatService} from "../services/chat.service";
import {Chat} from "../models/chat";

@Component({
  selector: 'app-chats',
  templateUrl: './chats.component.html',
  styleUrls: ['./chats.component.css']
})
export class ChatsComponent implements OnInit {
  private userId = 1;

  public chats: Chat[];

  constructor(private chatService: ChatService) {
  }

  ngOnInit(): void {
    this.chatService.getAllForUser(this.userId).subscribe(chats => {
      this.chats = chats;
    });
  }

}
