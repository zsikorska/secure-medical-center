import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HomePageComponent} from "./components/home-page/home-page.component";
import {NavBarComponent} from "./components/nav-bar/nav-bar.component";
import {FooterComponent} from "./components/footer/footer.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MaterialSharedModule} from "./material-shared/material-shared.module";
import { MakeAppointmentComponent } from './components/make-appointment/make-appointment.component';
import { ContactComponent } from './components/contact/contact.component';
import { ResultExaminationComponent } from './components/result-examination/result-examination.component';
import {HttpClientModule} from "@angular/common/http";
import { UserRegisterComponent } from './components/user-register/user-register.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import {ToastrModule} from "ngx-toastr";
import { DialogCreatedAppointmentComponent } from './components/dialog-created-appointment/dialog-created-appointment.component';
import {ClipboardModule} from "@angular/cdk/clipboard";
import { DynamicScriptDirective } from './directives/dynamic-script.directive';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    NavBarComponent,
    FooterComponent,
    MakeAppointmentComponent,
    ContactComponent,
    ResultExaminationComponent,
    UserRegisterComponent,
    UserLoginComponent,
    DialogCreatedAppointmentComponent,
    DynamicScriptDirective,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialSharedModule,
    ClipboardModule,

    ToastrModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
