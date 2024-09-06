package com.example.Arevalo_Saibene_Nosicoski.service.impl;

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

import java.time.LocalDate;
import java.util.List;
@Service
public class TurnoService implements ITurnoService {


    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private ModelMapper modelMapper;
    private ITurnoRepository turnoRepository;
    private IPacienteService pacienteService;
    private IOdontologoService odontologoService;

    public TurnoService(IPacienteService pacienteService, IOdontologoService odontologoService, ITurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.modelMapper = modelMapper;
        this.turnoRepository = turnoRepository;
    }

    @Override
    public TurnoResponseDto registrarTurno(TurnoRequestDto turnoRequestDto) throws BadRequestException {

        OdontologoResponseDto odontologo = odontologoService.buscarOdontologo(turnoRequestDto.getOdontologo_id());
        PacienteResponseDto paciente = pacienteService.buscarPorId(turnoRequestDto.getPaciente_id().longValue());

        LOGGER.info("Registrando turno para el paciente: {} y el odontologo: {}", paciente, odontologo);

        Odontologo odontologoEntity = modelMapper.map(odontologo, Odontologo.class);
        Paciente pacienteEntity = modelMapper.map(paciente, Paciente.class);

        Turno turnoGuardado = turnoRepository.save(Turno.builder()
                .odontologo(odontologoEntity)
                .paciente(pacienteEntity)
                .fecha(LocalDate.parse(turnoRequestDto.getFecha()))
                .build());

        LOGGER.info("Turno guardado: {}", turnoGuardado);

        return modelMapper.map(turnoGuardado, TurnoResponseDto.class);
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

        return turnoRepository.findById(id).map(turno -> modelMapper.map(turno, TurnoResponseDto.class))
                .orElseGet(() -> {
                    LOGGER.info("No se encontró el turno con id: {}", id);
                    return null;
                });
    }

    @Override
    public void eliminarTurno(Integer id) throws ResourceNotFoundException {
        if (!turnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el turno para eliminar con el id: " + id);
        }
        turnoRepository.deleteById(id);
        LOGGER.info("turno eliminado con id: {}", id);

    }

    @Override
    public TurnoResponseDto actualizarTurno(TurnoRequestDto turnoEntradaDTO, Integer id) throws ResourceNotFoundException {
        return null;
    }
}
