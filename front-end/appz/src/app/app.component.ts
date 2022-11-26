import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "./services/authentication.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'appz';

  public constructor(public authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    const isLoggedIn = localStorage.getItem("isLoggedIn");

    if (isLoggedIn) {
      this.authenticationService.setLoggedInValue(true);
    }
  }

  logout() {
    this.authenticationService.logout();
  }
}
