package com.example.Mi_Clinica.ServiceTest;

import com.example.Mi_Clinica.DTO.Request.PacienteRequestDto;
import com.example.Mi_Clinica.DTO.Response.PacienteResponseDto;
import com.example.Mi_Clinica.exception.ResourceNotFoundException;
import com.example.Mi_Clinica.model.Paciente;
import com.example.Mi_Clinica.repository.IPacienteRepository;
import com.example.Mi_Clinica.service.impl.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PacienteServiceTest {

    @Mock
    private IPacienteRepository pacienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarPaciente() {
        PacienteRequestDto requestDto = new PacienteRequestDto();
        requestDto.setNombre("Carlos");
        requestDto.setApellido("Sánchez");

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Carlos");
        paciente.setApellido("Sánchez");

        PacienteResponseDto responseDto = new PacienteResponseDto();
        responseDto.setId(1L);
        responseDto.setNombre("Carlos");
        responseDto.setApellido("Sánchez");

        when(modelMapper.map(requestDto, Paciente.class)).thenReturn(paciente);
        when(pacienteRepository.save(paciente)).thenReturn(paciente);
        when(modelMapper.map(paciente, PacienteResponseDto.class)).thenReturn(responseDto);

        PacienteResponseDto result = pacienteService.guardarPaciente(requestDto);

        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
        assertEquals("Sánchez", result.getApellido());
        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void testListarPacientes() {
        Paciente paciente1 = new Paciente();
        paciente1.setId(1L);
        paciente1.setNombre("Carlos");
        paciente1.setApellido("Sánchez");

        Paciente paciente2 = new Paciente();
        paciente2.setId(2L);
        paciente2.setNombre("Ana");
        paciente2.setApellido("Gómez");

        List<Paciente> pacientes = Arrays.asList(paciente1, paciente2);

        PacienteResponseDto responseDto1 = new PacienteResponseDto();
        responseDto1.setId(1L);
        responseDto1.setNombre("Carlos");
        responseDto1.setApellido("Sánchez");

        PacienteResponseDto responseDto2 = new PacienteResponseDto();
        responseDto2.setId(2L);
        responseDto2.setNombre("Ana");
        responseDto2.setApellido("Gómez");

        when(pacienteRepository.findAll()).thenReturn(pacientes);
        when(modelMapper.map(paciente1, PacienteResponseDto.class)).thenReturn(responseDto1);
        when(modelMapper.map(paciente2, PacienteResponseDto.class)).thenReturn(responseDto2);

        List<PacienteResponseDto> result = pacienteService.listarPacientes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Carlos", result.get(0).getNombre());
        assertEquals("Ana", result.get(1).getNombre());
        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Carlos");
        paciente.setApellido("Sánchez");

        PacienteResponseDto responseDto = new PacienteResponseDto();
        responseDto.setId(1L);
        responseDto.setNombre("Carlos");
        responseDto.setApellido("Sánchez");

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(modelMapper.map(paciente, PacienteResponseDto.class)).thenReturn(responseDto);

        PacienteResponseDto result = pacienteService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
        assertEquals("Sánchez", result.getApellido());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorIdNotFound() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        PacienteResponseDto result = pacienteService.buscarPorId(1L);

        assertNull(result);
        verify(pacienteRepository, times(1)).findById(1L);
    }



    @Test
    void testActualizarPacienteNotFound() {
        PacienteRequestDto requestDto = new PacienteRequestDto();
        requestDto.setNombre("Carlos");
        requestDto.setApellido("Sánchez");

        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> pacienteService.actualizarPaciente(requestDto, 1L));

        verify(pacienteRepository, times(1)).findById(1L);
        verify(pacienteRepository, never()).save(any(Paciente.class));
    }

    @Test
    void testEliminarPaciente() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        pacienteService.eliminarPaciente(1L);

        verify(pacienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testEliminarPacienteNotFound() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));

        verify(pacienteRepository, never()).deleteById(1L);
    }
}
