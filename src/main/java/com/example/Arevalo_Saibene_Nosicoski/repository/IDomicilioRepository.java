package com.example.Arevalo_Saibene_Nosicoski.repository;

import com.example.Arevalo_Saibene_Nosicoski.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomicilioRepository  extends JpaRepository <Domicilio,Integer> {

}
