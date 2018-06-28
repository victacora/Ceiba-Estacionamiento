package co.com.ceiba.estacionamiento.dominio.validaciones;

import co.com.ceiba.estacionamiento.dominio.Carro;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.dominio.excepciones.CupoExcedidoException;
import co.com.ceiba.estacionamiento.dominio.servicios.ITicketParqueaderoServicio;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoVehiculo;

public class ValidacionCupo implements IValidacion {

	private ITicketParqueaderoServicio ticketParqueaderoServicio;

	public ValidacionCupo(ITicketParqueaderoServicio repositorioTicketParqueadero) {
		this.ticketParqueaderoServicio = repositorioTicketParqueadero;
	}

	@Override
	public void validar(Vehiculo vehiculo) {
		if (vehiculo instanceof Carro) {
			Integer totalCarrosIngresados = this.ticketParqueaderoServicio
					.verificarCupoVehiculo(EnumTipoVehiculo.CARRO.name());
			if (totalCarrosIngresados > Vigilante.NUMERO_MAXIMO_CUPOS_CARRO) {
				throw new CupoExcedidoException("No hay cupos disponibles para carros.");
			}
		} else {
			Integer totalMotosIngresadas = this.ticketParqueaderoServicio
					.verificarCupoVehiculo(EnumTipoVehiculo.MOTO.name());
			if (totalMotosIngresadas > Vigilante.NUMERO_MAXIMO_CUPOS_MOTO) {
				throw new CupoExcedidoException("No hay cupos disponibles para motos.");
			}

		}

	}

}
