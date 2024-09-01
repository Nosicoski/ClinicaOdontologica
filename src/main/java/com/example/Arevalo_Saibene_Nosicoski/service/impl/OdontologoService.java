package com.example.Arevalo_Saibene_Nosicoski.service.impl;

import java.util.List;


import com.example.Arevalo_Saibene_Nosicoski.DTO.Request.OdontologoRequestDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.OdontologoResponseDto;

import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import com.example.Arevalo_Saibene_Nosicoski.repository.IOdontologoRepository;
import com.example.Arevalo_Saibene_Nosicoski.service.IOdontologoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class OdontologoService implements IOdontologoService  {
    private final IOdontologoRepository iOdontologoRepository;

public class OdontologoService {
    private IOdontologoRepository iOdontologoRepository;


    public OdontologoService(IOdontologoRepository iOdontologoRepository) {
        this.iOdontologoRepository = iOdontologoRepository;

    }

    @Transactional
    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return iOdontologoRepository.save(odontologo);
    }

    @Override
public Optional<Odontologo> buscarPorId(Integer id) {
        return iOdontologoRepository.findById(id);
    }


    @Transactional
    @Override
    public void eliminarOdontologo(Integer id) {
        iOdontologoRepository.deleteById(id);
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        return iOdontologoRepository.findAll();
    }
}



    @Transactional
    public OdontologoResponseDto guardarOdontologo(OdontologoRequestDto requestDto) {
        Odontologo odontologo = new Odontologo(
                //null,
        // Errores a resolver en RequestDto        requestDto.getNroMatricula(),
        // Errores a resolver en RequestDto       requestDto.getApellido(),
        // Errores a resolver en RequestDto       requestDto.getNombre()
        );








        Odontologo odontologoGuardado = iOdontologoRepository.save(odontologo);

        return new OdontologoResponseDto(
                odontologoGuardado.getId(),
                odontologoGuardado.getNroMatricula(),
                odontologoGuardado.getApellido(),
                odontologoGuardado.getNombre()
        );
    }

    public Optional<OdontologoResponseDto> buscarPorMatricula(Integer id) {
        return iOdontologoRepository.findById(id)
                .map(odontologo -> new OdontologoResponseDto(
                        odontologo.getId(),
                        odontologo.getNroMatricula(),
                        odontologo.getApellido(),
                        odontologo.getNombre()));
    }
    public List<OdontologoResponseDto> listarTodos() {
        return iOdontologoRepository.findAll().stream()
                .map(odontologo -> new OdontologoResponseDto(
                        odontologo.getId(),
                        odontologo.getNroMatricula(),
                        odontologo.getApellido(),
                        odontologo.getNombre()
                ))
                .toList();
    }
    @Transactional
    public OdontologoResponseDto actualizarOdontologo(Integer id, OdontologoRequestDto requestDto) {
        Odontologo odontologo = iOdontologoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Odontólogo con ID " + id + " no encontrado"));

        //odontologo.setNroMatricula(requestDto.getNroMatricula());
        //odontologo.setApellido(requestDto.getApellido());
        //odontologo.setNombre(requestDto.getNombre());

        Odontologo odontologoActualizado = iOdontologoRepository.save(odontologo);

        return new OdontologoResponseDto(
                odontologoActualizado.getId(),
                odontologoActualizado.getNroMatricula(),
                odontologoActualizado.getApellido(),
                odontologoActualizado.getNombre()
        );
    }

    @Transactional
    public void eliminarOdontologo(Integer id) {
        if (iOdontologoRepository.existsById(id)) {
            iOdontologoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Odontólogo con ID " + id + " no encontrado");
        }
    }
}

