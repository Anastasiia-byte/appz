import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {ConsultantsComponent} from "./consultants.component";

const routes: Routes = [
  {
    path: '',
    component: ConsultantsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConsultantsRoutingModule {}
