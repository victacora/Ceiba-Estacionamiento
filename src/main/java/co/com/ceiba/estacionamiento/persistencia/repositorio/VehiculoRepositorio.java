package co.com.ceiba.estacionamiento.persistencia.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import co.com.ceiba.estacionamiento.persistencia.entidades.VehiculoEntity;

@Repository
public interface  VehiculoRepositorio  extends PagingAndSortingRepository<VehiculoEntity, String> {

}