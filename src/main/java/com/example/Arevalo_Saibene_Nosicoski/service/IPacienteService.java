package com.example.Arevalo_Saibene_Nosicoski.service;

import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Paciente guardarPaciente(Paciente paciente);

    Optional<Paciente> buscarPorId(Integer id);

    List<Paciente> buscarTodos();

    void modificarPaciente(Paciente paciente);
    void eliminarPaciente(Integer id);
    List <Paciente> buscarPorApellidoyNombre(String apellido, String nombre);
    @Query ("Select p from Paciente where p.nombre LIKE %:nombre%")
    List <Paciente> buscarLikeNombre (String nombre);

}
