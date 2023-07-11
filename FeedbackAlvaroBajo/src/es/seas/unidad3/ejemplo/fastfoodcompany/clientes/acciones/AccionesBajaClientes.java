package es.seas.unidad3.ejemplo.fastfoodcompany.clientes.acciones;

import es.seas.unidad3.ejemplo.fastfoodcompany.clientes.vistas.PanelBajaClientes;
import es.seas.unidad3.ejemplo.fastfoodcompany.controllers.DAOClienteImpl;
import es.seas.unidad3.ejemplo.fastfoodcompany.interfaces.DAO;
import es.seas.unidad3.fastfoodcompany.models.Clientes;
import es.seas.unidad3.fastfoodcompany.models.Empleados;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase que realiza las acciones asociadas al panel de baja. El alumno deberá
 * completar la clase de cara al ejercicio feedback.
 *
 * @author Juan José Hernández Alonso
 */
public class AccionesBajaClientes {

    private PanelBajaClientes panel;
    ArrayList<Clientes> lista;
    Clientes clienteE = new Clientes();
    DAO dao = new DAOClienteImpl();

    /**
     * Constructor de la clase.
     *
     * @param panel PanelAltaClientes del que deberá manejar los eventos.
     */
    public AccionesBajaClientes(PanelBajaClientes panel) {
        this.panel = panel;

    }

    /**
     * Inicio estilos con botones ocultos para que segun valla avanzando se
     * vallan activando y que sea mas comprensible de entender
     */
    public void InitStyles() {
        PanelBajaClientes.btnConfirmar.setVisible(false);
        PanelBajaClientes.comboClientes.setVisible(false);
        PanelBajaClientes.nombreLabel.setVisible(false);
        PanelBajaClientes.apellido1Label.setVisible(false);
        PanelBajaClientes.apellido2Label.setVisible(false);
        PanelBajaClientes.txtApellido1.setVisible(false);
        PanelBajaClientes.txtApellido2.setVisible(false);
        PanelBajaClientes.txtNombre.setVisible(false);
        PanelBajaClientes.btnModificar.setVisible(false);
    }

    /**
     * con el boton confirmar haremos que muestre lo que vamos a borrar
     */
    public void confirmar() {
        System.out.println(lista.get(PanelBajaClientes.comboClientes.getSelectedIndex()));
        System.out.println(PanelBajaClientes.comboClientes.getSelectedIndex());
        clienteE = lista.get(PanelBajaClientes.comboClientes.getSelectedIndex());
        PanelBajaClientes.txtNombre.setText(clienteE.getNombre());
        PanelBajaClientes.txtNombre.setVisible(true);
        PanelBajaClientes.txtApellido1.setText(clienteE.getApellidoPaterno());
        PanelBajaClientes.txtApellido1.setVisible(true);
        PanelBajaClientes.txtApellido2.setText(clienteE.getApellidoMaterno());
        PanelBajaClientes.txtApellido2.setVisible(true);
        PanelBajaClientes.nombreLabel.setVisible(true);
        PanelBajaClientes.apellido1Label.setVisible(true);
        PanelBajaClientes.apellido2Label.setVisible(true);
        PanelBajaClientes.btnModificar.setVisible(true);
    }

    /**
     * para buscar en la base de datos nos hace falta un método de este estilo
     * asi creo que a la hora de borrar sera mas facil
     */
    public void botonBuscar() {
        try {
            lista = (ArrayList<Clientes>) dao.buscar(PanelBajaClientes.txtBucar.getText().trim());
            PanelBajaClientes.comboClientes.removeAllItems();
            if (!lista.isEmpty()) {
                PanelBajaClientes.comboClientes.setVisible(true);
                PanelBajaClientes.btnConfirmar.setVisible(true);
                for (int i = 0; i < lista.size(); i++) {
                    PanelBajaClientes.comboClientes.addItem(lista.get(i).getNombre());
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe cliente con ese nombre", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                PanelBajaClientes.txtBucar.requestFocus();
                // cargarClientes();
                PanelBajaClientes.comboClientes.setSelectedItem("");
            }
            PanelBajaClientes.txtBucar.setText("");
        } catch (Exception ex) {
            System.out.println("!ERROR¡ al buscar" + ex);
        }
    }

    /**
     * para terminar de eliminar la entrada en la base de datos, volvemos a
     * empezar desde esta ventana, vacio los objetos para que no se quede en
     * memoria
     */
    public void eliminar() {
        try {
            dao.eliminar(clienteE.getId());
            InitStyles();
            PanelBajaClientes.comboClientes.removeAllItems();
            clienteE = null; // para limpiar la memoria marcamos el objeto como inacesible
            lista.clear(); //limpiamos el Array

        } catch (Exception ex) {
            System.out.println("Error al eliminar" + ex);
        }

    }

}
