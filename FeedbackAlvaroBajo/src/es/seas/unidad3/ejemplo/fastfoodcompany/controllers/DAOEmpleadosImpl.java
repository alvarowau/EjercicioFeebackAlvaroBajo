
package es.seas.unidad3.ejemplo.fastfoodcompany.controllers;

import com.mysql.jdbc.PreparedStatement;
import es.seas.unidad3.ejemplo.fastfoodcompany.interfaces.DAO;
import es.seas.unidad3.fastfoodcompany.bbdd.GestionSql;
import es.seas.unidad3.fastfoodcompany.models.Empleados;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author AlvaroBajo
 */
public class DAOEmpleadosImpl extends GestionSql implements DAO<Empleados> {

    @Override
    public void registrar(Empleados X) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("INSERT INTO trabajadores(nombre, apellidoPaterno, apellidoMaterno, permiso, pass ) VALUES (?,?,?,?,?);");
            st.setString(1, X.getNombre());
            st.setString(2, X.getApellidoPaterno());
            st.setString(3, X.getApellidoMaterno());
            st.setBoolean(4, X.getPermiso());
            st.setString(5, X.getPass());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public void modificar(Empleados X) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("UPDATE trabajadores SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, permiso = ? WHERE id = ?");
            st.setString(1, X.getNombre());
            st.setString(2, X.getApellidoPaterno());
            st.setString(3, X.getApellidoMaterno());
            st.setBoolean(4, X.getPermiso());
            st.setInt(5, X.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("DELETE FROM trabajadores WHERE id= ?;");
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public List<Empleados> listar() throws Exception {
        List<Empleados> lista = null;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM trabajadores;");
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Empleados X = new Empleados();
                X.setId(rs.getInt("id"));
                X.setNombre(rs.getString("nombre"));
                X.setApellidoPaterno(rs.getString("apellidoPaterno"));
                X.setApellidoMaterno(rs.getString("apellidoMaterno"));
                X.setPermiso(rs.getBoolean("permiso"));
                lista.add(X);
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            this.closeConnection();
        }
        return lista;
    }

    @Override
    public List<Empleados> buscar(String nombre) throws Exception {
         List<Empleados> lista = null;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM trabajadores WHERE nombre LIKE '%" + nombre + "%';");
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Empleados X = new Empleados();
                X.setId(rs.getInt("id"));
                X.setNombre(rs.getString("nombre"));
                X.setApellidoPaterno(rs.getString("apellidoPaterno"));
                X.setApellidoMaterno(rs.getString("apellidoMaterno"));
                X.setPermiso(rs.getBoolean("permiso"));
                lista.add(X);
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            this.closeConnection();
        }
        return lista;
    }

}
