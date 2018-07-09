import { Resultado } from './resultado';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ParqueaderoService {

  API_URL = 'http://localhost:9091/parqueadero';

  constructor(private httpClient: HttpClient) {}

  getlistadovehiculos(pagina, tamano, dirOrdenamiento, campoOrdenamiento): Observable<Resultado> {
    return this.httpClient.get<Resultado>
      (this.API_URL + '/listadovehiculos?pagina=' + pagina + '&tamano=' + tamano +
       '&dirOrdenamiento=' + dirOrdenamiento + '&campoOrdenamiento=' + campoOrdenamiento);
  }

  ingresarVehiculo(vehiculo) {
    return this.httpClient.post(this.API_URL + '/ingresarvehiculo', vehiculo);
  }

  retirarVehiculo(placa) {
    return this.httpClient.post(this.API_URL + '/retirarvehiculo', placa);
  }

}
