package com.example.Arevalo_Saibene_Nosicoski.ServiceTest;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.DomicilioRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.PacienteRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.PacienteResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.model.Domicilio;
import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import com.example.Arevalo_Saibene_Nosicoski.repository.IPacienteRepository;
import com.example.Arevalo_Saibene_Nosicoski.service.impl.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest

class PacienteServiceImplTest {

    @Mock
    private IPacienteRepository pacienteRepository;

    @Spy
    ModelMapper modelMapper;

    @InjectMocks
    private PacienteService pacienteService;

    private PacienteRequestDto pacienteRequestDto;

    private Paciente paciente;

    @BeforeEach
    void setUp() {
        pacienteRequestDto = PacienteRequestDto.builder()
                .nombre("nicolas")
                .apellido("sanchez")
                .dni(12345)
                .domicilioEntradaDTO(
                        DomicilioRequestDto.builder()
                                .calle("calle")
                                .localidad("localidad")
                                .numero(1234)
                                .provincia("provincia")
                                .build())
                .build();

        paciente = Paciente.builder()
                .id(1L)
                .nombre("nicolas")
                .apellido("sanchez")
                .dni("12345")
                .fechaIngreso(LocalDate.of(2027, 12, 27))
                .domicilio(Domicilio.builder()
                        .id(1)
                        .calle("Avenida Libertador")
                        .numero(123)
                        .provincia("Canelones")
                        .localidad("Pando city")
                        .build())
                .build();
    }



    @Test
    void deberiaGuardarUnPaciente_YRetornarUnPacienteConSuId() {
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);
        PacienteResponseDto pacienteGuardado = pacienteService.guardarPaciente(pacienteRequestDto);

        verify(pacienteRepository, times(1)).save(any(Paciente.class));

        assertNotNull(pacienteGuardado.getId());
    }


    @Test
    void deberiaBuscarUnPacientePorId_YRetornarElPacienteEncontrado() {
        when(pacienteRepository.findById(1)).thenReturn(Optional.of(paciente));
        PacienteResponseDto pacienteBuscado = pacienteService.buscarPorId(1L);

        verify(pacienteRepository, times(1)).findById(1);

        assertNotNull(pacienteBuscado);
        assertEquals(paciente.getId(), pacienteBuscado.getId());
    }

    @Test
    void deberiaBuscarUnPacientePorId_YRetornarNull() {
        when(pacienteRepository.findById((int)).thenReturn(Optional.empty());
        PacienteResponseDto pacienteBuscado = pacienteService.buscarPorId(1L);

        verify(pacienteRepository, times(1)).findById(12);

        assertNull(pacienteBuscado);
    }

    @Test
    void deberiaBuscarUnaListaDePacientes_YRetornarUnaListaConElementos() {
        when(pacienteRepository.findAll()).thenReturn(List.of(paciente));
        List<PacienteResponseDto> pacientes = pacienteService.listarPacientes();

        verify(pacienteRepository, times(1)).findAll();

        assertFalse(pacientes.isEmpty());
        assertEquals(1, pacientes.size());
        assertEquals(paciente.getId(), pacientes.get(0).getId());
    }

    @Test
    void deberiaBuscarUnaListaDePacientes_YRetornarUnaListaVacia() {
        when(pacienteRepository.findAll()).thenReturn(List.of());
        List<PacienteResponseDto> pacientes = pacienteService.listarPacientes();

        verify(pacienteRepository, times(1)).findAll();

        assertTrue(pacientes.isEmpty());
    }

    @Test
    void deberiaIntentarEliminarUnPacienteInexistente_YLanzarExcepcionConMensaje() {
        when(pacienteRepository.existsById(1)).thenReturn(false);

        ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () -> {
            pacienteService.eliminarPaciente(1L);
        });

        verify(pacienteRepository, times(1)).existsById(2);
        verify(pacienteRepository, never()).deleteById((int) anyLong());

        assertEquals("No se encontró el paciente a eliminar con id: 1", resourceNotFoundException.getMessage());
    }

    @Test
    void deberiaEliminarUnPaciente_YNoRetornarNada() throws ResourceNotFoundException {
        when(pacienteRepository.existsById(1)).thenReturn(true);
        pacienteService.eliminarPaciente(1L);

        verify(pacienteRepository, times(1)).existsById(1);
        verify(pacienteRepository, times(1)).deleteById(1);
    }

    @Test
    void deberiaActualizarUnPacienteExistente_YRetornarElPacienteActualizado() throws ResourceNotFoundException {
        PacienteRequestDto pacienteEntradaActualizar =  PacienteRequestDto.builder()
                .nombre("nuevoNombre")
                .apellido("nuevoApellido")
                .dni(12322)
                .domicilioEntradaDTO(
                        DomicilioRequestDto.builder()
                                .calle("nuevaCalle")
                                .localidad("nuevaLocalidad")
                                .numero(12345)
                                .provincia("nuevaProvincia")
                                .build()).build();


        Paciente crearPacienteActualizado =  Paciente.builder()
                .id(1L)
                .nombre("nuevoNombre")
                .apellido("nuevoApellido")
                .dni("12322")
                .fechaIngreso(LocalDate.of(2027,12,27))
                .domicilio(Domicilio.builder()
                        .id(1)
                        .calle("nuevaCalle")
                        .numero(12345)
                        .provincia("nuevaProvincia")
                        .localidad("nuevaLocalidad")
                        .build())
                .build();

        when(pacienteRepository.findById(1)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(crearPacienteActualizado);

        PacienteResponseDto pacienteActualizado = pacienteService.actualizarPaciente(pacienteEntradaActualizar,1L);

        verify(pacienteRepository, times(1)).save(any(Paciente.class));
        verify(pacienteRepository,times(1)).findById(1);

        assertNotNull(pacienteActualizado);
        assertEquals(paciente.getId(),pacienteActualizado.getId());
        assertEquals(paciente.getDomicilio().getId(),pacienteActualizado.getDomicilioResponseDto().getId());
        assertNotEquals(paciente.getNombre(),pacienteActualizado.getNombre());
        assertNotEquals(paciente.getApellido(),pacienteActualizado.getApellido());
        assertNotEquals(paciente.getDni(),pacienteActualizado.getDni());
        assertNotEquals(paciente.getDomicilio().getCalle(),pacienteActualizado.getDomicilioResponseDto().getCalle());
        assertNotEquals(paciente.getDomicilio().getNumero(),pacienteActualizado.getDomicilioResponseDto().getNumero());
        assertNotEquals(paciente.getDomicilio().getProvincia(),pacienteActualizado.getDomicilioResponseDto().getProvincia());
        assertNotEquals(paciente.getDomicilio().getLocalidad(),pacienteActualizado.getDomicilioResponseDto().getLocalidad());
    }

    @Test
    void deberiaIntentarActualizarUnPacienteInexistente_YLanzarExcepcionConMensaje() {
        PacienteRequestDto pacienteEntradaActualizar = pacienteRequestDto;

        when(pacienteRepository.findById(1)).thenReturn(Optional.empty());

        ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () -> {
            pacienteService.actualizarPaciente(pacienteEntradaActualizar, 1L);
        });

        verify(pacienteRepository, times(1)).findById(1);
        verify(pacienteRepository, never()).save(any(Paciente.class));

        assertEquals("No se encontró el paciente a actualizar con id: 1", resourceNotFoundException.getMessage());
    }

}