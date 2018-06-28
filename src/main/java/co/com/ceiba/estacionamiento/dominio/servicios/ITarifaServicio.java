package co.com.ceiba.estacionamiento.dominio.servicios;

public interface ITarifaServicio {
	public double obtenerValorTarifa(String tipoVehiculo, String tipoTarifa, String unidadTiempo);
}
