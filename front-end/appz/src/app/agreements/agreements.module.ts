import {AgreementsComponent} from "./agreements.component";
import {NgModule} from "@angular/core";
import {AgreementsRoutingModule} from "./agreements-routing.module";
import {MatCardModule} from "@angular/material/card";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatButtonModule} from "@angular/material/button";
import {CommonModule} from "@angular/common";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";

@NgModule({
  declarations: [AgreementsComponent],
    imports: [AgreementsRoutingModule, MatCardModule, MatExpansionModule, MatButtonModule, CommonModule, MatProgressSpinnerModule],
})
export class AgreementsModule {}
