import { NewsletterAddComponent } from './component/newsletter/newsletter-add/newsletter-add.component';
import { NewsletterGeneralComponent } from './component/newsletter/newsletter-general/newsletter-general.component';
import { PostUpdateComponent } from './component/post/post-update/post-update.component';
import { PostAddComponent } from './component/post/post-add/post-add.component';
import { PostGeneralComponent } from './component/post/post-general/post-general.component';
import { CommentUpdateComponent } from './component/comment/comment-update/comment-update.component';
import { CommentAddComponent } from './component/comment/comment-add/comment-add.component';
import { CommentGeneralComponent } from './component/comment/comment-general/comment-general.component';
import { NotificationAddComponent } from './component/notification/notification-add/notification-add.component';
import { NotificationGeneralComponent } from './component/notification/notification-general/notification-general.component';
import { UserInfoComponent } from './component/user/user-info/user-info.component';
import { UserUpdateComponent } from './component/user/user-update/user-update.component';
import { UserAddComponent } from './component/user/user-add/user-add.component';
import { UserGeneralComponent } from './component/user/user-general/user-general.component';
import { GeneralComponent } from './component/statics/general/general.component';
import { PageViewsComponent } from './component/statics/page-views/page-views.component';
import { SettingsComponent } from './component/home/settings/settings.component';
import { MailComponent } from './component/home/mail/mail.component';
import { PostsComponent } from './component/home/posts/posts.component';
import { CommentsComponent } from './component/home/comments/comments.component';
import { NotificationsComponent } from './component/home/notifications/notifications.component';
import { UsersComponent } from './component/home/users/users.component';
import { StaticsComponent } from './component/home/statics/statics.component';
import { HomeComponent } from './pages/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'home',component:HomeComponent, children:[
    {path: 'statics', component: StaticsComponent, children:[
      {path:'',component:GeneralComponent},
      {path:'views',component:PageViewsComponent}
    ]},
    {path: 'users', component: UsersComponent,children:[
      {path:'',component:UserGeneralComponent},
      {path:'add',component:UserAddComponent},
      {path:'update',component:UserUpdateComponent},
      {path:'info',component:UserInfoComponent}
    ]},
    {path: 'notification', component: NotificationsComponent, children:[
      {path:'',component:NotificationGeneralComponent},
      {path:'add',component:NotificationAddComponent}
    ]},
    {path: 'comments', component: CommentsComponent, children:[
      {path:'',component:CommentGeneralComponent},
      {path:'add',component:CommentAddComponent},
      {path:'update',component:CommentUpdateComponent}
    ]},
    {path: 'post', component: PostsComponent, children:[
      {path:'',component:PostGeneralComponent},
      {path:'add',component:PostAddComponent},
      {path:'update',component:PostUpdateComponent}
    ]},
    {path: 'mail', component: MailComponent, children:[
      {path:'',component:NewsletterGeneralComponent},
      {path:'add',component:NewsletterAddComponent}
    ]},
    {path: 'settings', component: SettingsComponent},
  ]}

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
