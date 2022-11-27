import {Injectable} from "@angular/core";
import {
  Subject
} from "rxjs";

import * as Stomp from "stompjs";
import SockJS from "sockjs-client";
import {Message} from "../models/message";
import {Client, Subscription} from "stompjs";

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private serverUrl = 'http://127.0.0.1:8080/chat'
  private stompClient: Client | undefined;

  public messages = new Subject<Message>();

  public createWebSocketConnection(id: number): void {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient?.subscribe("/queue/" + id, (message) => {
        if(message.body) {
          that.messages.next(JSON.parse(message.body));
        }
      });
    });
  }

  public sendMessage(message: Message, chatId: number): void {
    this.stompClient?.send("/chat/" + chatId, {}, JSON.stringify(message));
  }
}
