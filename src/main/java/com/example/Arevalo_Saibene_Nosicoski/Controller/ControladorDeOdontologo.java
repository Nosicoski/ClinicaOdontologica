package com.example.Arevalo_Saibene_Nosicoski.Controller;


import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.OdontologoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.OdontologoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.exception.ResourceNotFoundException;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import com.example.Arevalo_Saibene_Nosicoski.service.impl.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.Optional;

@RestController
@RequestMapping ("/Odontologo")
public class ControladorDeOdontologo {
    private OdontologoService odontologService;

    public ControladorDeOdontologo(OdontologoService odontologService) {
        this.odontologService = odontologService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<OdontologoResponseDto> agregarOdontologo(@RequestBody OdontologoRequestDto odontologo){
        return ResponseEntity.ok(odontologService.guardarOdontologo(odontologo));
    }



    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoResponseDto> buscarPorId(@PathVariable Integer id){
        Optional<OdontologoResponseDto> odontologo = odontologService.buscarPorId(id);
        if(odontologo.isPresent()){
            return ResponseEntity.ok(odontologo.get());
        } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<OdontologoResponseDto> eliminarOdontologo(@PathVariable Integer id) throws ResourceNotFoundException{
        odontologService.eliminarOdontologoDto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

