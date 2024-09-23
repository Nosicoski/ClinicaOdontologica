package com.example.Mi_Clinica.DTO.Response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OdontologoResponseDto {
    private Integer id;
    private String nroMatricula;
    private String apellido;
    private String nombre;

}



