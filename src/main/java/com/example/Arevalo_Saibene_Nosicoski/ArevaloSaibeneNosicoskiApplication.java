package com.example.Arevalo_Saibene_Nosicoski;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;


@SpringBootApplication
public class ArevaloSaibeneNosicoskiApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(ArevaloSaibeneNosicoskiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ArevaloSaibeneNosicoskiApplication.class);
		LOGGER.info("Aplicacion inicializada");

	}

}
