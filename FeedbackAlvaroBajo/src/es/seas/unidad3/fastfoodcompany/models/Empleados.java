
package es.seas.unidad3.fastfoodcompany.models;

/**
 *
 * @author AlvaroBajo
 */
public class Empleados extends Persona {

    private boolean permiso;
    private String pass;

    public Empleados(boolean permiso, String nombre, String apellidoPaterno, String apellidoMaterno) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.permiso = permiso;
    }

    public Empleados() {
        
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    
    public boolean getPermiso() {
        return permiso;
    }

    public void setPermiso(boolean permiso) {
        this.permiso = permiso;
    }

    @Override
    public String toString() {
        return super.toString() +"Empleados{" + "permiso=" + permiso + '}';
    }

 

 
    
    
    


    
    
    
}
