package com.example.Arevalo_Saibene_Nosicoski.Config;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @PostConstruct
    public void init() {
        System.out.println("ModelMapper bean created!");
    }
}