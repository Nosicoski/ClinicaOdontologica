package com.example.Mi_Clinica.repository;

import com.example.Mi_Clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPacienteRepository extends JpaRepository <Paciente,Long> {

}
