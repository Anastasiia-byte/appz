import {NgModule} from "@angular/core";
import {ChatRoutingModule} from "./chat-routing.module";
import {MatCardModule} from "@angular/material/card";
import {ChatComponent} from "./chat.component";
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {CommonModule} from "@angular/common";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";

@NgModule({
  declarations: [ChatComponent],
    imports: [ChatRoutingModule, MatCardModule, MatFormFieldModule, FormsModule, MatInputModule, MatButtonModule, MatIconModule, CommonModule, MatProgressSpinnerModule]
})
export class ChatModule {}
