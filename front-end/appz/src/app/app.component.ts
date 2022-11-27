import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "./services/authentication.service";
import {WebSocketService} from "./services/web-socket.service";
import {SnackbarService} from "./services/snack-bar.service";
import {Notification} from "./models/notification";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'appz';

  public isConnectToChat = false;

  public constructor(public authenticationService: AuthenticationService, public ws: WebSocketService, private snackBatService: SnackbarService) {
  }

  ngOnInit(): void {
    const isLoggedIn = localStorage.getItem("isLoggedIn");
    const isConsultant = localStorage.getItem("isConsultant");
    const userId = localStorage.getItem("userId");

    if (isLoggedIn) {
      this.authenticationService.setLoggedInValue(true);
    }

    if (isConsultant) {
      this.authenticationService.setIsConsultantValue(isConsultant == "true");
    }

    this.ws.createNotificationsWebSocketConnection(Number(userId));

    this.ws.connectionSubject.subscribe((isConnect) => this.isConnectToChat = isConnect);
    this.ws.notifications.subscribe((notification: Notification) => {
      if (!this.isConnectToChat) {
        this.snackBatService.info('You receive new message from ' + notification.senderFullName);
      }
    });
  }

  logout() {
    this.authenticationService.logout();
  }
}
