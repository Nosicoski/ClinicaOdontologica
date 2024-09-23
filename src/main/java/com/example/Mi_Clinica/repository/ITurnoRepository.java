package com.example.Mi_Clinica.repository;

import com.example.Mi_Clinica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository <Turno, Integer> {

}
