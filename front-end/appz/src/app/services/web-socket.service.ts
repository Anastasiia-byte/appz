import {Injectable} from "@angular/core";
import {
  BehaviorSubject,
  Subject
} from "rxjs";

import * as Stomp from "stompjs";
import SockJS from "sockjs-client";
import {Message} from "../models/message";
import {Client} from "stompjs";
import {Notification} from "../models/notification";

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private serverUrlChat = 'http://127.0.0.1:8080/chat'
  private serverUrlNotification = 'http://127.0.0.1:8080/notification'
  private stompClient: Client | undefined;

  private chatWs: WebSocket;

  public messages = new Subject<Message>();
  public notifications = new Subject<Notification>();
  public connectionSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  public get connectionSubjectValue(): boolean {
    return this.connectionSubject.value;
  }

  public setConnectionSubjectValue(isConnect: boolean)
  {
    this.connectionSubject.next(isConnect);
  }

  public createWebSocketConnection(chatId: number): void {
    this.chatWs = new SockJS(this.serverUrlChat);
    this.stompClient = Stomp.over(this.chatWs);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.setConnectionSubjectValue(true);
      that.stompClient?.subscribe("/queue/" + chatId, (message) => {
        if(message.body) {
          that.messages.next(JSON.parse(message.body));
        }
      });
    }, function (message) {
      console.warn(message);
      if (typeof(message) == 'string') {
        if (message.startsWith("Whoops! Lost connection")) {
          that.setConnectionSubjectValue(false);
        }
      }
    });
  }

  public createNotificationsWebSocketConnection(userId: number): void {
    let ws = new SockJS(this.serverUrlNotification);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient?.subscribe("/queue/notification/" + userId, (message) => {
        if(message.body) {
          that.notifications.next(JSON.parse(message.body));
        }
      });
    });
  }

  public closeChatWS(): void {
    this.chatWs.close();
  }

  public sendMessage(message: Message, chatId: number): void {
    this.stompClient?.send("/chat/" + chatId, {}, JSON.stringify(message));
  }
}
