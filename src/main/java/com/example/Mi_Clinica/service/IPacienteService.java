package com.example.Mi_Clinica.service;

import com.example.Mi_Clinica.DTO.Request.PacienteRequestDto;
import com.example.Mi_Clinica.DTO.Response.PacienteResponseDto;
import com.example.Mi_Clinica.exception.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService  {
    PacienteResponseDto guardarPaciente(PacienteRequestDto paciente);

    List<PacienteResponseDto> listarPacientes();

    PacienteResponseDto buscarPorId(Long id);

    PacienteResponseDto actualizarPaciente(PacienteRequestDto pacienteRequestDto,Long id) throws ResourceNotFoundException;

    void eliminarPaciente(Long id)throws ResourceNotFoundException;

}
