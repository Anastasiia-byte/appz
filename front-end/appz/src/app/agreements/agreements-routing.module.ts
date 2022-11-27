import {RouterModule, Routes} from "@angular/router";
import {AgreementsComponent} from "./agreements.component";
import {NgModule} from "@angular/core";

const routes: Routes = [
  {
    path: '',
    component: AgreementsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AgreementsRoutingModule {}
