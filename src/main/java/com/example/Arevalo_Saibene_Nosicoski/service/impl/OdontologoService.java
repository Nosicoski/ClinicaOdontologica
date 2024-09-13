package com.example.Arevalo_Saibene_Nosicoski.service.impl;

import java.util.List;


import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.OdontologoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.OdontologoResponseDto;

import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import com.example.Arevalo_Saibene_Nosicoski.repository.IOdontologoRepository;
import com.example.Arevalo_Saibene_Nosicoski.service.IOdontologoService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

import static org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties.UiService.LOGGER;

@Service

public class OdontologoService implements IOdontologoService {
    private ModelMapper modelMapper;
    private final IOdontologoRepository iOdontologoRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    public OdontologoService(ModelMapper modelMapper, IOdontologoRepository iOdontologoRepository) {
        this.modelMapper = modelMapper;
        this.iOdontologoRepository = iOdontologoRepository;

    }


    @Override
    public void eliminarOdontologoDto(Integer id) {
        if (iOdontologoRepository.findById(id).isEmpty()) throw new ResourceNotFoundException("No se encontró el paciente a eliminar con id: " + id);
        iOdontologoRepository.deleteById(id);
        LOGGER.info("Odontologo eliminado con id:" + id);
    }


    @Override
    public OdontologoResponseDto guardarOdontologo(OdontologoRequestDto requestDto) {
        Odontologo odontologo = new Odontologo();
        odontologo.setApellido(requestDto.getApellido());
        odontologo.setNombre(requestDto.getNombre());
        odontologo.setNroMatricula(requestDto.getNroMatricula());
        Odontologo odontologoGuardado = iOdontologoRepository.save(odontologo);
        return new OdontologoResponseDto(
                odontologoGuardado.getId(),
                odontologoGuardado.getNroMatricula(),
                odontologoGuardado.getApellido(),
                odontologoGuardado.getNombre()
        );
    }

    @Override
    public OdontologoResponseDto buscarOdontologo(Integer id) {
        return iOdontologoRepository.findById(id).map(odontologo -> modelMapper.map(odontologo, OdontologoResponseDto.class))
                .orElseGet(() -> {
                    LOGGER.info("No se encontró el odontologo con id: {}"+ id);
                    return null;
                });
    }
    @Override
    public List<OdontologoResponseDto> listarTodos( ) {
        return iOdontologoRepository.findAll().stream()

                .map(odontologo -> new OdontologoResponseDto(
                        odontologo.getId(),
                        odontologo.getNroMatricula(),
                        odontologo.getApellido(),
                        odontologo.getNombre()
                ))
                .toList();
    }
    @Override
    public OdontologoResponseDto actualizarOdontologo( Integer id,OdontologoRequestDto odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoAActualizar = iOdontologoRepository.findById(id);
        if (odontologoAActualizar.isPresent()) {
            Odontologo odontologoActualizado = iOdontologoRepository.save(modelMapper.map(Odontologo.builder()
                    .id(id)
                    .apellido(odontologo.getApellido())
                    .nombre(odontologo.getNombre())
                    .nroMatricula(odontologo.getNroMatricula())
                    .build(), Odontologo.class));
            LOGGER.info("Odontologo actualizado: {}", odontologoActualizado);
            return modelMapper.map(odontologoActualizado, OdontologoResponseDto.class);
        } else {
            throw new ResourceNotFoundException("No se encontró el odontologo a actualizar con id: " + id);
        }
    }


}





