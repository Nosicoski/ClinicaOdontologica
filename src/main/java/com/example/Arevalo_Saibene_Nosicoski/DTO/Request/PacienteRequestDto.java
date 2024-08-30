package com.example.Arevalo_Saibene_Nosicoski.DTO.Request;

import com.example.Arevalo_Saibene_Nosicoski.model.Domicilio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PacienteRequestDto {
    private String nombre;
    private String apellido;
    private int dni;
    private LocalDate fechaIngreso;
    
}
