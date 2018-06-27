package co.com.ceiba.estacionamiento.persistencia.repositorio.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.estacionamiento.persistencia.entidades.TicketParqueaderoEntity;

public interface  TicketParqueaderoRepositorioJPA  extends CrudRepository<TicketParqueaderoEntity, Long> {
	
	@Query("SELECT COUNT(*) FROM TicketParqueadero t  WHERE t.tipoVehiculo=:tipoVehiculo")
	public Integer verificarCupoVehiculo(@Param("tipoVehiculo")String tipoVehiculo);
	
	@Query("SELECT COUNT(*) FROM TicketParqueadero t  WHERE t.placa=:placa AND t.fechaSalida IS NOT NULL")
	public Integer verificarIngresoVehiculo(@Param("placa") String placa);
}