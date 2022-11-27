import {Injectable} from "@angular/core";
import {BehaviorSubject, map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Response} from "../models/response";
import {RegisterInfo} from "../models/register-info";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private readonly serverUrl = 'http://localhost:8080/api'

  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private isConsultant: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  public constructor(private router: Router,
                     private httpClient: HttpClient) {
  }

  public get loggedInValue(): boolean {
    return this.loggedIn.value;
  }

  public get isConsultantValue(): boolean {
    return this.isConsultant.value;
  }

  public setLoggedInValue(isLoggedIn: boolean)
  {
    localStorage.setItem("isLoggedIn", String(isLoggedIn));
    this.loggedIn.next(isLoggedIn);
  }

  public setIsConsultantValue(isConsultant: boolean)
  {
    localStorage.setItem("isConsultant", String(isConsultant));
    this.isConsultant.next(isConsultant);
  }

  public login(email: string, password: string): Observable<Response<string>> {
    return this.httpClient.post<Response<string>>(`${this.serverUrl}/login`, {email, password});
  }

  public register(user: any): Observable<void> {
    return this.httpClient.post<void>(`${this.serverUrl}/register`, user);
  }

  public fillUserInfo(registerInfo: RegisterInfo): Observable<void> {
    return this.httpClient.put<void>(`${this.serverUrl}/register/info`, registerInfo);
  }

  public logout(): void {
    localStorage.clear();
    this.loggedIn.next(false)
    this.router.navigate(['/login']);
  }

}
