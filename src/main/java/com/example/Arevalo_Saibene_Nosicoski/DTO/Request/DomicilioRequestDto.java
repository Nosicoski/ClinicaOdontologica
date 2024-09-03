package com.example.Arevalo_Saibene_Nosicoski.DTO.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DomicilioRequestDto {
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;
}
