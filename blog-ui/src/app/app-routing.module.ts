import { VerifyComponent } from './components/user/verify/verify.component';
import { UnsubscribeComponent } from './components/user/unsubscribe/unsubscribe.component';
import { LoginGuard } from './service/guard/guardService';
import { NotificationsComponent } from './components/user/notifications/notifications.component';
import { ExitComponent } from './components/user/exit/exit.component';
import { AccountComponent } from './components/user/account/account.component';
import { TermsofpolicyComponent } from './components/user/termsofpolicy/termsofpolicy.component';
import { CommentsComponent } from './components/user/comments/comments.component';
import { FavouritesComponent } from './components/user/favourites/favourites.component';
import { SettingsComponent } from './components/user/settings/settings.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { UserComponent } from './pages/user/user.component';
import { ResetpasswordComponent } from './components/user/resetpassword/resetpassword.component';
import { ForgotpasswordComponent } from './components/user/forgotpassword/forgotpassword.component';
import { ArticleComponent } from './pages/article/article.component';
import { JoinComponent } from './pages/join/join.component';
import { UnauthorizedComponent } from './pages/errors/unauthorized/unauthorized.component';
import { NotFoundComponent } from './pages/errors/not-found/not-found.component';
import { HomeComponent } from './pages/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContactComponent } from './pages/contact/contact.component';


const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'join',component:JoinComponent},
  {path:'a/:articleID',component:ArticleComponent},
  {path:'forgotpassword',component:ForgotpasswordComponent},
  {path:'verify',component:VerifyComponent},
  {path:'resetpassword',component:ResetpasswordComponent},
  {path:'unsubscribe',component:UnsubscribeComponent},
  {path:'user',component:UserComponent,canActivate: [LoginGuard], children:[
    {path: '', component: ProfileComponent},
    {path: 'profile', component: ProfileComponent},
    {path: 'settings', component: SettingsComponent},
    {path: 'notification', component: NotificationsComponent},
    {path: 'favourites', component: FavouritesComponent},
    {path: 'comments', component: CommentsComponent},
    {path: 'account', component: AccountComponent},
    {path: 'terms', component: TermsofpolicyComponent},
    {path: 'exit', component: ExitComponent}
  ]},
  {path:'contact',component:ContactComponent},
  {path:'404',component:NotFoundComponent},
  {path:'403',component:UnauthorizedComponent}

];
@NgModule({
  imports: [RouterModule.forRoot(routes, {
    initialNavigation: 'enabled',
    relativeLinkResolution: 'legacy'
})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
