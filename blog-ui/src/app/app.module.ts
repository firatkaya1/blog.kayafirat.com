import { ErrorInterceptor } from './interceptor/ErrorInterceptor';
import { AuthService } from './service/auth/AuthenticateService';
import { LoginGuard } from './service/guard/guardService';
import { ArticleComponent } from './pages/article/article.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostComponent } from './components/blog/post/post.component';
import { SearchComponent } from './components/blog/search/search.component';
import { MenuComponent } from './components/menu/menu.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { NotFoundComponent } from './pages/errors/not-found/not-found.component';
import { UnauthorizedComponent } from './pages/errors/unauthorized/unauthorized.component';
import { RegisterComponent } from './components/register/register.component';
import { JoinComponent } from './pages/join/join.component';
import { CommentComponent } from './components/blog/comment/comment.component';
import { ArticlesComponent } from './components/blog/articles/articles.component';
import { ResetpasswordComponent } from './components/user/resetpassword/resetpassword.component';
import { ForgotpasswordComponent } from './components/user/forgotpassword/forgotpassword.component';
import { UserComponent } from './pages/user/user.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { SettingsComponent } from './components/user/settings/settings.component';
import { NotificationSettingsComponent } from './components/user/notification-settings/notification-settings.component';
import { FavouritesComponent } from './components/user/favourites/favourites.component';
import { CommentsComponent } from './components/user/comments/comments.component';
import { AccountComponent } from './components/user/account/account.component';
import { TermsofpolicyComponent } from './components/user/termsofpolicy/termsofpolicy.component';
import { ExitComponent } from './components/user/exit/exit.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { AlertComponent } from './components/alert/alert.component';
import { NotificationsComponent } from './components/user/notifications/notifications.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VisibilityComponent } from './components/user/visibility/visibility.component';
import { TranslateModule,TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { HttpClient,HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CommentbarComponent } from './components/blog/commentbar/commentbar.component';
import { NgxSpinnerModule } from "ngx-spinner";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpInterceptorService } from './interceptor/HttpInterceptorService';
import { UnsubscribeComponent } from './components/user/unsubscribe/unsubscribe.component';
import { MailSettingsComponent } from './components/user/mail-settings/mail-settings.component';
import { VerifyComponent } from './components/user/verify/verify.component';
import { PublisherComponent } from './components/blog/publisher/publisher.component';



@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
    SearchComponent,
    MenuComponent,
    LoginComponent,
    HomeComponent,
    NotFoundComponent,
    UnauthorizedComponent,
    RegisterComponent,
    JoinComponent,
    CommentComponent,
    ArticlesComponent,
    ArticleComponent,
    ResetpasswordComponent,
    ForgotpasswordComponent,
    UserComponent,
    ProfileComponent,
    SettingsComponent,
    NotificationSettingsComponent,
    FavouritesComponent,
    CommentsComponent,
    AccountComponent,
    TermsofpolicyComponent,
    ExitComponent,
    SidebarComponent,
    AlertComponent,
    NotificationsComponent,
    VisibilityComponent,
    CommentbarComponent,
    UnsubscribeComponent,
    MailSettingsComponent,
    VerifyComponent,
    PublisherComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule.withServerTransition({ appId: 'serverApp' }),
    AppRoutingModule,
    ReactiveFormsModule,
    NgxSpinnerModule,
    FormsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
      }
  }),
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }, AuthService, LoginGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}