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
    const isConsultant = localStorage.getItem("isConsultant");

    if (isLoggedIn) {
      this.authenticationService.setLoggedInValue(true);
    }

    if (isConsultant) {
      this.authenticationService.setIsConsultantValue(isConsultant == "true");
    }
  }

  logout() {
    this.authenticationService.logout();
  }
}
