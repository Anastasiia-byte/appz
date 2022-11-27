import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private serverUrl = "http://localhost:8080/api/user"

  public constructor(private httpClient: HttpClient) {
  }

  public getAllConsultants(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.serverUrl}/consultants`);
  }

  public deleteUser(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.serverUrl}/${id}`);
  }

  public updateUser(user: User): Observable<User> {
    return this.httpClient.put<User>(`${this.serverUrl}`, user);
  }

  public createConsultant(user: User): Observable<User> {
    return this.httpClient.post<User>(`${this.serverUrl}/consultant`, user);
  }
}
