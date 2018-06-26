package co.com.ceiba.estacionamiento.persistencia.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;

public interface  RepositorioTicketParqueadero  extends CrudRepository<TicketParqueadero, Long> {
	
	@Query("SELECT COUNT(t.placa) FROM TicketParqueadero t  WHERE t.tipoVehiculo=(:tipoVehiculo)")
	public Integer verificarCupoVehiculo(@Param("tipoVehiculo")String tipoVehiculo);
	
	@Query("SELECT COUNT(t.placa) FROM TicketParqueadero t  WHERE t.placa=(:placa) AND t.fechaFin IS NOT NULL")
	public Integer verificarIngresoVehiculo(@Param("placa") String placa);
}