package co.com.ceiba.estacionamiento.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.servicios.VehiculoServicio;
import co.com.ceiba.estacionamiento.persistencia.builders.VehiculoBuilder;
import co.com.ceiba.estacionamiento.persistencia.entidades.VehiculoEntity;
import co.com.ceiba.estacionamiento.persistencia.repositorio.VehiculoRepositorio;

@Service
public class VehiculoServicioImpl implements VehiculoServicio {

	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;

	@Override
	public boolean guardarVehiculo(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = VehiculoBuilder.convertirAEntity(vehiculo);
		return vehiculoRepositorio.save(vehiculoEntity) != null;
	}

	@Override
	public Vehiculo obtenerVehiculo(String placa) {
		Optional<VehiculoEntity> vehiculoEntity = vehiculoRepositorio.findById(placa);
		return (vehiculoEntity.isPresent() ? VehiculoBuilder.convertirADominio(vehiculoEntity.get()) : null);
	}

}
