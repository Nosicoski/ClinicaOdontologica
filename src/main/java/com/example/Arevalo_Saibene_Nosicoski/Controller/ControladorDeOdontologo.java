package com.example.Arevalo_Saibene_Nosicoski.Controller;


import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.OdontologoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.OdontologoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import com.example.Arevalo_Saibene_Nosicoski.service.impl.OdontologoService;
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
    public ResponseEntity<OdontologoResponseDto> agregarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologService.guardarOdontologo(new OdontologoRequestDto()));
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
}

