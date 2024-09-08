package com.example.Arevalo_Saibene_Nosicoski.Controller;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.DomicilioResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.model.Domicilio;
import com.example.Arevalo_Saibene_Nosicoski.service.DomicilioService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Optional;

@RestController
@RequestMapping("/domicilio")
@Validated

public class ControladorDeDomicilio {
    private final ModelMapper modelMapper;
    private final DomicilioService domicilioService;

    // Constructor para inyectar ModelMapper
    public ControladorDeDomicilio(ModelMapper modelMapper, DomicilioService domicilioService) {
        this.modelMapper = modelMapper;
        this.domicilioService = domicilioService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<DomicilioResponseDto> guardarDomicilio(@RequestBody @Valid Domicilio domicilio) {
        try {
            DomicilioResponseDto domicilioResponseDto = domicilioService.guardarDomicilio(domicilio);
            return new ResponseEntity<>(domicilioResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DomicilioResponseDto> obtenerDomicilio(@PathVariable Long id) {
        //Lógica para obtener un Domicilio por ID (deberías implementar el servicio correspondiente)
        Optional<Domicilio> domicilioOptional = domicilioService.obtenerDomicilioPorId(id);

        if (domicilioOptional.isPresent()) {
            Domicilio domicilio = domicilioOptional.get();
            DomicilioResponseDto domicilioResponseDto = modelMapper.map(domicilio, DomicilioResponseDto.class);
            return new ResponseEntity<>(domicilioResponseDto, HttpStatus.OK);

            
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<DomicilioResponseDto> actualizarDomicilio(@PathVariable Long id) {
        Optional<Domicilio> domicilioOptional = domicilioService.obtenerDomicilioPorId(id);

        if (domicilioOptional.isPresent()) {
            Domicilio domicilio = domicilioOptional.get();
            DomicilioResponseDto domicilioResponseDto = modelMapper.map(domicilio, DomicilioResponseDto.class);
            return new ResponseEntity<>(domicilioResponseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarDomicilio(@PathVariable Long id) {
        domicilioService.eliminarDomicilio(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
