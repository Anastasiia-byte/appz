import {Component, OnInit} from "@angular/core";
import {User} from "../models/user";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './consultants.component.html',
  styleUrls: ['./consultants.component.css']
})
export class ConsultantsComponent implements OnInit{
  public consultants: User[];

  public constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getAllConsultants().subscribe(consultants =>{
      this.consultants = consultants;
    })
  }

  public delete(id: number) {
    this.userService.deleteUser(id).subscribe(() =>{
      this.consultants = this.consultants.filter(consultant => consultant.id != id);
    })
  }

}
