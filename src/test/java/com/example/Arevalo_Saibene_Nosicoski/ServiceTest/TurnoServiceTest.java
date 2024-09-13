package com.example.Arevalo_Saibene_Nosicoski.ServiceTest;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.TurnoResponseDto;

import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;

import com.example.Arevalo_Saibene_Nosicoski.repository.ITurnoRepository;
import com.example.Arevalo_Saibene_Nosicoski.service.IOdontologoService;
import com.example.Arevalo_Saibene_Nosicoski.service.IPacienteService;
import com.example.Arevalo_Saibene_Nosicoski.service.ITurnoService;
import com.example.Arevalo_Saibene_Nosicoski.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.modelmapper.ModelMapper;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TurnoServiceTest {

    private ITurnoRepository turnoRepository;
    private IPacienteService pacienteService;
    private IOdontologoService odontologoService;
    private ModelMapper modelMapper;
    private ITurnoService turnoService;

    @BeforeEach
    public void setUp() {
        turnoRepository = mock(ITurnoRepository.class);
        pacienteService = mock(IPacienteService.class);
        odontologoService = mock(IOdontologoService.class);
        modelMapper = new ModelMapper();
        turnoService = new TurnoService(pacienteService, odontologoService, turnoRepository, modelMapper);
    }


    @Test
    public void testBuscarTurnoPorIdNoEncontrado() {
        Integer id = 1;
        when(turnoRepository.findById(id)).thenReturn(Optional.empty());

        TurnoResponseDto result = turnoService.buscarTurnoPorId(id);

        assertNull(result);
        verify(turnoRepository).findById(id);
    }

    @Test
    public void testEliminarTurno() throws ResourceNotFoundException {
        Integer id = 1;
        when(turnoRepository.existsById(id)).thenReturn(true);

        turnoService.eliminarTurno(id);

        verify(turnoRepository).deleteById(id);
    }

    @Test
    public void testEliminarTurnoNoEncontrado() {
        Integer id = 1;
        when(turnoRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            turnoService.eliminarTurno(id);
        });

        String expectedMessage = "No se encontró el turno para eliminar con el id: " + id;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
