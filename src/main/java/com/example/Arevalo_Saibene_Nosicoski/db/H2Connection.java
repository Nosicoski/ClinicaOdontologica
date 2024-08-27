package com.example.Arevalo_Saibene_Nosicoski.db;

import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


import static java.lang.Class.forName;

public class H2Connection {

    public static String usuario = "sa";
    public static String Contraseña = "";
    public static String url = "jdbc:h2:~/test";
    public static String Drive_DB = "org.h2.Driver";

    public  static Connection getConnection() throws SQLException{
        Connection connection=null;
        try {
            Class.forName(Drive_DB);
            connection = DriverManager.getConnection(url, usuario, Contraseña);
            System.out.println("Conexion a la base de datos exitosa");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error en la conexión SQL: " + e.getMessage());
        }

        return connection;
    }
}
