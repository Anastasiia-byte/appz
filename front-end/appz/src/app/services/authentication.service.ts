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

  private userSubject: BehaviorSubject<User>;
  private user: Observable<User>;

  public constructor(private router: Router,
                     private httpClient: HttpClient) {
    // this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem("user")));
    // this.user = this.userSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.userSubject.value;
  }

  public login(email: string, password: string): Observable<void> {
    return this.httpClient.post<void>(`${this.serverUrl}/login`, {email, password});
  }

  public logout(): void {
    localStorage.removeItem('user');
    this.userSubject.next(new User());
    this.router.navigate(['/login']);
  }

}
