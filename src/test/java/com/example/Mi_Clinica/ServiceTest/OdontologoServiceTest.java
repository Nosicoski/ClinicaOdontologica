package com.example.Mi_Clinica.ServiceTest;

import com.example.Mi_Clinica.DTO.Request.OdontologoRequestDto;
import com.example.Mi_Clinica.DTO.Response.OdontologoResponseDto;
import com.example.Mi_Clinica.exception.ResourceNotFoundException;
import com.example.Mi_Clinica.model.Odontologo;
import com.example.Mi_Clinica.repository.IOdontologoRepository;

import com.example.Mi_Clinica.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OdontologoServiceTest {

    @Mock
    private IOdontologoRepository odontologoRepository;

    @InjectMocks
    private OdontologoService odontologoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarOdontologo() {
        OdontologoRequestDto requestDto = new OdontologoRequestDto();
        requestDto.setNombre("Juan");
        requestDto.setApellido("Pérez");

        Odontologo odontologo = new Odontologo();
        odontologo.setId(1);
        odontologo.setNombre("Juan");
        odontologo.setApellido("Pérez");

        when(odontologoRepository.save(any(Odontologo.class))).thenReturn(odontologo);

        OdontologoResponseDto responseDto = odontologoService.guardarOdontologo(requestDto);

        assertNotNull(responseDto);
        assertEquals(1, responseDto.getId());
        assertEquals("Juan", responseDto.getNombre());
        assertEquals("Pérez", responseDto.getApellido());
        verify(odontologoRepository, times(1)).save(any(Odontologo.class));
    }


    @Test
    public void testEliminarOdontologo_NotFound() {
        when(odontologoRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            odontologoService.eliminarOdontologoDto(1);
        });
    }

    @Test
    public void testListarTodos() {
        Odontologo odontologo1 = new Odontologo();
        odontologo1.setId(1);
        odontologo1.setNombre("Juan");
        odontologo1.setApellido("Pérez");

        Odontologo odontologo2 = new Odontologo();
        odontologo2.setId(2);
        odontologo2.setNombre("Ana");
        odontologo2.setApellido("Gómez");

        List<Odontologo> odontologos = Arrays.asList(odontologo1, odontologo2);

        when(odontologoRepository.findAll()).thenReturn(odontologos);

        List<OdontologoResponseDto> responseList = odontologoService.listarTodos();

        assertNotNull(responseList);
        assertEquals(2, responseList.size());
        assertEquals(1, responseList.get(0).getId());
        assertEquals("Juan", responseList.get(0).getNombre());
        assertEquals("Ana", responseList.get(1).getNombre());
        verify(odontologoRepository, times(1)).findAll();
    }
}
