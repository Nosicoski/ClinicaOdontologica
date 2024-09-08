package com.example.Arevalo_Saibene_Nosicoski.Config;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.PacienteResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.TurnoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.OdontologoResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.DomicilioResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.model.Domicilio;
import com.example.Arevalo_Saibene_Nosicoski.model.Paciente;
import com.example.Arevalo_Saibene_Nosicoski.model.Turno;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configuración de mapeo para Odontologo a OdontologoResponseDto
        modelMapper.addMappings(new PropertyMap<Odontologo, OdontologoResponseDto>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setNroMatricula(source.getNroMatricula());
                map().setApellido(source.getApellido());
                map().setNombre(source.getNombre());
            }
        });

        // Mapeo de Domicilio a DomicilioResponseDto
        modelMapper.addMappings(new PropertyMap<Domicilio, DomicilioResponseDto>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setCalle(source.getCalle());
                map().setNumero(source.getNumero());
                map().setLocalidad(source.getLocalidad());
                map().setProvincia(source.getProvincia());
            }
        });

        // Configuración de mapeo para Paciente a PacienteResponseDto
        modelMapper.addMappings(new PropertyMap<Paciente, PacienteResponseDto>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setNombre(source.getNombre());
                map().setApellido(source.getApellido());
                map().setDni(source.getDni());
                map().setFechaIngreso(source.getFechaIngreso());
                map().setDomicilioResponseDto(source.getDomicilio() != null ?
                        modelMapper.map(source.getDomicilio(), DomicilioResponseDto.class) : null);
            }
        });

        // Configuración de mapeo para Turno a TurnoResponseDto
        modelMapper.addMappings(new PropertyMap<Turno, TurnoResponseDto>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setFecha(source.getFecha().toString());
                map().setPacienteResponseDto(source.getPaciente() != null ?
                        modelMapper.map(source.getPaciente(), PacienteResponseDto.class) : null);
                map().setOdontologoResponseDto(source.getOdontologo() != null ?
                        modelMapper.map(source.getOdontologo(), OdontologoResponseDto.class) : null);
            }
        });

        return modelMapper;
    }

    @PostConstruct
    public void init() {
        System.out.println("ModelMapper bean created!");
    }
}
