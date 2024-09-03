package com.example.Arevalo_Saibene_Nosicoski.DTO.Request;

import com.example.Arevalo_Saibene_Nosicoski.model.Domicilio;
import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PacienteRequestDto {
    private String nombre;
    private String apellido;
    private int dni;
    private LocalDate fechaIngreso;
    private DomicilioRequestDto domicilioEntradaDTO;
    public PacienteRequestDto(Paciente paciente) {
    }
}
