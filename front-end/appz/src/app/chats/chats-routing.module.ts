import {RouterModule, Routes} from "@angular/router";
import {ChatsComponent} from "./chats.component";
import {NgModule} from "@angular/core";

const routes: Routes = [
  { path: '', component: ChatsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChatsRoutingModule {}
