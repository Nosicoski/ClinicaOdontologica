package com.example.Arevalo_Saibene_Nosicoski.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OdontologoRequestDto {
    @NotBlank(message = "Número de matrícula no puede estar vacío")
    private String nroMatricula;
    private String apellido;
    private String nombre;
}
