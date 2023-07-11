/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.seas.unidad3.fastfoodcompany.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AlvaroBajo
 */
public class GestionSql {

    protected Connection conexion;
    private final String DB_URL = "jdbc:mysql://localhost:3306/feedbackalvarobajo";
    private final String USER = "root";
    private final String PASS = "";

    public Connection openConnection() {
        //Conectamos a la BDD con las credenciales indicadas antes arriba    
        try {
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            return conexion;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }

    public void closeConnection() throws SQLException {
        if (conexion != null) { //comprobamos que conexion sea diferente de nula y si es diferente de nula:
            if (conexion.isClosed()) {//comprobamos que no este cerrada y si no esta cerrada, la cerramos.
                conexion.close();
            }
        }

    }
}
