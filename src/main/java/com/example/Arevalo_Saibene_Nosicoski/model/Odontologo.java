package com.example.Arevalo_Saibene_Nosicoski.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Odontologo")
@Builder

public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nroMatricula;
    private String apellido;
    private String nombre;

    @OneToMany(mappedBy = "odontologo")
    @JsonIgnore
    //@JsonManagedReference(value = "odontologo-turno")
    private Set<Turno> turnoSet;


    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", nroMatricula='" + nroMatricula + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
