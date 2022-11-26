import {RouterModule, Routes} from "@angular/router";
import {DwellingsComponent} from "./dwellings.component";
import {NgModule} from "@angular/core";

const routes: Routes = [
  {
    path: '',
    component: DwellingsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DwellingsRoutingModule {}
