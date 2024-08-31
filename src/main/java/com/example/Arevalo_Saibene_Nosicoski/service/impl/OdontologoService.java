package com.example.Arevalo_Saibene_Nosicoski.service.impl;
import java.util.List;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import com.example.Arevalo_Saibene_Nosicoski.repository.IOdontologoRepository;
import com.example.Arevalo_Saibene_Nosicoski.service.IOdontologoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService  {
    private final IOdontologoRepository iOdontologoRepository;

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







