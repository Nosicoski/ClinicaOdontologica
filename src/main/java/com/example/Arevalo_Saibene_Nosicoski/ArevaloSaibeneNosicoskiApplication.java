package com.example.Arevalo_Saibene_Nosicoski;

import com.example.Arevalo_Saibene_Nosicoski.db.H2Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArevaloSaibeneNosicoskiApplication {

	public static void main(String[] args) {
		H2Connection h2Connection =new H2Connection();
		SpringApplication.run(ArevaloSaibeneNosicoskiApplication.class, args);
		h2Connection.GetConexionDBH2();
	}

}
