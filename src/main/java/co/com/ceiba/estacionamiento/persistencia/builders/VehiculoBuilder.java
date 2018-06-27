package co.com.ceiba.estacionamiento.persistencia.builders;

import co.com.ceiba.estacionamiento.dominio.Moto;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.fabrica.VehiculoFactory;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoVehiculo;
import co.com.ceiba.estacionamiento.persistencia.entidades.VehiculoEntity;

public class VehiculoBuilder {

	private VehiculoBuilder() {
		throw new IllegalStateException("Clase utilidad");
	}

	public static Vehiculo convertirADominio(VehiculoEntity vehiculoEntity) {
		Vehiculo vehiculo = null;
		if (vehiculoEntity != null) {
			VehiculoFactory vehiculoFactory = new VehiculoFactory();
			vehiculo = vehiculoFactory.crearVehiculo(vehiculoEntity.getTipoVehiculo(), vehiculoEntity.getPlaca(),
					vehiculoEntity.getCilindraje());
		}
		return vehiculo;
	}

	public static VehiculoEntity convertirAEntity(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		if (vehiculo != null) {
			vehiculoEntity = new VehiculoEntity();
			vehiculoEntity.setPlaca(vehiculo.getPlaca());
			if (vehiculo instanceof Moto) {
				vehiculoEntity.setCilindraje(((Moto) vehiculo).getCilindraje());
				vehiculoEntity.setTipoVehiculo(EnumTipoVehiculo.MOTO.name());
			} else {
				vehiculoEntity.setCilindraje(0);
				vehiculoEntity.setTipoVehiculo(EnumTipoVehiculo.CARRO.name());
			}
		}
		return vehiculoEntity;
	}
}
