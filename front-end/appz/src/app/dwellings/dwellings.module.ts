import {DwellingsComponent} from "./dwellings.component";
import {NgModule} from "@angular/core";
import {DwellingsRoutingModule} from "./dwellings-routing.module";
import {MatCardModule} from "@angular/material/card";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatButtonModule} from "@angular/material/button";
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [DwellingsComponent],
  imports: [DwellingsRoutingModule, MatCardModule, MatExpansionModule, MatButtonModule, CommonModule],
})
export class DwellingsModule {}
