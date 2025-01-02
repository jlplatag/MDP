package es.unex.cum.mdp.ef2.carton;

import es.unex.cum.mdp.ef2.Usuario;

/**
 * Patrón builder para crear instancias de diferentes tipos de cartón.
 * 
 * @author José Luis Plata Gallardo
 * @author Clara Galván Bermúdez
 * 
 */
public class CartonBuilder {
	private ICarton b;

	/**
	 * Construye un CartonBuilder basado en el tipo especificado de cartón de bingo.
	 * Los tipos admitidos incluyen "75M", "75V", "75H", "80H", "80V", "80M", "90M",
	 * "90H" y "90V". Los tipos asociados a 90 están comentados para evitar errores.
	 * Sólo funcionaran correctamente las clases implementadas: 75H y 80M
	 *
	 * @param tipo El tipo de cartón de bingo que se construirá.
	 */
	public CartonBuilder(String tipo) {
		if (tipo.equals("75M")) {
			// b = new Carton75M();
		}
		if (tipo.equals("75V")) {
			// b = new Carton75V();
		} else if (tipo.equals("75H")) {
			b = new Carton75H();
		} else if (tipo.equals("80H")) {
			// b = new Carton80H();
		} else if (tipo.equals("80V")) {
			// b = new Carton80V();
		} else if (tipo.equals("80M")) {
			b = new Carton80M();
		} else if (tipo.equals("90M")) {
			// b= new Carton90M();
		} else if (tipo.equals("90H")) {
			// b= new Carton90H();
		} else if (tipo.equals("90V")) {
			// b= new Carton90V();
		}

	}

	/**
	 * Establece el ID para el cartón de bingo que se está construyendo.
	 *
	 * @param id El ID que se va a establecer.
	 * @return La instancia de CartonBuilder para encadenar métodos.
	 */
	public CartonBuilder withId(final int id) {
		this.b.setId(id);
		return this;
	}

	/**
	 * Establece el usuario para el cartón de bingo que se está construyendo.
	 *
	 * @param u El usuario que se va a establecer.
	 * @return El CartonBuilder para encadenar métodos.
	 */
	public CartonBuilder withUser(final Usuario u) {
		this.b.setUser(u);
		return this;
	}

	/**
	 * Llama al método repartir del cartón seleccionado, encargado de rellenar un
	 * cartón aleatoriamente.
	 *
	 * @return El cartón de bingo relleno.
	 */
	public ICarton build() {
		b.repartir();
		return b;

	}

}
