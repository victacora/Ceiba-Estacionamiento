package co.com.ceiba.estacionamiento.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;

public interface  RepositorioVehiculo  extends CrudRepository<Vehiculo, String> {

}