package com.example.Arevalo_Saibene_Nosicoski.Controller;


import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.PacienteRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.PacienteResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import com.example.Arevalo_Saibene_Nosicoski.service.impl.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping ("/paciente")

public class ControladorDePaciente {
    private PacienteService pacienteService;

    public ControladorDePaciente(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    // ingresa -> JSON -> jackson -> Objeto Paciente
    // salga -> Objeto Paciente -> jackson -> JSON
    @PostMapping("/guardar")
    public ResponseEntity<PacienteResponseDto> guardarPaciente(@RequestBody @Valid PacienteRequestDto pacienteRequestDto){
        return ResponseEntity.ok(pacienteService.guardarPaciente(new PacienteRequestDto()));
    }

    @GetMapping("/buscar/{id}")

    public ResponseEntity<PacienteResponseDto> buscarPorId(@PathVariable Integer id){

        PacienteResponseDto paciente = pacienteService.buscarPorId(1L);

        if(paciente != null){
            return ResponseEntity.ok(paciente);
        } else {
             ResponseEntity.status(HttpStatus.NOT_FOUND).body("paciente no encontrado");
            //ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<PacienteResponseDto>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarPaciente(@RequestBody Paciente paciente)
    {
        PacienteResponseDto pacienteEncontrado = pacienteService.buscarPorId(paciente.getId());

        if(pacienteEncontrado!= null)

        {
            pacienteService.actualizarPaciente(new PacienteRequestDto(paciente), paciente.getId());
            String jsonResponse = "{\"mensaje\": \"El paciente fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        }

        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")

    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Se borro correctamente el paciente", HttpStatus.NO_CONTENT);
    }
    }



