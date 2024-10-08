package com.example.Mi_Clinica.DTO.Request;

import com.example.Mi_Clinica.model.Domicilio;

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

    // Constructor que acepta un Domicilio
    public DomicilioRequestDto(Domicilio domicilio) {
        if (domicilio != null) {
            this.calle = domicilio.getCalle();
            this.numero = domicilio.getNumero();
            this.localidad = domicilio.getLocalidad();
            this.provincia = domicilio.getProvincia();
        }
    }
}
