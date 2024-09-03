package com.example.Arevalo_Saibene_Nosicoski.DTO.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TurnoRequestDto {
    private Integer paciente_id;
    private Integer odontologo_id;
    private String fecha;

}
