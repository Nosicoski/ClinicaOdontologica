package com.example.Mi_Clinica.ControllerTest;

import com.example.Mi_Clinica.Controller.ControladorDePaciente;
import com.example.Mi_Clinica.DTO.Request.PacienteRequestDto;
import com.example.Mi_Clinica.DTO.Response.DomicilioResponseDto;
import com.example.Mi_Clinica.DTO.Response.PacienteResponseDto;
import com.example.Mi_Clinica.service.impl.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ControladorDePaciente.class)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteService pacienteService;

    @Test
    void testGuardarPaciente() throws Exception {
        PacienteRequestDto requestDto = new PacienteRequestDto();
        requestDto.setNombre("Juan");
        requestDto.setApellido("Pérez");
        requestDto.setDni(12345678);
        requestDto.setFechaIngreso(LocalDate.now());

        DomicilioResponseDto domicilioResponseDto = new DomicilioResponseDto();
        // Configura domicilioResponseDto si es necesario

        PacienteResponseDto responseDto = PacienteResponseDto.builder()
                .id(1L)
                .nombre("Juan")
                .apellido("Pérez")
                .dni(12345678)
                .fechaIngreso(LocalDate.now())
                .domicilio(domicilioResponseDto)
                .build();

        when(pacienteService.guardarPaciente(any(PacienteRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/paciente/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Juan\",\"apellido\":\"Pérez\",\"dni\":12345678,\"fechaIngreso\":\"2024-09-10\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Pérez"))
                .andExpect(jsonPath("$.dni").value(12345678))
                .andExpect(jsonPath("$.fechaIngreso").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.domicilio").exists()); // Verifica que domicilio no sea nulo
    }

    @Test
    void testBuscarPorId() throws Exception {
        PacienteResponseDto responseDto = PacienteResponseDto.builder()
                .id(1L)
                .nombre("Juan")
                .apellido("Pérez")
                .dni(12345678)
                .fechaIngreso(LocalDate.now())
                .domicilio(null)
                .build();

        when(pacienteService.buscarPorId(1L)).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/buscar/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Pérez"));
    }

    @Test
    void testBuscarTodos() throws Exception {
        PacienteResponseDto responseDto = PacienteResponseDto.builder()
                .id(1L)
                .nombre("Juan")
                .apellido("Pérez")
                .dni(12345678)
                .fechaIngreso(LocalDate.now())
                .domicilio(null)
                .build();

        when(pacienteService.listarPacientes()).thenReturn(Collections.singletonList(responseDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/buscartodos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].apellido").value("Pérez"));
    }




    @Test
    void testEliminarPaciente() throws Exception {
        doNothing().when(pacienteService).eliminarPaciente(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/paciente/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Paciente eliminado con éxito"));

        verify(pacienteService, times(1)).eliminarPaciente(1L);
    }
}