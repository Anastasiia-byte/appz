import {NgModule} from "@angular/core";
import {RegisterComponent} from "./register.component";
import {RegisterRoutingModule} from "./register-routing.module";
import {CommonModule} from "@angular/common";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatIconModule} from "@angular/material/icon";
import {MatNativeDateModule} from "@angular/material/core";
import {MatButtonModule} from "@angular/material/button";

@NgModule({
  declarations: [RegisterComponent],
  imports: [RegisterRoutingModule, CommonModule, MatCardModule, MatFormFieldModule, MatInputModule, MatNativeDateModule, ReactiveFormsModule, MatDatepickerModule, MatIconModule, MatButtonModule],
  providers: [MatDatepickerModule]
})
export class RegisterModule {}
