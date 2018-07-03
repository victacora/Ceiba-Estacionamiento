package co.com.ceiba.estacionamiento.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.com.ceiba.estacionamiento.persistencia.entidades.VehiculoEntity;

@Repository
public interface  VehiculoRepositorio  extends CrudRepository<VehiculoEntity, String> {

}