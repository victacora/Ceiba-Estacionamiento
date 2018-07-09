import {TicketParqueadero} from '../ticketparqueadero';
import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {DialogoComponent} from '../dialogo/dialogo.component';
import {MatDialog, MatDialogRef} from '@angular/material';
import {ParqueaderoService} from '../parqueadero.service';

@Component({
  selector: 'app-retirarvehiculo',
  templateUrl: './retirarvehiculo.component.html',
  styleUrls: ['./retirarvehiculo.component.css']
})
export class RetirarvehiculoComponent implements OnInit {

  public placa: string;
  public cilindraje: number;
  public fechaIngreso: string;

  @Output() public recargar = new EventEmitter()

  constructor(private dialog: MatDialog, private parqueaderoService: ParqueaderoService) {}

  ngOnInit() {
  }

  public cargarDatos(ticketParqueadero: TicketParqueadero) {
    if (ticketParqueadero) {
      this.placa = ticketParqueadero.placa;
      this.cilindraje = ticketParqueadero.cilindraje;
      this.fechaIngreso = ticketParqueadero.fechaIngreso;
    }
  }

  public retirarVehiculo() {
    if (this.placa !== '') {
      this.parqueaderoService.retirarVehiculo(this.placa).subscribe((response) => {
        if (response) {
          for (var key in response) {
            this.placa = '';
            this.fechaIngreso = '';
            this.cilindraje = 0;
            this.dialog.open(DialogoComponent, {
              data: {
                titulo: "Informacion",
                mensaje: response[key]
              }
            });
          }
          this.placa = '';
          this.cilindraje = 0;
          this.fechaIngreso = '';
          this.recargar.emit();
        }
        console.info(response);
      });
    }
  }
}
