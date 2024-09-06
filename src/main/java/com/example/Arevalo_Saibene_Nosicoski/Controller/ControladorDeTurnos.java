package com.example.Arevalo_Saibene_Nosicoski.Controller;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.TurnoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.TurnoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.BadRequestException;
import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.service.ITurnoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Turno")
public class ControladorDeTurnos {
    private ITurnoService TurnoService;
    public ControladorDeTurnos(ITurnoService turnoService){this.TurnoService=turnoService;}


    @PostMapping("/guardar")
    public ResponseEntity <TurnoResponseDto>registrarTurno(@RequestBody TurnoRequestDto turno)throws BadRequestException{
        return new ResponseEntity<>(TurnoService.registrarTurno(turno), HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity <List<TurnoResponseDto>> listarTurnos(){
        return new ResponseEntity<>(TurnoService.listarTurnos(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id) throws ResourceNotFoundException {
        TurnoService.eliminarTurno(id);
        return new ResponseEntity<>("Se borro correctamente el turno", HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TurnoResponseDto> actualizarTurno(@RequestBody @Valid TurnoRequestDto turnoEntradaDTO, @PathVariable Integer id) throws ResourceNotFoundException {
        return new ResponseEntity<>(TurnoService.actualizarTurno(turnoEntradaDTO,id), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDto> buscarTurno(@PathVariable Integer id) {
        return new ResponseEntity<>(TurnoService.buscarTurnoPorId(id), HttpStatus.OK);
}
}
