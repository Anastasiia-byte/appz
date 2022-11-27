import {Injectable} from "@angular/core";
import {CanActivate, Router, UrlTree} from "@angular/router";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  private readonly button: boolean;

  constructor(private router: Router) {
    this.button = false;
  }

  public canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.checkLogin();
  }

  private checkLogin(): boolean {
    if (localStorage.getItem('token')) {
      return true;
    } else {
      this.router.navigate(['/login']).then();
      return false;
    }
  }
}
