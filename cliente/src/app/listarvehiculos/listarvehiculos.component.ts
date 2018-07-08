import {TicketParqueadero} from '../ticketparqueadero';
import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource, MatPaginator, MatSort} from '@angular/material';
import {ParqueaderoService} from '../parqueadero.service';
import {DataSource} from '@angular/cdk/collections';
import {merge, Observable, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-listarvehiculos',
  templateUrl: './listarvehiculos.component.html',
  styleUrls: ['./listarvehiculos.component.css']
})

export class ListarvehiculosComponent implements OnInit {

  cargando = true;
  datos: TicketParqueadero[] = [];

  listaColumnas = ['id', 'placa', 'tipo_vehiculo', 'fecha_ingreso', 'operaciones'];
  pageSize = 10;
  totalRegistros = 0;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private parqueaderoService: ParqueaderoService) {}

  ngAfterViewInit() {
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
      startWith({}),
      switchMap(() => {
        this.cargando = true;
        return this.parqueaderoService.getlistadovehiculos(this.paginator.pageIndex,
          this.pageSize, this.sort.direction, this.sort.active);
      }),
      map(data => {
        this.cargando = false;
        this.totalRegistros = data.length;
        return data;
      }),
      catchError(() => {
        this.cargando = false;
        return observableOf([]);
      })
      ).subscribe(data => this.datos = data);

  }

  ngOnInit() {

  }

  regitrarSalida(elment) {


  }

}

