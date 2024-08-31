package com.example.Arevalo_Saibene_Nosicoski.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 5, max = 10, message = "Número de matrícula debe tener entre 5 y 10 caracteres")
    private String nroMatricula;

    @NotBlank(message = "Apellido no puede estar vacío")
    private String apellido;

    @NotBlank(message = "Nombre no puede estar vacío")
    private String nombre;
}
