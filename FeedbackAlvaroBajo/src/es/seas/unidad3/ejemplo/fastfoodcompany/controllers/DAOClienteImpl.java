
package es.seas.unidad3.ejemplo.fastfoodcompany.controllers;

import com.mysql.jdbc.PreparedStatement;
import es.seas.unidad3.ejemplo.fastfoodcompany.interfaces.DAO;
import es.seas.unidad3.fastfoodcompany.bbdd.GestionSql;
import es.seas.unidad3.fastfoodcompany.models.Clientes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author AlvaroBajo
 */
public class DAOClienteImpl extends GestionSql implements DAO<Clientes> {

    @Override
    public void registrar(Clientes X) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("INSERT INTO clientes(nombre, apellidoPaterno, apellidoMaterno ) VALUES (?,?,?);");
            st.setString(1, X.getNombre());
            st.setString(2, X.getApellidoPaterno());
            st.setString(3, X.getApellidoMaterno());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            this.closeConnection();

        }
    }

    @Override
    public void modificar(Clientes X) throws Exception {
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("UPDATE clientes SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ? WHERE id = ?");
            st.setString(1, X.getNombre());
            st.setString(2, X.getApellidoPaterno());
            st.setString(3, X.getApellidoMaterno());
            st.setInt(4, X.getId());
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
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("DELETE FROM clientes WHERE id= ?;");
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            this.closeConnection();

        }
    }

    /*
    *los metodos Listar y buscar podrian ir en el mismo metodo, pero decido hacerlo asi
    *por mi eficiencia a la hora de programar y no tener que pasar texto vacio
     */
    @Override
    public List<Clientes> listar() throws Exception {
        List<Clientes> lista = null;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM clientes;");
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Clientes X = new Clientes();
                X.setId(rs.getInt("id"));
                X.setNombre(rs.getString("nombre"));
                X.setApellidoPaterno(rs.getString("apellidoPaterno"));
                X.setApellidoMaterno(rs.getString("apellidoMaterno"));
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
    public List<Clientes> buscar(String nombre) throws Exception {
        List<Clientes> lista = null;
        try {
            this.openConnection();//Establecemos la conexion a la base de datos.
            PreparedStatement st = (PreparedStatement) this.conexion.prepareStatement("SELECT * FROM clientes WHERE nombre LIKE '%" + nombre + "%';");
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Clientes X = new Clientes();
                X.setId(rs.getInt("id"));
                X.setNombre(rs.getString("nombre"));
                X.setApellidoPaterno(rs.getString("apellidoPaterno"));
                X.setApellidoMaterno(rs.getString("apellidoMaterno"));
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
