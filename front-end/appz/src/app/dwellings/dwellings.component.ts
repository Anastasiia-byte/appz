import {Component, OnInit} from "@angular/core";
import {ChatService} from "../services/chat.service";
import {Router} from "@angular/router";
import {DwellingService} from "../services/dwelling.service";
import {Dwelling} from "../models/dwelling";
import {AgreementService} from "../services/agreement.service";
import {AuthenticationService} from "../services/authentication.service";
import {SnackbarService} from "../services/snack-bar.service";

@Component({
  selector: 'app-dwellings',
  templateUrl: './dwellings.component.html',
  styleUrls: ['./dwellings.component.css']
})
export class DwellingsComponent implements OnInit {
  public loading: boolean;
  public match: boolean;
  public dwellings: Dwelling[];
  public filter = true;

  constructor(private dwellingService: DwellingService,
              private agreementService: AgreementService,
              private chatService: ChatService,
              public authService: AuthenticationService,
              private router: Router,
              private snackBarService: SnackbarService) {
  }

  ngOnInit(): void {
    this.match = true;
    this.loading = true;
    this.getAllDwellings(true);
  }

  private getAllDwellings(filter: boolean): void {
    this.dwellingService.getAllDwellings(filter).subscribe((dwellings) => {
      this.dwellings = dwellings.dwellings;
      this.match = dwellings.match;

      this.filter = !(filter && !dwellings.match);


      this.loading = false;
    });
  }

  public getFiltered(filter: boolean): void {
    this.getAllDwellings(filter);

    setTimeout(() => {
      if (!this.filter) {
        this.snackBarService.info("We can't find dwellings by your requirements");
      }
    }, 100);
  }

  public createChat() {
    this.chatService.createChat().subscribe(response => {
      console.error(response);
      this.router.navigate(['/chat', response.chat.id], {queryParams: {receiverId: response.consultantId}});
    });
  }

  createAgreement(dwellingId: number) {
    const email = localStorage.getItem("email");
    this.agreementService.createAgreement(email, dwellingId).subscribe(() => {
      this.snackBarService.success("Success");
    });
  }
}
