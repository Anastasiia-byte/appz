import {DwellingsComponent} from "./dwellings.component";
import {NgModule} from "@angular/core";
import {DwellingsRoutingModule} from "./dwellings-routing.module";
import {MatCardModule} from "@angular/material/card";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatButtonModule} from "@angular/material/button";
import {CommonModule} from "@angular/common";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";

@NgModule({
  declarations: [DwellingsComponent],
    imports: [DwellingsRoutingModule, MatCardModule, MatExpansionModule, MatButtonModule, CommonModule, MatProgressSpinnerModule],
})
export class DwellingsModule {}
