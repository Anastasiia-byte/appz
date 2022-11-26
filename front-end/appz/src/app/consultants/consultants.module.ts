import {NgModule} from "@angular/core";
import {ConsultantsComponent} from "./consultants.component";
import {ConsultantsRoutingModule} from "./consultants-routing.module";
import {CommonModule} from "@angular/common";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatIconModule} from "@angular/material/icon";
import {MatNativeDateModule} from "@angular/material/core";
import {MatButtonModule} from "@angular/material/button";
import {MatDividerModule} from "@angular/material/divider";

@NgModule({
  declarations: [ConsultantsComponent],
  imports: [ConsultantsRoutingModule, CommonModule, MatCardModule, MatFormFieldModule, MatInputModule, MatNativeDateModule, ReactiveFormsModule, MatDatepickerModule, MatIconModule, MatButtonModule, MatDividerModule],
  providers: [MatDatepickerModule]
})
export class ConsultantsModule {}
