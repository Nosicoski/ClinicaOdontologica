package com.example.Mi_Clinica.Controller;


import com.example.Mi_Clinica.DTO.Request.OdontologoRequestDto;
import com.example.Mi_Clinica.DTO.Response.OdontologoResponseDto;
import com.example.Mi_Clinica.exception.ResourceNotFoundException;
import com.example.Mi_Clinica.service.IOdontologoService;
import com.example.Mi_Clinica.service.impl.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    @PutMapping ("/actualizar/{id}")
    public ResponseEntity<OdontologoResponseDto>actualizarOdontologo(@PathVariable Integer id,@RequestBody OdontologoRequestDto odontologo) throws ResourceNotFoundException{
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(id, odontologo), HttpStatus.OK);
    }

}

