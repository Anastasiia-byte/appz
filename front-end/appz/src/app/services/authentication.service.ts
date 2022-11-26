import {Injectable} from "@angular/core";
import {BehaviorSubject, map, Observable} from "rxjs";
import {User} from "../models/user";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private readonly serverUrl = 'http://localhost:8080/api'

  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  public constructor(private router: Router,
                     private httpClient: HttpClient) {
  }

  public get loggedInValue(): boolean {
    return this.loggedIn.value;
  }

  public setLoggedInValue(isLoggedIn: boolean)
  {
    this.loggedIn.next(isLoggedIn);
  }

  public login(email: string, password: string): Observable<void> {
    return this.httpClient.post<void>(`${this.serverUrl}/login`, {email, password});
  }

  public register(user: any): Observable<void> {
    return this.httpClient.post<void>(`${this.serverUrl}/register`, user);
  }

  public logout(): void {
    this.loggedIn.next(false)
    this.router.navigate(['/login']);
  }

}
