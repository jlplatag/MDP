package es.unex.cum.mdp.ef3.modelo;

/**
 * La clase FactoriaBingo (patrón de factoría simple) es responsable de crear
 * instancias de la clase Bingo basadas en un tipo específico y un precio dado.
 * 
 * Permite la creación de distintos tipos de Bingo dependiendo del parámetro
 * proporcionado. En este caso, se pueden crear instancias de Bingo75 o Bingo80
 * pues, son los bingos implementados.
 * 
 * @author Clara Galván Bermúdez
 * @author José Luis Plata Gallardo
 */
public class FactoriaBingo {
	/**
	 * Este es el constructor predeterminado de la clase FactoriaBingo. No se define
	 * un constructor explícito ya que todos los métodos de la clase son estáticos.
	 */
	FactoriaBingo() {
		// No hace falta implementar lógica aquí, ya que no se requiere un constructor
		// explícito.
	}

	/**
	 * Construye un objeto de tipo Bingo basado en el tipo especificado y el precio
	 * proporcionado.
	 *
	 * @param tipo   Tipo de Bingo a construir. Debe ser una cadena que indique el
	 *               tipo de Bingo como son: "75" y "80".
	 * @param precio Precio del Bingo a crear.
	 * @return Una instancia de la clase Bingo según el tipo especificado y el
	 *         precio proporcionado.
	 */
	public static Bingo buildBingo(String tipo, float precio) {
		Bingo b = null;
		if (tipo.contains("80")) {
			b = new Bingo80(precio);
		} else if (tipo.contains("75")) {
			b = new Bingo75(precio);
		} else {
			// b = new Bingo90(precio);
		}
		return b;
	}

}