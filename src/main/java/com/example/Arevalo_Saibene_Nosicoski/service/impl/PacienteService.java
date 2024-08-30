package com.example.Arevalo_Saibene_Nosicoski.service.impl;

import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import com.example.Arevalo_Saibene_Nosicoski.repository.IPacienteRepository;
import com.example.Arevalo_Saibene_Nosicoski.service.IPacienteService;

import java.util.Optional;


public class PacienteService {
    public Optional<Paciente> buscarPorId(Integer id) {
        return IPacienteRepository.findById(id);
    }
}
