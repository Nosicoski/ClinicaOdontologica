package com.example.Arevalo_Saibene_Nosicoski.Controller;


import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.OdontologoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.OdontologoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import com.example.Arevalo_Saibene_Nosicoski.service.IOdontologoService;
import com.example.Arevalo_Saibene_Nosicoski.service.impl.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/Odontologo")
public class ControladorDeOdontologo {
    private IOdontologoService odontologoService;

    public ControladorDeOdontologo(OdontologoService odontologService) {
        this.odontologoService = odontologService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<OdontologoResponseDto> agregarOdontologo(@RequestBody OdontologoRequestDto odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }



    @GetMapping("/buscar/{id}")
    public ResponseEntity <Optional<OdontologoResponseDto>> buscarOdontologoPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(odontologoService.buscarOdontologo(id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<OdontologoResponseDto> eliminarOdontologo(@PathVariable Integer id) throws ResourceNotFoundException{
        odontologoService.eliminarOdontologoDto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

