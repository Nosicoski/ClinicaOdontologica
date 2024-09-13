package com.example.Arevalo_Saibene_Nosicoski.ControllerTest;

import com.example.Arevalo_Saibene_Nosicoski.Controller.ControladorDeOdontologo;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.OdontologoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.OdontologoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

@WebMvcTest(ControladorDeOdontologo.class)
public class OdontologoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOdontologoService odontologoService;

    @Test
    public void testGuardarOdontologo() throws Exception {
        OdontologoRequestDto requestDto = OdontologoRequestDto.builder()
                .apellido("Gomez")
                .nombre("Juan")
                .nroMatricula("12345")
                .domicilio("Calle Falsa 123")
                .build();

        OdontologoResponseDto responseDto = OdontologoResponseDto.builder()
                .id(1)
                .nroMatricula("12345")
                .apellido("Gomez")
                .nombre("Juan")
                .build(); // No se incluye domicilio en el ResponseDto

        when(odontologoService.guardarOdontologo(any(OdontologoRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/Odontologo/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"apellido\":\"Gomez\",\"nombre\":\"Juan\",\"nroMatricula\":\"12345\",\"domicilio\":\"Calle Falsa 123\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nroMatricula").value("12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Gomez"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Juan"));
    }


    @Test
    public void testBuscarOdontologoPorId() throws Exception {
        OdontologoResponseDto responseDto = OdontologoResponseDto.builder()
                .id(1)
                .nroMatricula("12345")
                .apellido("Gomez")
                .nombre("Juan")
                .build();

        when(odontologoService.buscarOdontologo(anyInt())).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/Odontologo/buscar/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nroMatricula").value("12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Gomez"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Juan"));
    }


    @Test
    public void testEliminarOdontologo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/Odontologo/eliminar/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
