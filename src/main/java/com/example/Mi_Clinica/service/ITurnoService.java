package com.example.Mi_Clinica.service;

import com.example.Mi_Clinica.DTO.Request.TurnoRequestDto;
import com.example.Mi_Clinica.DTO.Response.TurnoResponseDto;
import com.example.Mi_Clinica.exception.BadRequestException;
import com.example.Mi_Clinica.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITurnoService {

    TurnoResponseDto registrarTurno(TurnoRequestDto turnoEntradaDTO) throws BadRequestException;


    List<TurnoResponseDto> listarTurnos();


    TurnoResponseDto buscarTurnoPorId(Integer id);

    void eliminarTurno(Integer id) throws ResourceNotFoundException;


    TurnoResponseDto actualizarTurno(TurnoRequestDto turnoEntradaDTO,Integer id) throws ResourceNotFoundException;
}
