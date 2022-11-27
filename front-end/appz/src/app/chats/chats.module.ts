import {ChatsComponent} from "./chats.component";
import {NgModule} from "@angular/core";
import {ChatsRoutingModule} from "./chats-routing.module";
import {MatCardModule} from "@angular/material/card";
import {CommonModule} from "@angular/common";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";

@NgModule({
  declarations: [ChatsComponent],
    imports: [ChatsRoutingModule, MatCardModule, CommonModule, MatProgressSpinnerModule]
})
export class ChatsModule {}
