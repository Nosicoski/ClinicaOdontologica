package com.example.Arevalo_Saibene_Nosicoski.service.impl;

import java.util.List;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.OdontologoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.OdontologoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import com.example.Arevalo_Saibene_Nosicoski.repository.IOdontologoRepository;
import com.example.Arevalo_Saibene_Nosicoski.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OdontologoService implements IOdontologoService {

    private final ModelMapper modelMapper;
    private final IOdontologoRepository iOdontologoRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

    public OdontologoService(ModelMapper modelMapper, IOdontologoRepository iOdontologoRepository) {
        this.modelMapper = modelMapper;
        this.iOdontologoRepository = iOdontologoRepository;
    }

    @Transactional
    @Override
    public void eliminarOdontologoDto(Integer id) {
        if (!iOdontologoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el odontologo a eliminar con id: " + id);
        }
        iOdontologoRepository.deleteById(id);
        LOGGER.info("Odontologo eliminado con id: {}", id);
    }

    @Override
    public OdontologoResponseDto guardarOdontologo(OdontologoRequestDto requestDto) {
        Odontologo odontologo = new Odontologo();
        odontologo.setApellido(requestDto.getApellido());
        odontologo.setNombre(requestDto.getNombre());
        odontologo.setNroMatricula(requestDto.getNroMatricula());
        Odontologo odontologoGuardado = iOdontologoRepository.save(odontologo);
        return modelMapper.map(odontologoGuardado, OdontologoResponseDto.class);
    }

    @Override
    public OdontologoResponseDto buscarOdontologo(Integer id) {
        return iOdontologoRepository.findById(id)
                .map(odontologo -> modelMapper.map(odontologo, OdontologoResponseDto.class))
                .orElseThrow(() -> {
                    LOGGER.info("No se encontró el odontologo con id: {}", id);
                    return new ResourceNotFoundException("No se encontró el odontologo con id: " + id);
                });
    }

    @Override
    public List<OdontologoResponseDto> listarTodos() {
        return iOdontologoRepository.findAll().stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoResponseDto.class))
                .toList();
    }

    @Transactional
    @Override
    public OdontologoResponseDto actualizarOdontologo(Integer id, OdontologoRequestDto requestDto) {
        Odontologo odontologo = iOdontologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Odontólogo con ID " + id + " no encontrado"));

        odontologo.setNroMatricula(requestDto.getNroMatricula());
        odontologo.setApellido(requestDto.getApellido());
        odontologo.setNombre(requestDto.getNombre());

        Odontologo odontologoActualizado = iOdontologoRepository.save(odontologo);

        return modelMapper.map(odontologoActualizado, OdontologoResponseDto.class);
    }
}
