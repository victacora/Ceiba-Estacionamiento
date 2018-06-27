package co.com.ceiba.estacionamiento.persistencia.repositorio.jpa;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.estacionamiento.persistencia.entidades.VehiculoEntity;



public interface  IVehiculoRepositorio  extends CrudRepository<VehiculoEntity, String> {

}