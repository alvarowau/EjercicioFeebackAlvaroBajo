package es.seas.unidad3.ejemplo.fastfoodcompany.principal;

/**
 * Clase lanzadora de la aplicación.
 *
 * @author Juan José Hernández Alonso
 */
public class LanzadorAplicacion {

    /**
     * Método lanzador.
     *
     * @param args String[] parámetros.
     */
    public static void main(String[] args) {
        FastFoodCompanyFrameLogin login = new FastFoodCompanyFrameLogin();
        login.setVisible(true);
        
        //FastFoodCompanyFrame frame = new FastFoodCompanyFrame();
        //frame.setVisible(true);
    }
}
