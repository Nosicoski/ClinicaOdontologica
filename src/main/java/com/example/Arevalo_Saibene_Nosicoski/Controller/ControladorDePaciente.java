package com.example.Arevalo_Saibene_Nosicoski.Controller;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.PacienteRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.PacienteResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import com.example.Arevalo_Saibene_Nosicoski.service.impl.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class ControladorDePaciente {

    private final PacienteService pacienteService;

    public ControladorDePaciente(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<PacienteResponseDto> guardarPaciente(@RequestBody @Valid PacienteRequestDto pacienteRequestDto) {
        PacienteResponseDto pacienteResponseDto = pacienteService.guardarPaciente(pacienteRequestDto);
        return ResponseEntity.ok(pacienteResponseDto);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteResponseDto> buscarPorId(@PathVariable Long id) {
        try {
            PacienteResponseDto paciente = pacienteService.buscarPorId(id);
            return ResponseEntity.ok(paciente);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<PacienteResponseDto>> buscarTodos() {
        List<PacienteResponseDto> pacientes = pacienteService.listarPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> modificarPaciente(@PathVariable Long id, @RequestBody @Valid PacienteRequestDto pacienteRequestDto) {
        try {
            PacienteResponseDto pacienteActualizado = pacienteService.actualizarPaciente(pacienteRequestDto, id);
            return ResponseEntity.ok("{\"mensaje\": \"El paciente fue modificado\"}");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\": \"Paciente no encontrado\"}");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
