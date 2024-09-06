package com.example.Arevalo_Saibene_Nosicoski.DTO.Request;

import com.example.Arevalo_Saibene_Nosicoski.model.Domicilio;
import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
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
    private DomicilioRequestDto domicilioRequestDto; // Nombre del campo debe coincidir con el JSON esperado
    public PacienteRequestDto(Paciente paciente) {
        this.nombre = paciente.getNombre();
        this.apellido = paciente.getApellido();
        this.dni = paciente.getDni();
        this.fechaIngreso = paciente.getFechaIngreso();
        if (paciente.getDomicilio() != null) {
            this.domicilioRequestDto = new DomicilioRequestDto(paciente.getDomicilio());
        }
    }

}
