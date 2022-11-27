import {Injectable} from "@angular/core";
import {Message} from "../models/message";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Chat} from "../models/chat";
import {ChatConsultant} from "../models/chat-consultant";
import {ChatUser} from "../models/chat-user";

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  private readonly chatEndpointUrl = "http://localhost:8080/api/chat";

  public messages: Message[] = [];

  constructor(private http: HttpClient) {
  }

  public createChat(): Observable<ChatConsultant> {
    return this.http.post<ChatConsultant>(this.chatEndpointUrl, {});
  }

  public getChat(id: number): Observable<Chat> {
    return this.http.get<Chat>(`${this.chatEndpointUrl}/${id}`);
  }

  public getAllForUser(userId: number): Observable<ChatUser[]> {
    return this.http.get<ChatUser[]>(`${this.chatEndpointUrl}/all/${userId}`);
  }
}
