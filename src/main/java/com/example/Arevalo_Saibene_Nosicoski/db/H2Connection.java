package com.example.Arevalo_Saibene_Nosicoski.db;

import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


import static java.lang.Class.forName;

public class H2Connection {

    public String usuario = "sa";
    public String Contraseña = "";
    public String url = "jdbc:h2:~/test";
    public String Drive_DB = "org.h2.Driver";

    public Connection GetConexionDBH2(){
        Connection connection=null;
try {
    Class.forName(Drive_DB);
     connection= DriverManager.getConnection(url,usuario,Contraseña);
    System.out.println("Conexion a la base de datos exitosa");

} catch ( ClassNotFoundException | SQLException exception){
    exception.printStackTrace();
    System.out.println("Conexion a la base de datos fallida");
 }
        return connection;
    }
}
