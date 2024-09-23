package com.example.Mi_Clinica.DTO.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TurnoResponseDto {
    private Long id;
    private PacienteResponseDto pacienteResponseDto;
    private  OdontologoResponseDto odontologoResponseDto;
    private String fecha;
}
