package com.example.Mi_Clinica.Controller;

import java.util.List;
import com.example.Mi_Clinica.DTO.Request.TurnoRequestDto;
import com.example.Mi_Clinica.DTO.Response.TurnoResponseDto;
import com.example.Mi_Clinica.exception.BadRequestException;
import com.example.Mi_Clinica.exception.ResourceNotFoundException;
import com.example.Mi_Clinica.service.ITurnoService;
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
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TurnoResponseDto>> listarTurnos() {
        List<TurnoResponseDto> turnos = turnoService.listarTurnos();
        return new ResponseEntity<>(turnos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDto> buscarTurnoPorId(@PathVariable Integer id) {
        try {
            TurnoResponseDto turno = turnoService.buscarTurnoPorId(id);
            return new ResponseEntity<>(turno, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Integer id) {
        try {
            turnoService.eliminarTurno(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurnoResponseDto> actualizarTurno(@PathVariable Integer id, @RequestBody @Valid TurnoRequestDto turnoRequestDto) {
        try {
            TurnoResponseDto turnoActualizado = turnoService.actualizarTurno(turnoRequestDto, id);
            return new ResponseEntity<>(turnoActualizado, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
