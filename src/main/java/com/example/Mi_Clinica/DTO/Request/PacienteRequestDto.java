package com.example.Mi_Clinica.DTO.Request;

import com.example.Mi_Clinica.model.Paciente;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PacienteRequestDto {
    private String nombre;
    private String apellido;
    private int dni;
    private LocalDate fechaIngreso;
    private DomicilioRequestDto domicilio; // Nombre del campo debe coincidir con el JSON esperado

    public PacienteRequestDto(Paciente paciente) {
        this.nombre = paciente.getNombre();
        this.apellido = paciente.getApellido();
        this.dni = paciente.getDni();
        this.fechaIngreso = paciente.getFechaIngreso();
        if (paciente.getDomicilio() != null) {
            this.domicilio = new DomicilioRequestDto(paciente.getDomicilio());
        }
    }

}
