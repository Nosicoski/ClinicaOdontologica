package com.example.Arevalo_Saibene_Nosicoski.DTO.Response;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PacienteResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private int dni;
    private LocalDate fechaIngreso;
    private DomicilioResponseDto domicilioResponseDto;
}
