import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LayoutModule} from '@angular/cdk/layout';
import {
  MatCardModule, MatToolbarModule, MatFormFieldModule, MatInputModule, MatButtonModule,
  MatSidenavModule, MatIconModule, MatListModule, MatDialogModule, MatTableModule, MatPaginatorModule, MatSortModule
} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {IngresarvehiculoComponent} from './ingresarvehiculo/ingresarvehiculo.component';
import {ListarvehiculosComponent} from './listarvehiculos/listarvehiculos.component';
import {RetirarvehiculoComponent} from './retirarvehiculo/retirarvehiculo.component';
import {DialogoComponent} from './dialogo/dialogo.component';



@NgModule({
  declarations: [
    AppComponent,
    IngresarvehiculoComponent,
    ListarvehiculosComponent,
    RetirarvehiculoComponent,
    DialogoComponent
  ],
  entryComponents: [DialogoComponent],
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
    MatListModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule
  ],
  exports: [
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
    MatListModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
