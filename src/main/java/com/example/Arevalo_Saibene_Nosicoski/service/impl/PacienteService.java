package com.example.Arevalo_Saibene_Nosicoski.service.impl;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.PacienteRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.PacienteResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;

import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import com.example.Arevalo_Saibene_Nosicoski.repository.IPacienteRepository;
import com.example.Arevalo_Saibene_Nosicoski.service.IPacienteService;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService{


    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    @Autowired
    private IPacienteRepository pacienteRepository;

    private ModelMapper modelMapper;

    public PacienteService(IPacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;

    }


    @Override
    public PacienteResponseDto guardarPaciente(PacienteRequestDto paciente) {
        LOGGER.info("Guardando paciente:".toString());
        Paciente pacienteGuardado =pacienteRepository.save(modelMapper.map(paciente,Paciente.class));
        LOGGER.info("Paciente Guardado: ".toString());

        return modelMapper.map(pacienteGuardado,PacienteResponseDto.class);
    }

    @Override
    public List<PacienteResponseDto> listarPacientes() {
        return List.of();
    }

    @Override
    public PacienteResponseDto buscarPorId(Long id) {
        return null;
    }



    @Override
    public PacienteResponseDto actualizarPaciente(PacienteRequestDto paciente, Long id) {
        return null;
    }

    @Override
    public void eliminarPaciente(Long id) {

        if (pacienteRepository.findById(id).isEmpty()) throw new ResourceNotFoundException("No se encontró el paciente a eliminar con id: " + id);
        pacienteRepository.deleteById(id);
        LOGGER.info("Paciente eliminado con id:" + id);

    }
}
