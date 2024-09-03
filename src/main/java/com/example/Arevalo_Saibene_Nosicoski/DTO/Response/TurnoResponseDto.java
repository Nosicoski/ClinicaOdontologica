package com.example.Arevalo_Saibene_Nosicoski.DTO.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TurnoResponseDto {

    private Integer id;
    private PacienteResponseDto pacienteResponseDto;
    private  OdontologoResponseDto odontologoResponseDto;
    private String fecha;
}
