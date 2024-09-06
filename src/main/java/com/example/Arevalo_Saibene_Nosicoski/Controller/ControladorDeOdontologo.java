package com.example.Arevalo_Saibene_Nosicoski.Controller;


import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.OdontologoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.OdontologoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import com.example.Arevalo_Saibene_Nosicoski.service.IOdontologoService;
import com.example.Arevalo_Saibene_Nosicoski.service.impl.OdontologoService;
import jakarta.validation.Valid;
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
    public ResponseEntity <OdontologoResponseDto> guardarOdontologo(@RequestBody  OdontologoRequestDto odontologo){
        return new ResponseEntity<>(odontologoService.guardarOdontologo(odontologo),HttpStatus.CREATED );
    }



    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoResponseDto> buscarOdontologoPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(odontologoService.buscarOdontologo(id), HttpStatus.OK);
    }

    @GetMapping ("/ListarTodos")
    public ResponseEntity<List<OdontologoResponseDto>> listarTodos(){
       return new ResponseEntity<>(odontologoService.listarTodos(),HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<OdontologoResponseDto> eliminarOdontologo(@PathVariable Integer id) throws ResourceNotFoundException{
        odontologoService.eliminarOdontologoDto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

