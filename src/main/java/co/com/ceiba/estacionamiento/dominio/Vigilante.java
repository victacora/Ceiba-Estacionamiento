package co.com.ceiba.estacionamiento.dominio;

import java.util.Date;

import co.com.ceiba.estacionamiento.dominio.excepciones.AccesoRestringidoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.CupoExcedidoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoNoEncontradoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoNoRegistradoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoRegistradoException;
import co.com.ceiba.estacionamiento.dominio.servicios.TarifaServicio;
import co.com.ceiba.estacionamiento.dominio.servicios.ParqueaderoServicio;
import co.com.ceiba.estacionamiento.dominio.servicios.VehiculoServicio;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoTarifa;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoVehiculo;
import co.com.ceiba.estacionamiento.enumeraciones.EnumUnidadTiempo;

public class Vigilante {

	public static final String MSJ_EL_VEHICULO_SE_ENCUENTRA_REGISTRADO = "El vehiculo se encuentra registrado.";
	public static final String MSJ_EL_VEHICULO_NO_EXISTE = "El vehiculo no existe.";
	public static final String MSJ_EL_VEHICULO_NO_SE_ENCUENTRA_REGISTRADO = "El vehiculo no se encuentra registrado.";
	public static final String MSJ_NO_ESTA_AUTORIZADO_PARA_INGRESAR = "No esta autorizado para ingresar.";
	public static final String MSJ_NO_HAY_CUPOS_DISPONIBLES = "No hay cupos disponibles.";
	public static final int NUMERO_MAXIMO_CUPOS_CARRO = 20;
	public static final int NUMERO_MAXIMO_CUPOS_MOTO = 10;
	private static final double MIN_CILINDRAJE_MOTO = 500;
	private static final int UNA_HORA_EN_MILISEG = 1000 * 60 * 60;
	private static final int TOTAL_HORAS_EN_UN_DIA = 24;
	private static final int MIN_HORAS_POR_DIA = 9;
	private static final int VEHICULO_NO_REGISTRADO = 0;

	private ParqueaderoServicio parqueaderoServicio;
	private VehiculoServicio vehiculoServicio;
	private TarifaServicio tarifaServicio;
	private CalendarioVigilante calendarioVigilante;

	public Vigilante(ParqueaderoServicio parqueaderoServicio, VehiculoServicio vehiculoServicio,
			TarifaServicio tarifaServicio, CalendarioVigilante calendarioVigilante) {
		this.parqueaderoServicio = parqueaderoServicio;
		this.vehiculoServicio = vehiculoServicio;
		this.tarifaServicio = tarifaServicio;
		this.calendarioVigilante = calendarioVigilante;
	}

	private void validarCupoVehiculo(Vehiculo vehiculo) {
		if (vehiculo instanceof Carro) {
			Integer totalCarrosIngresados = this.parqueaderoServicio
					.verificarCupoVehiculo(EnumTipoVehiculo.CARRO.name());
			if (totalCarrosIngresados >= NUMERO_MAXIMO_CUPOS_CARRO) {
				throw new CupoExcedidoException(MSJ_NO_HAY_CUPOS_DISPONIBLES);
			}
		} else {
			Integer totalMotosIngresadas = this.parqueaderoServicio.verificarCupoVehiculo(EnumTipoVehiculo.MOTO.name());
			if (totalMotosIngresadas >= NUMERO_MAXIMO_CUPOS_MOTO) {
				throw new CupoExcedidoException(MSJ_NO_HAY_CUPOS_DISPONIBLES);
			}

		}

	}

	private void validarIngresoNoAutorizado(Vehiculo vehiculo) {
		if (vehiculo.getPlaca().toUpperCase().startsWith("A") && !calendarioVigilante.esDiaHabil()) {
			throw new AccesoRestringidoException(MSJ_NO_ESTA_AUTORIZADO_PARA_INGRESAR);
		}
	}

	private void validarVehiculoNoRegistrado(String placa) {
		if (parqueaderoServicio.verificarIngresoVehiculo(placa) == VEHICULO_NO_REGISTRADO) {
			throw new VehiculoNoRegistradoException(MSJ_EL_VEHICULO_NO_SE_ENCUENTRA_REGISTRADO);
		}
	}

	private void validarVehiculoRegistrado(Vehiculo vehiculo) {
		if (parqueaderoServicio.verificarIngresoVehiculo(vehiculo.getPlaca()) != VEHICULO_NO_REGISTRADO) {
			throw new VehiculoRegistradoException(MSJ_EL_VEHICULO_SE_ENCUENTRA_REGISTRADO);
		}
	}

	private void validarPlacaVehiculo(String placa) {
		if (vehiculoServicio.obtenerVehiculo(placa) == null) {
			throw new VehiculoNoEncontradoException(MSJ_EL_VEHICULO_NO_EXISTE);
		}
	}

	public boolean ingresarVehiculo(Vehiculo vehiculo) {

		validarCupoVehiculo(vehiculo);
		validarIngresoNoAutorizado(vehiculo);
		validarVehiculoRegistrado(vehiculo);
		if (vehiculoServicio.obtenerVehiculo(vehiculo.getPlaca()) == null) {
			vehiculoServicio.guardarVehiculo(vehiculo);
		}
		return parqueaderoServicio.crearTicketParqueadero(new TicketParqueadero(new Date(), vehiculo));
	}

	public TicketParqueadero retirarVehiculo(String placa) {

		validarPlacaVehiculo(placa);
		validarVehiculoNoRegistrado(placa);

		TicketParqueadero ticketParqueadero = parqueaderoServicio.obtenerTicketParquedero(placa);
		Date fechaSalida = new Date();
		ticketParqueadero.setFechaSalida(fechaSalida);
		ticketParqueadero.setValor(calcularValorAPagar(ticketParqueadero));
		ticketParqueadero = parqueaderoServicio.actualizarTicketParqueadero(ticketParqueadero);
		return ticketParqueadero;
	}

	private double calcularValorAPagar(TicketParqueadero ticketParqueadero) {
		double valor;
		int totalHorasTranscurridas = obtenerTotalHorasEntreDosFechas(ticketParqueadero.getFechaIngreso(),
				ticketParqueadero.getFechaSalida());
		int totalDias = totalHorasTranscurridas / TOTAL_HORAS_EN_UN_DIA;
		int totalHoras = totalHorasTranscurridas % TOTAL_HORAS_EN_UN_DIA;

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
