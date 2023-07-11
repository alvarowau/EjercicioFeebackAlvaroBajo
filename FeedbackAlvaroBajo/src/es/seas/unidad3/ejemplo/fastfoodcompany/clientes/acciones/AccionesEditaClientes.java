package es.seas.unidad3.ejemplo.fastfoodcompany.clientes.acciones;

import es.seas.unidad3.ejemplo.fastfoodcompany.clientes.vistas.PanelEditaClientes;
import es.seas.unidad3.ejemplo.fastfoodcompany.controllers.DAOClienteImpl;
import es.seas.unidad3.ejemplo.fastfoodcompany.interfaces.DAO;
import es.seas.unidad3.fastfoodcompany.models.Clientes;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase que realiza las acciones asociadas al panel de edición. El alumno
 * deberá completar la clase de cara al ejercicio feedback.
 *
 * @author Juan José Hernández Alonso
 */
public class AccionesEditaClientes {

    private PanelEditaClientes panel;
    ArrayList<Clientes> lista;
    Clientes clienteE = new Clientes();
    DAO dao = new DAOClienteImpl();

    public AccionesEditaClientes(PanelEditaClientes panel) {
        this.panel = panel;
    }

    public void InitStyles() {
        PanelEditaClientes.btnConfirmar.setVisible(false);
        PanelEditaClientes.comboClientes.setVisible(false);
        PanelEditaClientes.nombreLabel.setVisible(false);
        PanelEditaClientes.apellido1Label.setVisible(false);
        PanelEditaClientes.apellido2Label.setVisible(false);
        PanelEditaClientes.txtApellido1.setVisible(false);
        PanelEditaClientes.txtApellido2.setVisible(false);
        PanelEditaClientes.txtNombre.setVisible(false);
        PanelEditaClientes.btnModificar.setVisible(false);
    }

    /**
     * con el boton confirmar haremos que muestre lo que vamos a editar
     */
    public void confirmar() {
        System.out.println(lista.get(PanelEditaClientes.comboClientes.getSelectedIndex()));
        System.out.println(PanelEditaClientes.comboClientes.getSelectedIndex());
        clienteE = lista.get(PanelEditaClientes.comboClientes.getSelectedIndex());
        PanelEditaClientes.txtNombre.setText(clienteE.getNombre());
        PanelEditaClientes.txtNombre.setVisible(true);
        PanelEditaClientes.txtApellido1.setText(clienteE.getApellidoPaterno());
        PanelEditaClientes.txtApellido1.setVisible(true);
        PanelEditaClientes.txtApellido2.setText(clienteE.getApellidoMaterno());
        PanelEditaClientes.txtApellido2.setVisible(true);
        PanelEditaClientes.nombreLabel.setVisible(true);
        PanelEditaClientes.apellido1Label.setVisible(true);
        PanelEditaClientes.apellido2Label.setVisible(true);
        PanelEditaClientes.btnModificar.setVisible(true);
    }

    /**
     * para buscar en la base de datos nos hace falta un método de este estilo
     * asi creo que a la hora de editar sera mas facil
     */
    public void botonBuscar() {
        try {
            lista = (ArrayList<Clientes>) dao.buscar(PanelEditaClientes.txtBucar.getText().trim());
            PanelEditaClientes.comboClientes.removeAllItems();
            if (!lista.isEmpty()) {
                PanelEditaClientes.comboClientes.setVisible(true);
                PanelEditaClientes.btnConfirmar.setVisible(true);
                for (int i = 0; i < lista.size(); i++) {
                    PanelEditaClientes.comboClientes.addItem(lista.get(i).getNombre());
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe cliente con ese nombre", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                PanelEditaClientes.txtBucar.requestFocus();
                // cargarClientes();
                PanelEditaClientes.comboClientes.setSelectedItem("");
            }
            PanelEditaClientes.txtBucar.setText("");
        } catch (Exception ex) {
            System.out.println("!ERROR¡ al buscar" + ex);
        }
    }

    /**
     * para terminar de modificar la entrada en la base de datos, volvemos a
     * empezar desde esta ventana, vacio los objetos para que no se quede en
     * memoria
     */
    public void editar() {

        if (!PanelEditaClientes.txtNombre.getText().isEmpty()) {
            if (!PanelEditaClientes.txtApellido1.getText().isEmpty()) {
                if (!PanelEditaClientes.txtApellido2.getText().isEmpty()) {
                    try {
                        clienteE.setNombre(PanelEditaClientes.txtNombre.getText().trim());
                        clienteE.setApellidoPaterno(PanelEditaClientes.txtApellido1.getText().trim());
                        clienteE.setApellidoMaterno(PanelEditaClientes.txtApellido2.getText().trim());
                        dao.modificar(clienteE);
                        JOptionPane.showMessageDialog(null, "Cliente modificado con exito", "AVISO", javax.swing.JOptionPane.HEIGHT);
                        InitStyles();
                        PanelEditaClientes.comboClientes.removeAllItems();
                        clienteE = null; // para limpiar la memoria marcamos el objeto como inacesible
                        lista.clear(); //limpiamos el Array

                    } catch (Exception ex) {
                        System.out.println("Error al eliminar" + ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "'Apellido 2' esta vacio", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                    PanelEditaClientes.txtApellido2.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "'Apellido 1' esta vacio", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                PanelEditaClientes.txtApellido1.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "'Nombre' esta vacio", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
            PanelEditaClientes.txtNombre.requestFocus();
        }

    }
}
