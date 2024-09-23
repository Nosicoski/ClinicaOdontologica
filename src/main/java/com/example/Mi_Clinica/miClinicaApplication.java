package com.example.Mi_Clinica;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class miClinicaApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(miClinicaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(miClinicaApplication.class);
		LOGGER.info("Aplicacion inicializada");

	}

}
