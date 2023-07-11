package es.seas.unidad3.ejemplo.fastfoodcompany.empleados.acciones;


import es.seas.unidad3.fastfoodcompany.bbdd.GestionSql;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import es.seas.unidad3.ejemplo.fastfoodcompany.controllers.DAOEmpleadosImpl;
import es.seas.unidad3.ejemplo.fastfoodcompany.empleados.vistas.PanelAltaEmpleados;
import es.seas.unidad3.ejemplo.fastfoodcompany.interfaces.DAO;
import es.seas.unidad3.fastfoodcompany.models.Empleados;


/**
 * Clase que realiza las acciones asociadas al panel de alta.
 *
 * @author Juan José Hernández Alonso
 */
public class AccionesAltaEmpleados extends GestionSql {
    
    


    private PanelAltaEmpleados panel;

    /**
     * Constructor de la clase.
     *
     * @param panel PanelAltaClientes del que deberá manejar los eventos.
     */
    public AccionesAltaEmpleados(PanelAltaEmpleados panel) {
        this.panel = panel;
    }

    /**
     * Método que recupera los valores del formulario y los guarda en la
     * estructura de almacenamiento propuesta y existente en el frame.
     */
    public void guardarEmpleado()  {
        ArrayList filaEmpleado = new ArrayList();
        filaEmpleado.add(panel.getTxtNombre().getText().trim());
        filaEmpleado.add(panel.getTxtApellido1().getText().trim());
        filaEmpleado.add(panel.getTxtApellido2().getText().trim());
        filaEmpleado.add(panel.getJPassword());
        if("Si".equals(PanelAltaEmpleados.comboPermiso.getSelectedItem().toString())){
            filaEmpleado.add(true);
        }else{
            filaEmpleado.add(false);
        }
        panel.getFrame().getClientesGuardados().add(filaEmpleado);
        
        Empleados empleado = new Empleados();
        empleado.setNombre((String) filaEmpleado.get(0));
        empleado.setApellidoPaterno((String) filaEmpleado.get(1));
        empleado.setApellidoMaterno((String) filaEmpleado.get(2));
        empleado.setPass((String)filaEmpleado.get(3));
        empleado.setPermiso((boolean) filaEmpleado.get(4));
        
        System.out.println(empleado.toString());
        DAO dao = new DAOEmpleadosImpl();
        try {
            dao.registrar(empleado);
            JOptionPane.showMessageDialog(null, "Empleado Registrado correctamente", "AVISO", javax.swing.JOptionPane.WIDTH);
        } catch (Exception ex) {
            System.out.println("Fallo en registrar cliente" + ex.toString());
        }
        
        
    }
    
}
