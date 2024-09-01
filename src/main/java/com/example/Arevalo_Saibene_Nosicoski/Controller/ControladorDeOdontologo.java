package com.example.Arevalo_Saibene_Nosicoski.Controller;


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
    public ResponseEntity<Odontologo> agregarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologService.guardarOdontologo(odontologo));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id){
        Optional<Odontologo> odontologo = odontologService.buscarPorId(id);
        if(odontologo.isPresent()){
            return ResponseEntity.ok(odontologo.get());
        } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }
}

