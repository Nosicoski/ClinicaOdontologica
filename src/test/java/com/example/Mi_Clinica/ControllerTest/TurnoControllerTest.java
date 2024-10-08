package com.example.Mi_Clinica.ControllerTest;

import com.example.Mi_Clinica.DTO.Request.TurnoRequestDto;
import com.example.Mi_Clinica.DTO.Response.TurnoResponseDto;
import com.example.Mi_Clinica.Controller.ControladorDeTurnos;
import com.example.Mi_Clinica.service.ITurnoService;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
        String json = "{ \"odontologo_id\": null, \"paciente_id\": null, \"fecha\": null }";

        mockMvc.perform(post("/Turno/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testListarTurnos() throws Exception {
        mockMvc.perform(get("/Turno/listar"))
                .andExpect(status().isOk());
    }

    @Test
    public void testBuscarTurnoPorId() throws Exception {
        mockMvc.perform(get("/Turno/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testEliminarTurno() throws Exception {
        mockMvc.perform(delete("/Turno/1"))
                .andExpect(status().isNoContent());
    }

   


}