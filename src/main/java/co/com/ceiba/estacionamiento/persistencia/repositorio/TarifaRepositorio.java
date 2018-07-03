package co.com.ceiba.estacionamiento.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.persistencia.entidades.TarifaEntity;
import co.com.ceiba.estacionamiento.persistencia.entidades.TarifaId;

@Repository
public interface  TarifaRepositorio  extends CrudRepository<TarifaEntity, TarifaId> {
	
}