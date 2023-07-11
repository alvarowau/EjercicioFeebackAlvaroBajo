package es.seas.unidad3.ejemplo.fastfoodcompany.empleados.acciones;




import es.seas.unidad3.ejemplo.fastfoodcompany.controllers.DAOEmpleadosImpl;
import es.seas.unidad3.ejemplo.fastfoodcompany.empleados.vistas.PanelEditaEmpleados;
import es.seas.unidad3.ejemplo.fastfoodcompany.interfaces.DAO;
import es.seas.unidad3.fastfoodcompany.models.Empleados;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase que realiza las acciones asociadas al panel de edición. El alumno
 * deberá completar la clase de cara al ejercicio feedback.
 *
 * @author Juan José Hernández Alonso
 */
public class AccionesEditaEmpleados {

    private PanelEditaEmpleados panel;
    ArrayList<Empleados> lista;
    Empleados empleadoE = new Empleados();
    DAO dao = new DAOEmpleadosImpl();

    public AccionesEditaEmpleados(PanelEditaEmpleados panel) {
        this.panel = panel;
    }

    public void InitStyles() {
        PanelEditaEmpleados.btnConfirmar.setVisible(false);
        PanelEditaEmpleados.comboClientes.setVisible(false);
        PanelEditaEmpleados.nombreLabel.setVisible(false);
        PanelEditaEmpleados.apellido1Label.setVisible(false);
        PanelEditaEmpleados.apellido2Label.setVisible(false);
        PanelEditaEmpleados.txtApellido1.setVisible(false);
        PanelEditaEmpleados.txtApellido2.setVisible(false);
        PanelEditaEmpleados.txtNombre.setVisible(false);
        PanelEditaEmpleados.btnModificar.setVisible(false);
        PanelEditaEmpleados.PermisoLabel.setVisible(false);
        PanelEditaEmpleados.comboPermiso.setVisible(false);
        
    }

    /**
     * con el boton confirmar haremos que muestre lo que vamos a editar
     */
    public void confirmar() {
        System.out.println(lista.get(PanelEditaEmpleados.comboClientes.getSelectedIndex()));
        System.out.println(PanelEditaEmpleados.comboClientes.getSelectedIndex());
        empleadoE = lista.get(PanelEditaEmpleados.comboClientes.getSelectedIndex());
        PanelEditaEmpleados.txtNombre.setText(empleadoE.getNombre());
        PanelEditaEmpleados.txtNombre.setVisible(true);
        PanelEditaEmpleados.txtApellido1.setText(empleadoE.getApellidoPaterno());
        PanelEditaEmpleados.txtApellido1.setVisible(true);
        PanelEditaEmpleados.txtApellido2.setText(empleadoE.getApellidoMaterno());
        PanelEditaEmpleados.txtApellido2.setVisible(true);
        PanelEditaEmpleados.PermisoLabel.setVisible(true);
        PanelEditaEmpleados.comboPermiso.setVisible(true);
        if(empleadoE.getPermiso()){
            PanelEditaEmpleados.comboPermiso.setSelectedItem("Si");
        }else{
            PanelEditaEmpleados.comboPermiso.setSelectedItem("No");
        }
        PanelEditaEmpleados.nombreLabel.setVisible(true);
        PanelEditaEmpleados.apellido1Label.setVisible(true);
        PanelEditaEmpleados.apellido2Label.setVisible(true);
        PanelEditaEmpleados.btnModificar.setVisible(true);
    }

    /**
     * para buscar en la base de datos nos hace falta un método de este estilo
     * asi creo que a la hora de editar sera mas facil
     */
    public void botonBuscar() {
        try {
            lista = (ArrayList<Empleados>) dao.buscar(PanelEditaEmpleados.txtBucar.getText().trim());
            PanelEditaEmpleados.comboClientes.removeAllItems();
            if (!lista.isEmpty()) {
                PanelEditaEmpleados.comboClientes.setVisible(true);
                PanelEditaEmpleados.btnConfirmar.setVisible(true);
                for (int i = 0; i < lista.size(); i++) {
                    PanelEditaEmpleados.comboClientes.addItem(lista.get(i).getNombre());
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe cliente con ese nombre", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                PanelEditaEmpleados.txtBucar.requestFocus();
                // cargarClientes();
                PanelEditaEmpleados.comboClientes.setSelectedItem("");
            }
            PanelEditaEmpleados.txtBucar.setText("");
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

        if (!PanelEditaEmpleados.txtNombre.getText().isEmpty()) {
            if (!PanelEditaEmpleados.txtApellido1.getText().isEmpty()) {
                if (!PanelEditaEmpleados.txtApellido2.getText().isEmpty()) {
                    try {
                        empleadoE.setNombre(PanelEditaEmpleados.txtNombre.getText().trim());
                        empleadoE.setApellidoPaterno(PanelEditaEmpleados.txtApellido1.getText().trim());
                        empleadoE.setApellidoMaterno(PanelEditaEmpleados.txtApellido2.getText().trim());
                        if("Si".equals(PanelEditaEmpleados.comboPermiso.getSelectedItem().toString())){
                            empleadoE.setPermiso(true);
                        }else{
                            empleadoE.setPermiso(false);
                        }
                        dao.modificar(empleadoE);
                        JOptionPane.showMessageDialog(null, "Empleado modificado con exito", "AVISO", javax.swing.JOptionPane.HEIGHT);
                        InitStyles();
                        PanelEditaEmpleados.comboClientes.removeAllItems();
                        empleadoE = null; // para limpiar la memoria marcamos el objeto como inacesible
                        lista.clear(); //limpiamos el Array

                    } catch (Exception ex) {
                        System.out.println("Error al eliminar" + ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "'Apellido 2' esta vacio", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                    PanelEditaEmpleados.txtApellido2.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "'Apellido 1' esta vacio", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                PanelEditaEmpleados.txtApellido1.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "'Nombre' esta vacio", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
            PanelEditaEmpleados.txtNombre.requestFocus();
        }

    }
}
