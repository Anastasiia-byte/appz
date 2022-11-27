import {Component, OnInit} from "@angular/core";
import {User} from "../models/user";
import {UserService} from "../services/user.service";
import {MatDialog} from "@angular/material/dialog";
import {NewConsultantComponent} from "./new-consultant/new-consultant.component";
import {SnackbarService} from "../services/snack-bar.service";

@Component({
  selector: 'app-register',
  templateUrl: './consultants.component.html',
  styleUrls: ['./consultants.component.css']
})
export class ConsultantsComponent implements OnInit{
  public loading: boolean;
  public consultants: User[];

  public constructor(private userService: UserService,
                     private matDialog: MatDialog) {
  }

  ngOnInit(): void {
    this.loading = true;
    this.userService.getAllConsultants().subscribe(consultants =>{
      this.consultants = consultants;
      this.loading = false;
    });
  }

  public delete(id: number): void {
    this.userService.deleteUser(id).subscribe(() => {
      this.consultants = this.consultants.filter(consultant => consultant.id != id);
    })
  }

  public edit(consultant: User): void {
    this.matDialog
      .open(NewConsultantComponent, {
        panelClass: 'custom-modal-box',
        data: {
          consultant: consultant,
          consultants: this.consultants
        }
      })
      .afterClosed()
      .subscribe({
        next: (result: User) => {
          if (result)
          {
            const consultant = this.consultants.find(consultant => consultant.id === result.id);
            if (consultant)
            {
              const index = this.consultants.indexOf(consultant);
              this.consultants[index] = result;
            }
          }
        }
      });
  }

  public add(): void {
    this.matDialog
      .open(NewConsultantComponent, {
        panelClass: 'custom-modal-box',
        data: {
          adding: true,
          consultants: this.consultants
        }
      })
      .afterClosed()
      .subscribe({
        next: (result) => {
          if (result)
          {
            this.consultants.push(result);
          }
        }
      });
  }
}
