package com.example.Arevalo_Saibene_Nosicoski.service.impl;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.PacienteRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.PacienteResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;

import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import com.example.Arevalo_Saibene_Nosicoski.repository.IPacienteRepository;
import com.example.Arevalo_Saibene_Nosicoski.service.IPacienteService;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService{

    //Aca hago los atributos del logger
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private IPacienteRepository pacienteRepository;

    private ModelMapper modelMapper;

    public PacienteService(IPacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }


    @Override
    public PacienteResponseDto guardarPaciente(PacienteRequestDto paciente) {
        LOGGER.info("Guardando paciente:".toString());
        Paciente pacienteGuardado =IPacienteRepository.save(modelMapper.map(paciente,Paciente.class));
        LOGGER.info("Paciente Guardado:".toString());

        return modelMapper.map(pacienteGuardado,PacienteResponseDto.class);
    }

    @Override
    public List<PacienteResponseDto> listarPacientes() {
        return List.of();
    }

    @Override
    public PacienteResponseDto buscarPacientePorId(Long id) {
        return null;
    }

    @Override
    public PacienteResponseDto actualizarPaciente(PacienteRequestDto paciente, Long id) {
        return null;
    }

    @Override
    public void eliminarPaciente(Long id) {

    }
}
