/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.seas.unidad3.ejemplo.fastfoodcompany.controllers;

import com.mysql.jdbc.PreparedStatement;
import es.seas.unidad3.ejemplo.fastfoodcompany.interfaces.DAOLoguer;
import es.seas.unidad3.fastfoodcompany.bbdd.GestionSql;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AlvaroBajo
 */
public class DAOLoguerImpl extends GestionSql implements DAOLoguer {

    @Override
    public boolean login(String usuario, String clave) {
        boolean respuesta = false;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement str = (PreparedStatement) this.conexion.prepareStatement("SELECT nombre, pass FROM trabajadores where nombre = '" + usuario + "' and pass = '" + clave + "';");
            ResultSet rst = str.executeQuery();
            if (rst.next()) {
                PreparedStatement str1 = (PreparedStatement) this.conexion.prepareStatement("SELECT nombre, permiso FROM trabajadores where nombre = '" + usuario + "' and permiso = '1';");
                ResultSet rst1 = str1.executeQuery();
                if (rst1.next()) {
                    respuesta = true;
                    rst1.close();
                    str1.close();
                } else {
                    respuesta = false;
                    JOptionPane.showMessageDialog(null, "el usuario no tiene permiso. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                    rst1.close();
                    str1.close();
                }
            } else {
                respuesta = false;
                JOptionPane.showMessageDialog(null, "usuario o contraseña erroneo. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            rst.close();
            str.close();

        } catch (SQLException e) {
            System.out.println("ERROR EN DAOLOGUERIMPL" + e);;
        }
        return respuesta;
    }
}
