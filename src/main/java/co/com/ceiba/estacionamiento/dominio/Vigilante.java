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
	public static final int VEHICULO_NO_REGISTRADO = 0;
	private static final double MIN_CILINDRAJE_MOTO_COSTO_ADICIONAL = 500;
	private static final int TOTAL_MILISEGUNDOS_EN_UNA_HORA = 1000 * 60 * 60;
	private static final int TOTAL_HORAS_EN_UN_DIA = 24;
	private static final int MIN_HORAS_POR_DIA = 9;

	List<IValidacion> validaciones;

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
		if (vehiculoServicio.obtenerVehiculo(vehiculo.getPlaca()) == null) {
			vehiculoServicio.crearVehiculo(vehiculo);
		}
		return ticketParqueaderoServicio.crearTicketParqueadero(new TicketParqueadero(new Date(), vehiculo));
	}

	public boolean retirarVehiculo(String placa) {
		boolean resultado = false;

		for (IValidacion validacion : this.validaciones) {
			validacion.validar(new Vehiculo(placa));
		}

		TicketParqueadero ticketParqueadero = ticketParqueaderoServicio.obtenerTicketParquedero(placa);
		Date fechaSalida = new Date();
		ticketParqueadero.setFechaSalida(fechaSalida);
		double valor = calcularValorAPagar(ticketParqueadero);
		ticketParqueadero.setValor(valor);
		resultado = ticketParqueaderoServicio.actualizarTicketParqueadero(ticketParqueadero);
		return resultado;
	}

	public double calcularValorAPagar(TicketParqueadero ticketParqueadero) {
		double valor;

		int totalHorasTranscurridas = (int) (ticketParqueadero.getFechaSalida().getTime()
				- ticketParqueadero.getFechaIngreso().getTime()) / TOTAL_MILISEGUNDOS_EN_UNA_HORA;
		int totalDias = totalHorasTranscurridas / TOTAL_HORAS_EN_UN_DIA;
		int totalHoras = totalHorasTranscurridas % TOTAL_HORAS_EN_UN_DIA;

		if(totalHoras>MIN_HORAS_POR_DIA){
			totalHoras=0;
			totalDias=totalDias+1;
		}
		
		if (ticketParqueadero.getVehiculo() instanceof Moto) {
			Moto moto = (Moto) ticketParqueadero.getVehiculo();

			valor = totalDias
					* tarifaServicio.obtenerValorTarifa(EnumTipoVehiculo.MOTO.name(), EnumTipoTarifa.TIEMPO.name(),
							EnumUnidadTiempo.DIA.name())
					+ totalHoras * tarifaServicio.obtenerValorTarifa(EnumTipoVehiculo.MOTO.name(),
							EnumTipoTarifa.TIEMPO.name(), EnumUnidadTiempo.HORA.name());
			
			if (moto.getCilindraje() > MIN_CILINDRAJE_MOTO_COSTO_ADICIONAL)
				valor = valor + tarifaServicio.obtenerValorTarifa(EnumTipoVehiculo.MOTO.name(),
						EnumTipoTarifa.ADICIONAL.name(), EnumUnidadTiempo.NOAPLICA.name());
		} else {
			valor = totalDias
					* tarifaServicio.obtenerValorTarifa(EnumTipoVehiculo.CARRO.name(), EnumTipoTarifa.TIEMPO.name(),
							EnumUnidadTiempo.DIA.name())
					+ totalHoras * tarifaServicio.obtenerValorTarifa(EnumTipoVehiculo.CARRO.name(),
							EnumTipoTarifa.TIEMPO.name(), EnumUnidadTiempo.HORA.name());
		}
		return valor;
	}
}
