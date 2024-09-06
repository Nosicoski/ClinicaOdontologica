package com.example.Arevalo_Saibene_Nosicoski.service;

import com.example.Arevalo_Saibene_Nosicoski.model.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    Turno guardarTurno(Turno turno);

    Optional<Turno> buscarTurnoPorId(Integer id);

    List<Turno> buscarTodosLosTurnos();

    void modificarTurno(Turno turno);
    void eliminarTurno(Integer id);
}
