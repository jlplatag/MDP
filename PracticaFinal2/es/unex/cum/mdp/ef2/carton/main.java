package es.unex.cum.mdp.ef2.carton;

/**
 * Clase principal que demuestra el uso de diferentes tipos de cartones.
 * 
 * Esta clase se va a utilizar principalmente para probar si funciona
 * correctamente la jerarquía cartón como puede verse en el siguiente algoritmo.
 * 
 * @author Clara Galván Bermúdez
 * @author José Luis Plata Gallardo
 */
public class main {
	/**
	 * Método principal que demuestra el uso de diferentes tipos de cartones.
	 *
	 * @param args Los argumentos de la línea de comandos (no se utilizan en este
	 *             ejemplo).
	 */
	public static void main(String[] args) {
		String tipo = "75H";
		ICarton c = null;
		if (tipo.equals("75H")) {
			c = new Carton75H();
		} else {
			c = new Carton80M();
		}
		c.repartir();
		System.out.println(c.toString());

	}
}
