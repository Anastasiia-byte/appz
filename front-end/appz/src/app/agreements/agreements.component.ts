import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {AgreementService} from "../services/agreement.service";
import {Agreement} from "../models/agreement";

@Component({
  selector: 'app-dwellings',
  templateUrl: './agreements.component.html',
  styleUrls: ['./agreements.component.css']
})
export class AgreementsComponent implements OnInit {
  public loadingIncomplete: boolean;
  public loadingCompleted: boolean;

  public incompleteAgreements: Agreement[];
  public completedAgreements: Agreement[];

  constructor(private agreementService: AgreementService, private router: Router) {
  }

  ngOnInit(): void {
    this.loadingIncomplete = true;
    this.loadingCompleted = true;

    this.agreementService.getAllAgreementsByComplete(false).subscribe((agreements) => {
      this.incompleteAgreements = agreements;
      this.loadingIncomplete = false;
    });

    this.agreementService.getAllAgreementsByComplete(true).subscribe((agreements) => {
      this.completedAgreements = agreements;
      this.loadingCompleted = false;
    });
  }

  acceptAgreement(event: MouseEvent, id: number) {
    event.stopPropagation();

    this.agreementService.updateAgreement(id).subscribe((agreement) => {
      alert("Success");

      this.incompleteAgreements.forEach((value, index) => {
        if (value.id == id) {
          this.incompleteAgreements.splice(index, 1);
        }
      });

      this.completedAgreements.push(agreement);
    })
  }

  declineAgreement(event: MouseEvent, id: number) {
    event.stopPropagation();

    this.agreementService.deleteAgreement(id).subscribe(() => {
      alert("Success");

      this.incompleteAgreements.forEach((value, index) => {
        if (value.id == id) {
          this.incompleteAgreements.splice(index, 1);
        }
      });
    })
  }
}
