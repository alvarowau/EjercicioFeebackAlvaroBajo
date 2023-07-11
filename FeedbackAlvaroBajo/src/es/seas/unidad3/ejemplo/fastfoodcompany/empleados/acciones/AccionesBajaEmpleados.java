package es.seas.unidad3.ejemplo.fastfoodcompany.empleados.acciones;

import es.seas.unidad3.ejemplo.fastfoodcompany.controllers.DAOEmpleadosImpl;
import es.seas.unidad3.ejemplo.fastfoodcompany.empleados.vistas.PanelBajaEmpleados;
import es.seas.unidad3.ejemplo.fastfoodcompany.interfaces.DAO;
import es.seas.unidad3.fastfoodcompany.models.Empleados;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase que realiza las acciones asociadas al panel de baja. El alumno deberá
 * completar la clase de cara al ejercicio feedback.
 *
 * @author Juan José Hernández Alonso
 */
public class AccionesBajaEmpleados {

    private PanelBajaEmpleados panel;
    ArrayList<Empleados> lista;
    Empleados empleadoE = new Empleados();
    DAO dao = new DAOEmpleadosImpl();

    /**
     * Constructor de la clase.
     *
     * @param panel PanelAltaClientes del que deberá manejar los eventos.
     */
    public AccionesBajaEmpleados(PanelBajaEmpleados panel) {
        this.panel = panel;

    }

    /**
     * Inicio estilos con botones ocultos para que segun valla avanzando se
     * vallan activando y que sea mas comprensible de entender
     */
    public void InitStyles() {
        PanelBajaEmpleados.btnConfirmar.setVisible(false);
        PanelBajaEmpleados.comboClientes.setVisible(false);
        PanelBajaEmpleados.nombreLabel.setVisible(false);
        PanelBajaEmpleados.apellido1Label.setVisible(false);
        PanelBajaEmpleados.apellido2Label.setVisible(false);
        PanelBajaEmpleados.txtApellido1.setVisible(false);
        PanelBajaEmpleados.txtApellido2.setVisible(false);
        PanelBajaEmpleados.txtNombre.setVisible(false);
        PanelBajaEmpleados.btnModificar.setVisible(false);
    }

    /**
     * con el boton confirmar haremos que muestre lo que vamos a borrar
     */
    public void confirmar() {
        System.out.println(lista.get(PanelBajaEmpleados.comboClientes.getSelectedIndex()));
        System.out.println(PanelBajaEmpleados.comboClientes.getSelectedIndex());
        empleadoE = lista.get(PanelBajaEmpleados.comboClientes.getSelectedIndex());
        PanelBajaEmpleados.txtNombre.setText(empleadoE.getNombre());
        PanelBajaEmpleados.txtNombre.setVisible(true);
        PanelBajaEmpleados.txtApellido1.setText(empleadoE.getApellidoPaterno());
        PanelBajaEmpleados.txtApellido1.setVisible(true);
        PanelBajaEmpleados.txtApellido2.setText(empleadoE.getApellidoMaterno());
        PanelBajaEmpleados.txtApellido2.setVisible(true);
        PanelBajaEmpleados.nombreLabel.setVisible(true);
        PanelBajaEmpleados.apellido1Label.setVisible(true);
        PanelBajaEmpleados.apellido2Label.setVisible(true);
        PanelBajaEmpleados.btnModificar.setVisible(true);
    }

    /**
     * para buscar en la base de datos nos hace falta un método de este estilo
     * asi creo que a la hora de borrar sera mas facil
     */
    public void botonBuscar() {
        try {
            lista = (ArrayList<Empleados>) dao.buscar(PanelBajaEmpleados.txtBucar.getText().trim());
            PanelBajaEmpleados.comboClientes.removeAllItems();
            if (!lista.isEmpty()) {
                PanelBajaEmpleados.comboClientes.setVisible(true);
                PanelBajaEmpleados.btnConfirmar.setVisible(true);
                for (int i = 0; i < lista.size(); i++) {
                    PanelBajaEmpleados.comboClientes.addItem(lista.get(i).getNombre());
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe cliente con ese nombre", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                PanelBajaEmpleados.txtBucar.requestFocus();
                // cargarClientes();
                PanelBajaEmpleados.comboClientes.setSelectedItem("");
            }
            PanelBajaEmpleados.txtBucar.setText("");
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
            dao.eliminar(empleadoE.getId());
            InitStyles();
            PanelBajaEmpleados.comboClientes.removeAllItems();
            empleadoE = null; // para limpiar la memoria marcamos el objeto como inacesible
            lista.clear(); //limpiamos el Array

        } catch (Exception ex) {
            System.out.println("Error al eliminar" + ex);
        }
    }


}
