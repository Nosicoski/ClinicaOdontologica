package com.example.Arevalo_Saibene_Nosicoski;

import com.example.Arevalo_Saibene_Nosicoski.db.H2Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ArevaloSaibeneNosicoskiApplication {

	public static void main(String[] args) {
		H2Connection h2Connection =new H2Connection();
		SpringApplication.run(ArevaloSaibeneNosicoskiApplication.class, args);
		try {
			h2Connection.getConnection();
		}catch (SQLException e){
			System.out.println("Error en la conexion" + e.getMessage());

		}

	}

}
