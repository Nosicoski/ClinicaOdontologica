package com.example.Arevalo_Saibene_Nosicoski.repository;

import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo,Integer> {

}
