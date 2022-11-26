import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'dwellings',
    pathMatch: 'full'
  },
  {
    path: 'chats',
    loadChildren: () => import('./chats/chats.module').then((m) => m.ChatsModule)
  },
  {
    path: 'chat',
    loadChildren: () => import('./chat/chat.module').then((m) => m.ChatModule),
  },
  {
    path: 'dwellings',
    loadChildren: () => import('./dwellings/dwellings.module').then((m) => m.DwellingsModule)
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
    loadChildren: () => import('./consultants/consultants.module').then((m) => m.ConsultantsModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
