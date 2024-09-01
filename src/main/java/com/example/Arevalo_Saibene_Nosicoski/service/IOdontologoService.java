package com.example.Arevalo_Saibene_Nosicoski.service;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.OdontologoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.OdontologoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import com.example.Arevalo_Saibene_Nosicoski.repository.IOdontologoRepository;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);

    Optional<Odontologo> buscarPorId(Integer id);

    void eliminarOdontologo(Integer id);

    List<Odontologo> listarOdontologos();

    List<OdontologoResponseDto> listarOdontologosDto();
    Optional<OdontologoResponseDto> buscarDtoPorId(Integer id);
    OdontologoResponseDto guardarOdontologo(OdontologoRequestDto requestDto);

    OdontologoResponseDto actualizarOdontologo(Integer id, OdontologoRequestDto requestDto);

    void eliminarOdontologoDto(Integer id);
}
