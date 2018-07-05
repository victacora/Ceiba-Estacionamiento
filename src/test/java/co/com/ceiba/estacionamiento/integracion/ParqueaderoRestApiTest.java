package co.com.ceiba.estacionamiento.integracion;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.com.ceiba.estacionamiento.EstacionamientoApplication;
import co.com.ceiba.estacionamiento.api.ParqueaderoController;
import co.com.ceiba.estacionamiento.dominio.dto.TicketParqueaderoDTO;
import co.com.ceiba.estacionamiento.dominio.servicios.ParqueaderoServicio;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoVehiculo;
import co.com.ceiba.estacionamiento.persistencia.builders.TicketParqueaderoDTOBuilder;
import co.com.ceiba.estacionamiento.persistencia.entidades.TicketParqueaderoEntity;
import co.com.ceiba.estacionamiento.persistencia.entidades.VehiculoEntity;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { EstacionamientoApplication.class })
@WebMvcTest(ParqueaderoController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParqueaderoRestApiTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ParqueaderoServicio parqueaderoSevicio;

	@Test
	public void consultarTodosLosTicketParqueadero() throws Exception {

		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setPlaca("CXX-000");
		vehiculoEntity.setCilindraje(10);
		vehiculoEntity.setTipoVehiculo(EnumTipoVehiculo.CARRO.name());
		TicketParqueaderoEntity ticketParqueaderoEntity = new TicketParqueaderoEntity();
		ticketParqueaderoEntity.setId(1l);
		ticketParqueaderoEntity.setValor(1000);
		ticketParqueaderoEntity.setFechaIngreso(new Date());
		ticketParqueaderoEntity.setFechaSalida(new Date());
		ticketParqueaderoEntity.setVehiculo(vehiculoEntity);

		TicketParqueaderoDTO ticketParqueaderoDTO = TicketParqueaderoDTOBuilder.convertirADTO(ticketParqueaderoEntity);

		List<TicketParqueaderoDTO> ticketParqueaderos = Arrays.asList(ticketParqueaderoDTO);

		given(parqueaderoSevicio.listarTicketsParqueadero(0, 10, "ASC", "fechaIngreso")).willReturn(ticketParqueaderos);

		mvc.perform(
				get("/parqueadero/ticketsparqueadero?pagina=0&tamano=10&dirOrdenamiento=ASC&campoOrdenamiento=fechaIngreso")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)));
	}

}
