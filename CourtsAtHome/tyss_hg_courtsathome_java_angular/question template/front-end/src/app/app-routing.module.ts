import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ViewCaseDetailsComponent } from './view-case-details/view-case-details.component';

const routes: Routes = [
  {path:'auth',component:HomeComponent},
  {path:'view-case-details',component:ViewCaseDetailsComponent},
  {path:'',component:HomeComponent,pathMatch:'full'},
  {path:'**',redirectTo:''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
