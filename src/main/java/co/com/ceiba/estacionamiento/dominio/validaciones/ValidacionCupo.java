package co.com.ceiba.estacionamiento.dominio.validaciones;

import co.com.ceiba.estacionamiento.dominio.Carro;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.dominio.excepciones.CupoExcedidoException;
import co.com.ceiba.estacionamiento.dominio.repositorio.TicketParqueaderoRepositorio;
import co.com.ceiba.estacionamiento.enumeraciones.TipoVehiculo;

public class ValidacionCupo implements Validacion {

	private TicketParqueaderoRepositorio repositorioTicketParqueadero;

	public ValidacionCupo(TicketParqueaderoRepositorio repositorioTicketParqueadero) {
		this.repositorioTicketParqueadero = repositorioTicketParqueadero;
	}

	@Override
	public void validar(Vehiculo vehiculo) {
		if (vehiculo instanceof Carro) {
			if (this.repositorioTicketParqueadero
					.verificarCupoVehiculo(TipoVehiculo.CARRO.name()) == Vigilante.NUMERO_MAXIMO_CUPOS_CARRO) {
				throw new CupoExcedidoException("No hay cupos disponibles para carros");
			}
		} else {
			if (this.repositorioTicketParqueadero
					.verificarCupoVehiculo(TipoVehiculo.MOTO.name()) == Vigilante.NUMERO_MAXIMO_CUPOS_MOTO) {
				throw new CupoExcedidoException("No hay cupos disponibles para motos");
			}

		}

	}

}
