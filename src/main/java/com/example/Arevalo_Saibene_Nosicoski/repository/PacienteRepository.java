package com.example.Arevalo_Saibene_Nosicoski.repository;

import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository <Paciente,Integer> {
}
