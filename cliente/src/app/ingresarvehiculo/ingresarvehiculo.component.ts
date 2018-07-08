import {DialogoComponent} from '../dialogo/dialogo.component';
import {Component, OnInit} from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-ingresarvehiculo',
  templateUrl: './ingresarvehiculo.component.html',
  styleUrls: ['./ingresarvehiculo.component.css']
})
export class IngresarvehiculoComponent implements OnInit {

  public placa: string = "";
  public cilindraje: number = 0.0;

  constructor(private dialog: MatDialog) {}

  ngOnInit() {
  }


  ingresarVehiculo() {
    if (this.placa || this.placa === "") {
      this.dialog.open(DialogoComponent, {
        data: {
          titulo: "Error al validar datos",
          mensaje: "La placa del vehiculo es obligatoria."
        }
      });
    }
    else {
      //Almacenar en el servidor
    }
  }
}
