import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {AppComponent} from './app.component';
import {AdminLayoutComponent} from './layouts/admin-layout/admin-layout.component';
import {AuthLayoutComponent} from './layouts/auth-layout/auth-layout.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppRoutingModule} from './app.routing';
import {ComponentsModule} from './components/components.module';
import {NgxSpinnerModule} from 'ngx-spinner';
import {AdminLayoutModule} from './layouts/admin-layout/admin-layout.module';

// @ts-ignore
// @ts-ignore
@NgModule({
    imports: [
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,
        ComponentsModule,
        NgbModule,
        RouterModule,
        AppRoutingModule,
        ReactiveFormsModule,
        MatSnackBarModule,
        NgxSpinnerModule,
        AdminLayoutModule,


    ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    AuthLayoutComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
