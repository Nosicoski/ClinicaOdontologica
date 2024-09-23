package com.example.Mi_Clinica.repository;

import com.example.Mi_Clinica.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomicilioRepository  extends JpaRepository <Domicilio,Long> {

}
