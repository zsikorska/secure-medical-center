import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomePageComponent} from "./components/home-page/home-page.component";
import {ResultExaminationComponent} from "./components/result-examination/result-examination.component";
import {ContactComponent} from "./components/contact/contact.component";
import {MakeAppointmentComponent} from "./components/make-appointment/make-appointment.component";
import {UserLoginComponent} from "./components/user-login/user-login.component";
import {UserRegisterComponent} from "./components/user-register/user-register.component";

const routes: Routes = [
  {
    path: '',
    component: HomePageComponent,
  },
  {
    path: 'make-appointment',
    component: MakeAppointmentComponent,
  },
  {
    path: 'examination-result',
    component: ResultExaminationComponent,
  },
  {
    path: 'contact',
    component: ContactComponent,
  },
  {
    path: 'account',
    children: [
      {
        path: 'login',
        component: UserLoginComponent,
      },
      {
        path: 'register',
        component: UserRegisterComponent,
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
