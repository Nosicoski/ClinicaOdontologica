package com.example.Arevalo_Saibene_Nosicoski.ControllerTest;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.TurnoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.TurnoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.Controller.ControladorDeTurnos;
import com.example.Arevalo_Saibene_Nosicoski.service.ITurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.Mockito.when;

@WebMvcTest(ControladorDeTurnos.class)
public class TurnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ITurnoService turnoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistrarTurnoValido() throws Exception {
        TurnoResponseDto mockTurno = new TurnoResponseDto();
        mockTurno.setId(1L); // Asigna valores de prueba
        mockTurno.setFecha("2024-09-10");

        Mockito.when(turnoService.registrarTurno(Mockito.any(TurnoRequestDto.class))).thenReturn(mockTurno);

        mockMvc.perform(MockMvcRequestBuilders.post("/Turno/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"odontologo_id\":1,\"paciente_id\":1,\"fecha\":\"2024-09-10\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.fecha").value("2024-09-10"));
    }



    @Test
    public void testRegistrarTurnoInvalido() throws Exception {
        // Configura el objeto de prueba con datos inválidos
        TurnoRequestDto requestDto = new TurnoRequestDto();
        // (Optional) Añade valores inválidos o deja los campos requeridos vacíos
        // e.g., requestDto.setFecha(""); // If fecha is required

        // Configura el objeto ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Convierte el objeto a JSON
        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/turnos/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()) // Helps with debugging by printing the request and response
                .andExpect(status().isBadRequest()); // Asserts that the response status is 400 Bad Request
    }


}