package com.example.Mi_Clinica.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OdontologoRequestDto {

    @NotBlank(message = "Número de matrícula no puede estar vacío")
    @Size(min = 5, max = 10, message = "Número de matrícula debe tener entre 5 y 10 caracteres")
    private String nroMatricula;

    @NotBlank(message = "Apellido no puede estar vacío")
    private String apellido;

    @NotBlank(message = "Nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "Domicilio no puede estar vacío")
    private String domicilio;

}
