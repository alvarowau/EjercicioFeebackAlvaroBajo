package es.seas.unidad3.ejemplo.fastfoodcompany.clientes.acciones;

import es.seas.unidad3.ejemplo.fastfoodcompany.clientes.vistas.PanelAltaClientes;
import es.seas.unidad3.fastfoodcompany.bbdd.GestionSql;
import es.seas.unidad3.fastfoodcompany.models.Clientes;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import es.seas.unidad3.ejemplo.fastfoodcompany.controllers.DAOClienteImpl;
import es.seas.unidad3.ejemplo.fastfoodcompany.interfaces.DAO;


/**
 * Clase que realiza las acciones asociadas al panel de alta.
 *
 * @author Juan José Hernández Alonso
 */
public class AccionesAltaClientes extends GestionSql {
    
    


    private PanelAltaClientes panel;

    /**
     * Constructor de la clase.
     *
     * @param panel PanelAltaClientes del que deberá manejar los eventos.
     */
    public AccionesAltaClientes(PanelAltaClientes panel) {
        this.panel = panel;
    }

    /**
     * Método que recupera los valores del formulario y los guarda en la
     * estructura de almacenamiento propuesta y existente en el frame.
     */
    public void guardarCliente()  {
        ArrayList filaCliente = new ArrayList();
        filaCliente.add(panel.getTxtNombre().getText().trim());
        filaCliente.add(panel.getTxtApellido1().getText().trim());
        filaCliente.add(panel.getTxtApellido2().getText().trim());
        panel.getFrame().getClientesGuardados().add(filaCliente);
        
        Clientes cliente = new Clientes();
        cliente.setNombre((String) filaCliente.get(0));
        cliente.setApellidoPaterno((String) filaCliente.get(1));
        cliente.setApellidoMaterno((String) filaCliente.get(2));
        DAO dao = new DAOClienteImpl();
        try {
            dao.registrar(cliente);
            JOptionPane.showMessageDialog(null, "Cliente Registrado correctamente", "AVISO", javax.swing.JOptionPane.WIDTH);
        } catch (Exception ex) {
            System.out.println("Fallo en registrar cliente" + ex.toString());
        }
        
        System.out.println(filaCliente);
    }
    
}
