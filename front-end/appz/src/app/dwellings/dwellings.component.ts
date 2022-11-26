import {Component} from "@angular/core";
import {ChatService} from "../services/chat.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dwellings',
  templateUrl: './dwellings.component.html',
  styleUrls: ['./dwellings.component.css']
})
export class DwellingsComponent {

  constructor(private chatService: ChatService, private router: Router) {
  }

  public createChat(event: Event) {
    this.chatService.createChat().subscribe(response => {
      console.error(response);
      this.router.navigate(['/chat', response.id], {});
    });
  }
}
