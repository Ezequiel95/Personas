package Test;



import com.example.demo.controller.PersonaController;
import com.example.demo.controller.dto.PersonaDTO;
import com.example.demo.service.PersonaServicelmpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(PersonaController.class)
public class PersonaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    // Usamos la implementación del servicio PersonaServicelmpl
    @Mock
    private PersonaServicelmpl personaService;

    @InjectMocks
    private PersonaController personaController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    // 1. Prueba GET /personas (obtener todas las personas)
    @Test
    public void testGetAllPersonas() throws Exception {
        // Crear datos de prueba
        PersonaDTO persona1 = new PersonaDTO(1L, "Ezequiel", "Hernandez Zeferino", "29", "Masculino", "Activo");
        PersonaDTO persona2 = new PersonaDTO(2L, "Karen Elizabeth", "Garcia Lopez", "28", "Femenina", "Activo");

        List<PersonaDTO> personas = Arrays.asList(persona1, persona2);

        // Mock del servicio
        when(personaService.getAllPersonas()).thenReturn((AggregationExpression) personas);

        // Realizar la solicitud GET
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/personas")
                .contentType(MediaType.APPLICATION_JSON));

        // Verificar el resultado
        result.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.size()", is(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].nombres", is(Integer.parseInt("Ezequiel"))))
                .andExpect((ResultMatcher) jsonPath("$[1].nombres", is(Integer.parseInt("Karen Elizabeth"))));
    }

    // 2. Prueba GET /personas/{id} (obtener una persona específica por ID)
    @Test
    public void testGetPersonaById() throws Exception {
        PersonaDTO persona = new PersonaDTO(1L, "Ezequiel", "Hernandez Zeferino", "29", "Masculino", "Activo");

        // Mock del servicio
        when(personaService.getPersona(1L)).thenReturn(persona);

        // Realizar la solicitud GET
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/personas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        // Verificar el resultado
        result.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.nombres", is(Integer.parseInt("Ezequiel"))))
                .andExpect((ResultMatcher) jsonPath("$.apellidos", is(Integer.parseInt("Hernandez Zeferino"))));
    }

    // 3. Prueba POST /personas (crear una nueva persona)
    @Test
    public void testCreatePersona() throws Exception {
        PersonaDTO persona = new PersonaDTO(1L, "Ezequiel", "Hernandez Zeferino", "29", "Masculino", "Activo");

        // Mock del servicio
        when(personaService.setPersona(any(PersonaDTO.class))).thenReturn(persona);

        // Realizar la solicitud POST
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/personas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(persona)));

        // Verificar el resultado
        result.andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.nombres", is(Integer.parseInt("Ezequiel"))))
                .andExpect((ResultMatcher) jsonPath("$.status", is(Integer.parseInt("Activo"))));
    }

    // 4. Prueba PUT /personas (actualizar los datos de una persona)
    @Test
    public void testUpdatePersona() throws Exception {
        PersonaDTO updatedPersona = new PersonaDTO(1L, "Ezequiel", "Hernandez Zeferino", "30", "Masculino", "Activo");

        // Mock del servicio
        when(personaService.setPersona(any(PersonaDTO.class))).thenReturn(updatedPersona);

        // Realizar la solicitud PUT
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/personas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPersona)));

        // Verificar el resultado
        result.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.edad", is(30)))
                .andExpect((ResultMatcher) jsonPath("$.nombres", is(Integer.parseInt("Ezequiel"))))
                .andExpect((ResultMatcher) jsonPath("$.apellidos", is(Integer.parseInt("Hernandez Zeferino"))));
    }

    // 5. Prueba DELETE /personas/{id} (eliminar una persona)
    @Test
    public void testDeletePersona() throws Exception {
        // Mock del servicio
        doNothing().when(personaService).deletePersona(1L);

        // Realizar la solicitud DELETE
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/personas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        // Verificar el resultado
        result.andExpect(status().isNoContent());
    }
}