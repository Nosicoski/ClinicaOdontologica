package com.example.Arevalo_Saibene_Nosicoski.dao.impl;

import com.example.Arevalo_Saibene_Nosicoski.dao.IDao;
import com.example.Arevalo_Saibene_Nosicoski.db.H2Connection;
import com.example.Arevalo_Saibene_Nosicoski.model.Odontologo;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class DaoH2Odontologo implements IDao<Odontologo> {

    public static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DaoH2Odontologo.class);
    public static final String BUSCAR_ID = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return null;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        Connection connection = null;
        Odontologo odontologoEncontrado = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(BUSCAR_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Integer iddb = resultSet.getInt(1);
                String nro_matricula = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);
                odontologoEncontrado = new Odontologo(id, nro_matricula,nombre,apellido);
            }
            LOGGER.info("Odontologo encontrado: "+odontologoEncontrado);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return odontologoEncontrado;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return null;
    }


    @Override
    public void modificar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Obtén la conexión
            connection = H2Connection.getConnection();

            // Sentencia SQL de actualización
            String modificarSQL = "UPDATE ODONTOLOGOS SET nroMatricula = ?, apellido = ?, nombre = ? WHERE id = ?";

            // Preparar la consulta
            preparedStatement = connection.prepareStatement(modificarSQL);

            // Establecer los parámetros de la consulta
            preparedStatement.setString(1, odontologo.getNroMatricula());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getNombre());
            preparedStatement.setInt(4, odontologo.getId());

            // Ejecutar la actualización
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("El odontólogo fue modificado con éxito.");
            } else {
                System.out.println("No se encontró ningún odontólogo con el ID especificado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al modificar el odontólogo: " + e.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // Obtener la conexión a la base de datos
            connection = H2Connection.getConnection();

            // Sentencia SQL para eliminar el odontólogo por ID
            String eliminarSQL = "DELETE FROM ODONTOLOGOS WHERE ID = ?";

            // Preparar la consulta
            preparedStatement = connection.prepareStatement(eliminarSQL);

            // Establecer el parámetro del ID
            preparedStatement.setInt(1, id);

            // Ejecutar la eliminación
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("El odontólogo fue eliminado con éxito.");
            } else {
                System.out.println("No se encontró ningún odontólogo con el ID especificado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el odontólogo: " + e.getMessage());
        } finally {
            // Cerrar los recursos
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}


}

