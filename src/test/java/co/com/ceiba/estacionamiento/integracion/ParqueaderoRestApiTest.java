package co.com.ceiba.estacionamiento.integracion;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import co.com.ceiba.estacionamiento.EstacionamientoApplication;
import co.com.ceiba.estacionamiento.configuracion.H2JpaConfig;
import co.com.ceiba.estacionamiento.dominio.dto.VehiculoDTO;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoVehiculo;
import co.com.ceiba.estacionamiento.persistencia.repositorio.TicketParqueaderoRepositorio;
import co.com.ceiba.estacionamiento.testdatabuilder.VehiculoDTOTestDataBuilder;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EstacionamientoApplication.class, H2JpaConfig.class })
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParqueaderoRestApiTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TicketParqueaderoRepositorio parqueaderoSevicio;

	@Test
	public void verificarConsultarVehiculosParqueadero() throws Exception {

		Integer total = parqueaderoSevicio.contarVehiculosParqueadero();

		mvc.perform(
				get("/parqueadero/listadovehiculos?pagina=0&tamano=10&dirOrdenamiento=ASC&campoOrdenamiento=fecha_ingreso")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.total", is(total)));
	}

	@Test
	public void ingresarVehiculoParqueadero() throws Exception {

		VehiculoDTO vehiculoDTO = new VehiculoDTOTestDataBuilder().withPlaca("CXX-000").withCilindraje(10)
				.withTipoVehiculo(EnumTipoVehiculo.MOTO.name()).build();

		String vehiculoDTOJson = json(vehiculoDTO);

		mvc.perform(
				post("/parqueadero/ingresarvehiculo").contentType(MediaType.APPLICATION_JSON).content(vehiculoDTOJson))
				.andExpect(status().isOk());

	}

	@Test
	public void retirarVehiculoParqueadero() throws Exception {
		String placa = "CXX-000";
		mvc.perform(post("/parqueadero/retirarvehiculo").contentType(MediaType.TEXT_PLAIN).content(placa))
				.andExpect(status().isOk());
	}

	public static String json(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
