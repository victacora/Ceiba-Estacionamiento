import { TicketParqueadero } from '../ticketparqueadero';
import { Component, OnInit, ViewChild } from '@angular/core';
import {MatTableDataSource, MatPaginator, MatSort} from '@angular/material';

@Component({
  selector: 'app-listarvehiculos',
  templateUrl: './listarvehiculos.component.html',
  styleUrls: ['./listarvehiculos.component.css']
})
  
export class ListarvehiculosComponent implements OnInit {

  loading  =  true;
  
  dataSource = new MatTableDataSource<TicketParqueadero>();
  
  displayedColumns = ['id', 'placa', 'tipoVehiculo', 'fechaIngreso','operaciones'];
  pageSize = 10;
  
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  
  constructor() { }

  ngOnInit() {
  }

  regitrarSalida(elment){
    
    
  }
  
}
