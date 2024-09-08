package com.example.Arevalo_Saibene_Nosicoski.service.impl;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.DomicilioResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.model.Domicilio;

import com.example.Arevalo_Saibene_Nosicoski.repository.IDomicilioRepository;
import com.example.Arevalo_Saibene_Nosicoski.service.DomicilioService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class DomicilioServiceImpl implements DomicilioService {
    private final IDomicilioRepository domicilioRepository;
    private final ModelMapper modelMapper;

    public DomicilioServiceImpl(IDomicilioRepository domicilioRepository, ModelMapper modelMapper) {
        this.domicilioRepository = domicilioRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public Optional<Domicilio> obtenerDomicilioPorId(Long id) {
        return domicilioRepository.findById(id);
    }

    @Override
    public DomicilioResponseDto guardarDomicilio(Domicilio domicilio) {
        Domicilio domicilioGuardado = domicilioRepository.save(domicilio);
        return modelMapper.map(domicilioGuardado, DomicilioResponseDto.class);
    }

    @Override
    public void eliminarDomicilio(Long id) {
        domicilioRepository.deleteById(id);
    }

}
