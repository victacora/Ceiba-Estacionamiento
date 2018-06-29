package co.com.ceiba.estacionamiento.dominio;

import java.util.Date;
import java.util.List;

import co.com.ceiba.estacionamiento.dominio.servicios.ITarifaServicio;
import co.com.ceiba.estacionamiento.dominio.servicios.ITicketParqueaderoServicio;
import co.com.ceiba.estacionamiento.dominio.servicios.IVehiculoServicio;
import co.com.ceiba.estacionamiento.dominio.validaciones.IValidacion;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoTarifa;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoVehiculo;
import co.com.ceiba.estacionamiento.enumeraciones.EnumUnidadTiempo;

public class Vigilante {

	public static final int NUMERO_MAXIMO_CUPOS_CARRO = 20;
	public static final int NUMERO_MAXIMO_CUPOS_MOTO = 10;
	private static final double MIN_CILINDRAJE_MOTO = 500;
	private static final int UNA_HORA_EN_MILISEG = 1000 * 60 * 60;
	private static final int UN_DIA_EN_MILISEG = 24;
	private static final int MIN_HORAS_POR_DIA = 9;

	private List<IValidacion> validaciones;

	private ITicketParqueaderoServicio ticketParqueaderoServicio;
	private IVehiculoServicio vehiculoServicio;
	private ITarifaServicio tarifaServicio;

	public Vigilante(ITicketParqueaderoServicio ticketParqueaderoServicio, IVehiculoServicio vehiculoServicio,
			ITarifaServicio tarifaServicio, List<IValidacion> validaciones) {
		this.ticketParqueaderoServicio = ticketParqueaderoServicio;
		this.vehiculoServicio = vehiculoServicio;
		this.tarifaServicio = tarifaServicio;
		this.validaciones = validaciones;
	}

	public boolean ingresarVehiculo(Vehiculo vehiculo) {
		for (IValidacion validacion : this.validaciones) {
			validacion.validar(vehiculo);
		}
		vehiculoServicio.guardarVehiculo(vehiculo);
		return ticketParqueaderoServicio.crearTicketParqueadero(new TicketParqueadero(new Date(), vehiculo));
	}

	public TicketParqueadero retirarVehiculo(String placa) {
		for (IValidacion validacion : this.validaciones) {
			validacion.validar(new Vehiculo(placa));
		}

		TicketParqueadero ticketParqueadero = ticketParqueaderoServicio.obtenerTicketParquedero(placa);
		Date fechaSalida = new Date();
		ticketParqueadero.setFechaSalida(fechaSalida);
		ticketParqueadero.setValor(calcularValorAPagar(ticketParqueadero));
		ticketParqueadero = ticketParqueaderoServicio.actualizarTicketParqueadero(ticketParqueadero);
		return ticketParqueadero;
	}

	private double calcularValorAPagar(TicketParqueadero ticketParqueadero) {
		double valor;
		int totalHorasTranscurridas = obtenerTotalHorasEntreDosFechas(ticketParqueadero.getFechaIngreso(),
				ticketParqueadero.getFechaSalida());
		int totalDias = totalHorasTranscurridas / UN_DIA_EN_MILISEG;
		int totalHoras = totalHorasTranscurridas % UN_DIA_EN_MILISEG;

		if (totalHoras > MIN_HORAS_POR_DIA) {
			totalHoras = 0;
			totalDias = totalDias + 1;
		}

		if (ticketParqueadero.getVehiculo() instanceof Moto) {
			Moto moto = (Moto) ticketParqueadero.getVehiculo();
			valor = aplicarTarifaVehiculo(EnumTipoVehiculo.MOTO, totalDias, totalHoras, moto.getCilindraje());
		} else {
			valor = aplicarTarifaVehiculo(EnumTipoVehiculo.CARRO, totalDias, totalHoras, 0);
		}
		return valor;
	}

	private double aplicarTarifaVehiculo(EnumTipoVehiculo tipoVehiculo, int totalDias, int totalHoras,
			double cilindraje) {
		return totalDias
				* tarifaServicio.obtenerValorTarifa(tipoVehiculo.name(), EnumTipoTarifa.TIEMPO.name(),
						EnumUnidadTiempo.DIA.name())
				+ totalHoras * tarifaServicio.obtenerValorTarifa(tipoVehiculo.name(), EnumTipoTarifa.TIEMPO.name(),
						EnumUnidadTiempo.HORA.name())
				+ aplicarCostoAdicionalMoto(tipoVehiculo, cilindraje);
	}

	private double aplicarCostoAdicionalMoto(EnumTipoVehiculo tipoVehiculo, double cilindraje) {
		if (tipoVehiculo == EnumTipoVehiculo.MOTO && cilindraje > MIN_CILINDRAJE_MOTO) {
			return tarifaServicio.obtenerValorTarifa(EnumTipoVehiculo.MOTO.name(), EnumTipoTarifa.ADICIONAL.name(),
					EnumUnidadTiempo.NOAPLICA.name());
		}
		return 0;
	}

	private int obtenerTotalHorasEntreDosFechas(Date fechaInicial, Date fechafinal) {
		return (int) (fechafinal.getTime() - fechaInicial.getTime()) / UNA_HORA_EN_MILISEG;
	}
}
