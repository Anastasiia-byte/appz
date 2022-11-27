import {Component, OnInit} from "@angular/core";
import {ChatService} from "../services/chat.service";
import {Router} from "@angular/router";
import {DwellingService} from "../services/dwelling.service";
import {Dwelling} from "../models/dwelling";
import {AgreementService} from "../services/agreement.service";

@Component({
  selector: 'app-dwellings',
  templateUrl: './dwellings.component.html',
  styleUrls: ['./dwellings.component.css']
})
export class DwellingsComponent implements OnInit {
  public loading: boolean;
  public dwellings: Dwelling[];

  constructor(private dwellingService: DwellingService,
              private agreementService: AgreementService,
              private chatService: ChatService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.loading = true;
    this.dwellingService.getAllDwellings().subscribe((dwellings) => {
      this.dwellings = dwellings;
      this.loading = false;
    });
  }

  public createChat(event: Event) {
    this.chatService.createChat().subscribe(response => {
      console.error(response);
      this.router.navigate(['/chat', response.chat.id], {queryParams: {receiverId: response.consultantId}});
    });
  }

  createAgreement(dwellingId: number) {
    const email = localStorage.getItem("email");
    this.agreementService.createAgreement(email, dwellingId).subscribe(() => {
      alert("Success");
    });
  }
}
