package com.example.Arevalo_Saibene_Nosicoski.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.TurnoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.OdontologoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.PacienteResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.TurnoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.BadRequestException;
import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import com.example.Arevalo_Saibene_Nosicoski.model.Turno;
import com.example.Arevalo_Saibene_Nosicoski.repository.ITurnoRepository;
import com.example.Arevalo_Saibene_Nosicoski.service.IOdontologoService;
import com.example.Arevalo_Saibene_Nosicoski.service.IPacienteService;
import com.example.Arevalo_Saibene_Nosicoski.service.ITurnoService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final ModelMapper modelMapper;
    private final ITurnoRepository turnoRepository;
    private final IPacienteService pacienteService;
    private final IOdontologoService odontologoService;

    public TurnoService(IPacienteService pacienteService, IOdontologoService odontologoService, ITurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.modelMapper = modelMapper;
        this.turnoRepository = turnoRepository;
    }

    @Transactional
    @Override
    public TurnoResponseDto registrarTurno(TurnoRequestDto turnoRequestDto) throws BadRequestException {
        try {
            if (turnoRequestDto == null) {
                throw new BadRequestException("El DTO del turno no puede ser nulo.");
            }
            if (turnoRequestDto.getOdontologo_id() == null || turnoRequestDto.getPaciente_id() == null || turnoRequestDto.getFecha() == null) {
                throw new BadRequestException("Faltan datos necesarios en el DTO.");
            }

            OdontologoResponseDto odontologo = odontologoService.buscarOdontologo(turnoRequestDto.getOdontologo_id());
            PacienteResponseDto paciente = pacienteService.buscarPorId(turnoRequestDto.getPaciente_id().longValue());

            if (odontologo == null) {
                throw new BadRequestException("Odontólogo no encontrado con id: " + turnoRequestDto.getOdontologo_id());
            }
            if (paciente == null) {
                throw new BadRequestException("Paciente no encontrado con id: " + turnoRequestDto.getPaciente_id());
            }

            LOGGER.info("Registrando turno para el paciente: {} y el odontologo: {}", paciente, odontologo);

            Odontologo odontologoEntity = modelMapper.map(odontologo, Odontologo.class);
            Paciente pacienteEntity = modelMapper.map(paciente, Paciente.class);

            LocalDate fecha;
            try {
                fecha = LocalDate.parse(turnoRequestDto.getFecha());
            } catch (DateTimeParseException e) {
                throw new BadRequestException("Formato de fecha inválido: " + turnoRequestDto.getFecha(), e);
            }

            Turno turnoGuardado = turnoRepository.save(Turno.builder()
                    .odontologo(odontologoEntity)
                    .paciente(pacienteEntity)
                    .fecha(fecha)
                    .build());

            LOGGER.info("Turno guardado: {}", turnoGuardado);

            return modelMapper.map(turnoGuardado, TurnoResponseDto.class);
        } catch (Exception e) {
            LOGGER.error("Error al registrar el turno: ", e);
            throw new BadRequestException("Error al registrar el turno: " + e.getMessage(), e);
        }
    }


    @Override
    public List<TurnoResponseDto> listarTurnos() {
        return turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoResponseDto.class))
                .toList();
    }

    @Override
    public TurnoResponseDto buscarTurnoPorId(Integer id) {
        return turnoRepository.findById(id)
                .map(turno -> modelMapper.map(turno, TurnoResponseDto.class))
                .orElseGet(() -> {
                    LOGGER.info("No se encontró el turno con id: {}", id);
                    return null;
                });
    }

    @Transactional
    @Override
    public void eliminarTurno(Integer id) throws ResourceNotFoundException {
        if (!turnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el turno para eliminar con el id: " + id);
        }
        turnoRepository.deleteById(id);
        LOGGER.info("Turno eliminado con id: {}", id);
    }

    @Override
    public TurnoResponseDto actualizarTurno(TurnoRequestDto turnoRequestDto, Integer id) throws ResourceNotFoundException {
        // Implementar la lógica de actualización aquí
        return null;
    }
}
