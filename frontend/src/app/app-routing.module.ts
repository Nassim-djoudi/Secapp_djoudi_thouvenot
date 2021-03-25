import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChoiceComponent } from './choice/choice.component';
import { FormComponent } from './form/form.component';
import { LoggingComponent } from './logging/logging.component';
import { MenuComponent } from './menu/menu.component';

/**
 * Gestion du routage des pages et des composants associes
 */
const routes: Routes = [
                        {path: '', component: MenuComponent},
                        {path: 'add-form', component: FormComponent},
                        {path: 'choice/:id', component: ChoiceComponent},
                        {path: 'logging', component: LoggingComponent},
                        {path: 'logging/secure', component: LoggingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
