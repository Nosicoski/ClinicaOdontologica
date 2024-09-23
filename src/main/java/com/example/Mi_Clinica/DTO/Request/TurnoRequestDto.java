package com.example.Mi_Clinica.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class TurnoRequestDto {

    @NotNull(message = "El ID del odontólogo no puede ser nulo.")
    private Integer odontologo_id;

    @NotNull(message = "El ID del paciente no puede ser nulo.")
    private Integer paciente_id;

    @NotNull(message = "La fecha del turno no puede ser nula.")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe tener el formato YYYY-MM-DD.")
    private String fecha;

    // Getters y Setters

    public Integer getOdontologo_id() {
        return odontologo_id;
    }

    public void setOdontologo_id(Integer odontologo_id) {
        this.odontologo_id = odontologo_id;
    }

    public Integer getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Integer paciente_id) {
        this.paciente_id = paciente_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

