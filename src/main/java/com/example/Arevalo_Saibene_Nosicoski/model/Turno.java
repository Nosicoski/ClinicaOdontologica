package com.example.Arevalo_Saibene_Nosicoski.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Odontologo odontologo;
    private LocalDate fecha;


    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", Odontologo=" + odontologo +
                ", fecha=" + fecha +
                '}';
    }
}

