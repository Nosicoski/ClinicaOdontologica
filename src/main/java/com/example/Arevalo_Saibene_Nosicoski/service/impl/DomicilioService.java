package com.example.Arevalo_Saibene_Nosicoski.service;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.DomicilioResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.model.Domicilio;

import java.util.Optional;

public interface DomicilioService {

    Optional<Domicilio> obtenerDomicilioPorId(Long id);

    DomicilioResponseDto guardarDomicilio(Domicilio domicilio);

    void eliminarDomicilio(Long id);
}
