package co.com.ceiba.estacionamiento.persistencia.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class TarifaId implements Serializable{
	@Column(name="tipo_vehiculo",length=5)
	private String tipoVehiculo;
	
	@Column(name="tipo_tarifa",length=9)
	private String tipoTarifa;
	
	@Column(name="unidad_tiempo",length=8)
	private String unidadTiempo;
	
	
	public TarifaId(String tipoVehiculo, String tipoTarifa, String unidadTiempo) {
		this.tipoVehiculo = tipoVehiculo;
		this.tipoTarifa = tipoTarifa;
		this.unidadTiempo = unidadTiempo;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getTipoTarifa() {
		return tipoTarifa;
	}

	public void setTipoTarifa(String tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}

	public String getUnidadTiempo() {
		return unidadTiempo;
	}

	public void setUnidadTiempo(String unidadTiempo) {
		this.unidadTiempo = unidadTiempo;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TarifaId)) return false;
        TarifaId that = (TarifaId) o;
        return Objects.equals(getTipoVehiculo(), that.getTipoVehiculo()) &&
                Objects.equals(getTipoTarifa(), that.getTipoTarifa())&&
                Objects.equals(getUnidadTiempo(), that.getUnidadTiempo());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getTipoVehiculo(), getTipoTarifa(), getUnidadTiempo());
    }
}
