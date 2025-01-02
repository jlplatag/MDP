package es.unex.cum.mdp.ef2;

/**
 * Clase principal que demuestra el uso de diferentes tipos de bingos.
 * 
 * Esta clase se va a utilizar principalmente para probar si funciona
 * correctamente Bingo tal y como se muestra en el siguiente código.
 * 
 * @author Clara Galván Bermúdez
 * @author José Luis Plata Gallardo
 */
public class mainBingo {
	/**
	 * Método principal que demuestra el uso de diferentes tipos de cartones.
	 *
	 * @param args Los argumentos de la línea de comandos (no se utilizan en este
	 *             ejemplo).
	 */
	public static void main(String[] args) {
		Bingo b1 = null;
		String tipoBingo = "Bingo75";
		if (tipoBingo.equals("Bingo75")) {
			b1 = new Bingo75(2.0F);
		} else {
			b1 = new Bingo80(2.0F);
		}
		Estadistica[] estadistica = new Estadistica[90 + 1];
		for (int i = 0; i < estadistica.length; i++)
			estadistica[i] = new Estadistica(i);
		String tipo = "75H";
		Usuario u1 = new Usuario("luis1", "luis1", "luis", 10000000);
		// Usuario u2 = new Usuario("luis2", "luis2", "luis2", 100);
		// Usuario u3 = new Usuario("luis3", "luis3", "luis3", 100);

		for (int i = 0; i < 5000; i++) {
			b1.crearCarton(tipo, u1);
			// b1.crearCarton(tipo, u2);
			// b1.crearCarton(tipo, u3);
		}
		System.out.println(b1.jugar(estadistica));
	}

}
