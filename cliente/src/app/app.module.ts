import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LayoutModule} from '@angular/cdk/layout';
import {MatCardModule ,MatToolbarModule,MatFormFieldModule,MatInputModule, MatButtonModule, 
  MatSidenavModule, MatIconModule, MatListModule} from '@angular/material';



@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    FlexLayoutModule,
    BrowserModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
