package com.example.Arevalo_Saibene_Nosicoski.Config;

import com.example.Arevalo_Saibene_Nosicoski.DTO.Response.DomicilioResponseDto;
import com.example.Arevalo_Saibene_Nosicoski.model.Domicilio;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        // Configurar mapeo entre Domicilio y DomicilioResponseDto
        modelMapper.addMappings(new PropertyMap<Domicilio, DomicilioResponseDto>() {
            @Override
            protected void configure() {
                map().setCalle(source.getCalle());
                map().setNumero(source.getNumero());
                map().setLocalidad(source.getLocalidad());
                map().setProvincia(source.getProvincia());
            }
        });

        return modelMapper;
    }

    @PostConstruct
    public void init() {
        System.out.println("ModelMapper bean created!");
    }
}
