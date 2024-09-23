package com.example.Mi_Clinica.DTO.Response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DomicilioResponseDto {
    private Long id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;
}
