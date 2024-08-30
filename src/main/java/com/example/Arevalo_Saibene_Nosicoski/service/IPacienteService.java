package com.example.Arevalo_Saibene_Nosicoski.service;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.PacienteRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.PacienteResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPacienteService  {
    PacienteResponseDto guardarPaciente(PacienteRequestDto paciente);

    List<PacienteResponseDto> listarPacientes();

    PacienteResponseDto buscarPacientePorId(Long id);

    PacienteResponseDto actualizarPaciente(PacienteRequestDto paciente,Long id) ;

    void eliminarPaciente(Long id);

}
