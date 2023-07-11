
package es.seas.unidad3.ejemplo.fastfoodcompany.interfaces;

import java.util.List;

/**
 * Dado que los metodos que buscamos para esta aplicacion son los mismo para clietne
 * y empleado creo una interface generica
 * @author AlvaroBajo
 * @param <T>
 */
public interface DAO <T>{

    /**
     * Registra una nueva entrada en la base de datos
     * @param X este es el objeto que vamos a enviar (cliente o empleado)
     * @throws Exception lazara un error si hay problemas con al base de datos
     */
    public void registrar(T X) throws Exception;

    /**
     * Modifica una nueva entrada en la base de datos
     * @param X este es el objeto que vamos a enviar (cliente o empleado)
     * @throws Exception lazara un error si hay problemas con al base de datos
     */
    public void modificar(T X) throws Exception;

    /**
     * Elimina una nueva entrada en la base de datos
     * @param id le pasaremos un id para eliminarlo de la base de datos
     * @throws Exception lazara un error si hay problemas con al base de datos
     */
    public void eliminar(int id) throws Exception;

    /**
     * Lista todos los registros de la base de datos, esto lo empleare para
     * la elecion de modificacion y eliminacion 
     * @return nos devolvera una lista de objetos 
     * @throws Exception lazara un error si hay problemas con al base de datos
     */
    public List <T> listar() throws Exception;

    /**
     * Lista uno o varios objetos que tengan el mismo nombre en la base de datos
     * esto lo utilizare para afinar el borrado o la modificacion
     * @param nombre le pasaremos un nombre para que busque en la base de datos
     * @return
     * @throws Exception lazara un error si hay problemas con al base de datos
     */
    public List <T> buscar( String nombre) throws Exception;
    
}
