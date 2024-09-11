package com.example.Arevalo_Saibene_Nosicoski.Controller;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.TurnoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.TurnoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.BadRequestException;
import com.example.Arevalo_Saibene_Nosicoski.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Turno")
@Validated
public class ControladorDeTurnos {

    private final ITurnoService turnoService;

    public ControladorDeTurnos(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<TurnoResponseDto> registrarTurno(@RequestBody @Valid TurnoRequestDto turnoRequestDto) {
        try {
            TurnoResponseDto turnoResponseDto = turnoService.registrarTurno(turnoRequestDto);
            return new ResponseEntity<>(turnoResponseDto, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Otros métodos del controlador...
}
