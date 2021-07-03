import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { SidebarComponent } from './component/sidebar/sidebar.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { StaticsComponent } from './component/home/statics/statics.component';
import { UsersComponent } from './component/home/users/users.component';
import { NotificationsComponent } from './component/home/notifications/notifications.component';
import { CommentsComponent } from './component/home/comments/comments.component';
import { PostsComponent } from './component/home/posts/posts.component';
import { MailComponent } from './component/home/mail/mail.component';
import { SettingsComponent } from './component/home/settings/settings.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { PageViewsComponent } from './component/statics/page-views/page-views.component';
import { GeneralComponent } from './component/statics/general/general.component';
import { UserGeneralComponent } from './component/user/user-general/user-general.component';
import { UserAddComponent } from './component/user/user-add/user-add.component';
import { UserUpdateComponent } from './component/user/user-update/user-update.component';
import { UserInfoComponent } from './component/user/user-info/user-info.component';
import { NotificationAddComponent } from './component/notification/notification-add/notification-add.component';
import { NotificationGeneralComponent } from './component/notification/notification-general/notification-general.component';
import { CommentGeneralComponent } from './component/comment/comment-general/comment-general.component';
import { CommentAddComponent } from './component/comment/comment-add/comment-add.component';
import { CommentUpdateComponent } from './component/comment/comment-update/comment-update.component';
import { PostGeneralComponent } from './component/post/post-general/post-general.component';
import { PostAddComponent } from './component/post/post-add/post-add.component';
import { PostUpdateComponent } from './component/post/post-update/post-update.component';
import { CKEditorModule } from 'ckeditor4-angular';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NewsletterGeneralComponent } from './component/newsletter/newsletter-general/newsletter-general.component';
import { NewsletterAddComponent } from './component/newsletter/newsletter-add/newsletter-add.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SidebarComponent,
    DashboardComponent,
    StaticsComponent,
    UsersComponent,
    NotificationsComponent,
    CommentsComponent,
    PostsComponent,
    MailComponent,
    SettingsComponent,
    PageViewsComponent,
    GeneralComponent,
    UserGeneralComponent,
    UserAddComponent,
    UserUpdateComponent,
    UserInfoComponent,
    NotificationAddComponent,
    NotificationGeneralComponent,
    CommentGeneralComponent,
    CommentAddComponent,
    CommentUpdateComponent,
    PostGeneralComponent,
    PostAddComponent,
    PostUpdateComponent,
    NewsletterGeneralComponent,
    NewsletterAddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgxChartsModule,
    BrowserAnimationsModule,
    CKEditorModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
