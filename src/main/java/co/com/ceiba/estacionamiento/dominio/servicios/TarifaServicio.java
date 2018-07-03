package co.com.ceiba.estacionamiento.dominio.servicios;

public interface TarifaServicio 
{
	public double obtenerValorTarifa(String tipoVehiculo, String tipoTarifa, String unidadTiempo);
}
