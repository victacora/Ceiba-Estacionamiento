import {IngresarvehiculoComponent} from './ingresarvehiculo/ingresarvehiculo.component';
import {ListarvehiculosComponent} from './listarvehiculos/listarvehiculos.component';
import {RetirarvehiculoComponent} from './retirarvehiculo/retirarvehiculo.component';
import {TicketParqueadero} from './ticketparqueadero';
import {Component, EventEmitter, Output, ViewChild} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Parqueadero';
  @ViewChild(IngresarvehiculoComponent) private ingresarVehiculo:
  IngresarvehiculoComponent;
  
  @ViewChild(ListarvehiculosComponent) private listarVehiculos:
  ListarvehiculosComponent;
  
  @ViewChild(RetirarvehiculoComponent) private retirarVehiculoComparendo:
  RetirarvehiculoComponent;

  public registrarSalidaParqueadero(ticketParqueadero: TicketParqueadero) {
    this.retirarVehiculoComparendo.cargarDatos(ticketParqueadero);
  }

   public recargarListadoParqueadero() {
    this.listarVehiculos.cargarRegistros();
  }


}
