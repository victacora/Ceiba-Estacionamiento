package co.com.ceiba.estacionamiento.servicios;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.ceiba.estacionamiento.dominio.servicios.ITarifaServicio;
import co.com.ceiba.estacionamiento.persistencia.entidades.TarifaEntity;
import co.com.ceiba.estacionamiento.persistencia.entidades.TarifaId;
import co.com.ceiba.estacionamiento.persistencia.repositorio.ITarifaRepositorio;

@Service
public class TarifaServicio implements ITarifaServicio {

	@Autowired
	private ITarifaRepositorio tarifaRepositorio;


	@Override
	public double obtenerValorTarifa(String tipoVehiculo, String tipoTarifa, String unidadTiempo) {
		Optional<TarifaEntity> tarifa=tarifaRepositorio.findById( new TarifaId(tipoVehiculo,tipoTarifa,unidadTiempo));
		return (tarifa.isPresent() ? tarifa.get().getValor() : 0.0);
	
	}

}
