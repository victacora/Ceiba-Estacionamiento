package co.com.ceiba.estacionamiento.persistencia.repositorio;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.persistencia.entidades.TicketParqueaderoEntity;

@Repository
public interface TicketParqueaderoRepositorio extends PagingAndSortingRepository<TicketParqueaderoEntity, Long> {

	@Query(value = "SELECT COUNT(*) FROM ticket_parqueadero t JOIN vehiculo v ON t.placa=v.placa WHERE v.tipo_vehiculo=:tipoVehiculo AND t.fecha_salida IS NULL", nativeQuery = true)
	public Integer verificarCupoVehiculo(@Param("tipoVehiculo") String tipoVehiculo);

	@Query(value = "SELECT COUNT(*) FROM ticket_parqueadero t  WHERE t.placa=:placa AND t.fecha_salida IS NULL", nativeQuery = true)
	public Integer verificarIngresoVehiculo(@Param("placa") String placa);

	@Query(value = "SELECT * FROM ticket_parqueadero t  WHERE t.fecha_salida IS NULL", countQuery = "SELECT COUNT(*) FROM ticket_parqueadero t  WHERE t.fecha_salida IS NULL", nativeQuery = true)
	public Page<TicketParqueaderoEntity> listarVehiculosParqueadero(Pageable pageable);

	@Query(value = "SELECT * FROM ticket_parqueadero t  WHERE t.placa=:placa AND t.fecha_salida IS NULL", nativeQuery = true)
	public Optional<TicketParqueaderoEntity> obtenerTicketParqueaderoByPlaca(@Param("placa") String placa);

	@Query(value = "SELECT COUNT(*) FROM ticket_parqueadero t  WHERE t.fecha_salida IS NULL", nativeQuery = true)
	public int contarVehiculosParqueadero();

}