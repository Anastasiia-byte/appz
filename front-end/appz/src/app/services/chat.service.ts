import {Injectable} from "@angular/core";
import {Message} from "../models/message";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Chat} from "../models/chat";

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  private readonly chatEndpointUrl = "http://localhost:8080/api/chat";

  public messages: Message[] = [];

  constructor(private http: HttpClient) {
  }

  public createChat(): Observable<Chat> {
    return this.http.post<Chat>(this.chatEndpointUrl, {});
  }

  public getChat(id: number): Observable<Chat> {
    return this.http.get<Chat>(`${this.chatEndpointUrl}/${id}`);
  }

  public getAllForUser(userId: number): Observable<Chat[]> {
    return this.http.get<Chat[]>(`${this.chatEndpointUrl}/all/${userId}`);
  }
}
