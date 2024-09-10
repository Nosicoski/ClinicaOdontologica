package com.example.Arevalo_Saibene_Nosicoski.service.impl;

import com.example.Arevalo_Saibene_Nosicoski.model.Domicilio;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.PacienteRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.PacienteResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import com.example.Arevalo_Saibene_Nosicoski.repository.IPacienteRepository;
import com.example.Arevalo_Saibene_Nosicoski.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final IPacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PacienteResponseDto guardarPaciente(PacienteRequestDto pacienteRequestDto) {
        LOGGER.info("Guardando paciente:");

        // Convertir DomicilioRequestDto a Domicilio
        Domicilio domicilio = modelMapper.map(pacienteRequestDto.getDomicilioRequestDto(), Domicilio.class);

        // Crear el Paciente
        Paciente paciente = modelMapper.map(pacienteRequestDto, Paciente.class);
        paciente.setDomicilio(domicilio); // Asociar el domicilio al paciente

        // Guardar el paciente en la base de datos
        Paciente pacienteGuardado = pacienteRepository.save(paciente);
        LOGGER.info("Paciente Guardado");

        // Convertir el paciente guardado a PacienteResponseDto
        return modelMapper.map(pacienteGuardado, PacienteResponseDto.class);
    }

    @Override
    public List<PacienteResponseDto> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                .map(paciente -> modelMapper.map(paciente, PacienteResponseDto.class))
                .toList();
    }

    @Override
    public PacienteResponseDto buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id: " + id));
        return modelMapper.map(paciente, PacienteResponseDto.class);
    }

    @Override
    public PacienteResponseDto actualizarPaciente(PacienteRequestDto pacienteRequestDto, Long id) throws ResourceNotFoundException {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id: " + id));

        // Actualizar los campos del paciente con los datos del DTO
        paciente.setNombre(pacienteRequestDto.getNombre());
        paciente.setApellido(pacienteRequestDto.getApellido());
        paciente.setDni(pacienteRequestDto.getDni());
        paciente.setFechaIngreso(pacienteRequestDto.getFechaIngreso());

        // Maneja DomicilioRequestDto si es necesario
        if (pacienteRequestDto.getDomicilioRequestDto() != null) {
            Domicilio domicilio = modelMapper.map(pacienteRequestDto.getDomicilioRequestDto(), Domicilio.class);
            paciente.setDomicilio(domicilio); // Actualizar el domicilio del paciente
        }

        Paciente pacienteActualizado = pacienteRepository.save(paciente);
        return modelMapper.map(pacienteActualizado, PacienteResponseDto.class);
    }

    @Override
    public void eliminarPaciente(Long id) {
        if (pacienteRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el paciente a eliminar con id: " + id);
        }
        pacienteRepository.deleteById(id);
        LOGGER.info("Paciente eliminado con id:" + id);
    }
}
