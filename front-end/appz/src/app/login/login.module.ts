import {NgModule} from "@angular/core";
import {MatCardModule} from "@angular/material/card";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatButtonModule} from "@angular/material/button";
import {CommonModule} from "@angular/common";
import {LoginComponent} from "./login.component";
import {LoginRoutingModule} from "./login-routing.module";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";

@NgModule({
  declarations: [LoginComponent],
  imports: [LoginRoutingModule, MatCardModule, MatExpansionModule, MatButtonModule, CommonModule, MatToolbarModule, MatIconModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule],
})
export class LoginModule {}
