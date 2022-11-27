import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthGuard} from "./guards/auth.guard";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'chats',
    loadChildren: () => import('./chats/chats.module').then((m) => m.ChatsModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'chat',
    loadChildren: () => import('./chat/chat.module').then((m) => m.ChatModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'dwellings',
    loadChildren: () => import('./dwellings/dwellings.module').then((m) => m.DwellingsModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then((m) => m.LoginModule)
  },
  {
    path: 'register',
    loadChildren: () => import('./register/register.module').then((m) => m.RegisterModule)
  },
  {
    path: 'consultants',
    loadChildren: () => import('./consultants/consultants.module').then((m) => m.ConsultantsModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'agreements',
    loadChildren: () => import('./agreements/agreements.module').then((m) => m.AgreementsModule),
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
