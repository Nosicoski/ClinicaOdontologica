package com.example.Arevalo_Saibene_Nosicoski.service;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.TurnoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.TurnoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.BadRequestException;
import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.model.Turno;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ITurnoService {

    TurnoResponseDto registrarTurno(TurnoRequestDto turnoEntradaDTO) throws BadRequestException;


    List<TurnoResponseDto> listarTurnos();


    TurnoResponseDto buscarTurnoPorId(Integer id);

    void eliminarTurno(Integer id) throws ResourceNotFoundException;


    TurnoResponseDto actualizarTurno(TurnoRequestDto turnoEntradaDTO,Integer id) throws ResourceNotFoundException;
}
