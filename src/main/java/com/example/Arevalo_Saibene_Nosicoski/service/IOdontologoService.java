package com.example.Arevalo_Saibene_Nosicoski.service;

import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;

import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);
    Optional<Odontologo> buscarPorId(Integer id);
}
