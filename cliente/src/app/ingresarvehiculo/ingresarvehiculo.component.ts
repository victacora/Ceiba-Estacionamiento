import {DialogoComponent} from '../dialogo/dialogo.component';
import {Component, OnInit, Output, EventEmitter, ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material';
import {ParqueaderoService} from '../parqueadero.service';

@Component({
  selector: 'app-ingresarvehiculo',
  templateUrl: './ingresarvehiculo.component.html',
  styleUrls: ['./ingresarvehiculo.component.css']
})
export class IngresarvehiculoComponent implements OnInit {

  public placa: string = '';
  public cilindraje: number = 0.0;

  @ViewChild('ingresarvehiculofrm') ingresarvehiculofrm;

  @Output() public recargar = new EventEmitter();

  constructor(private dialog: MatDialog, private parqueaderoService: ParqueaderoService) {}

  ngOnInit() {
  }



  ingresarVehiculo() {
    if (!this.placa || this.placa === '') {
      this.dialog.open(DialogoComponent, {
        data: {
          titulo: "Error al validar datos",
          mensaje: "La placa del vehiculo es obligatoria."
        }
      });
    }
    else {
      var vehiculo = {
        placa: this.placa,
        cilindraje: this.cilindraje,
        tipoVehiculo: this.cilindraje > 0 ? 'MOTO' : 'CARRO'
      };

      this.parqueaderoService.ingresarVehiculo(vehiculo).subscribe((response) => {
        this.dialog.open(DialogoComponent, {
          data: {
            titulo: "Informacion",
            mensaje: "Entrada registrada, para vehiculo con placa " + this.placa + "."
          }
        });
        this.recargar.emit();
        this.placa = '';
        this.cilindraje = 0;
        this.ingresarvehiculofrm.reset();
      }, (error) => {
        this.dialog.open(DialogoComponent, {
          data: {
            titulo: "Error",
            mensaje: error.error.message
          }
        });
      });
    }
  }
}
