package es.unex.cum.mdp.ef3.modelo;

/**
 * La excepción UsuarioNoAutenticado es lanzada cuando un usuario intenta
 * acceder a un recurso que requiere autenticación, pero el usuario no ha sido
 * autenticado de manera correcta.
 * 
 * Esta excepción extiende de la clase Exception de Java. Además, sobrescribe el
 * método getMessage() para devolver un mensaje de error predeterminado
 * indicando que el usuario no ha sido autenticado de manera correcta.
 * 
 * @author Clara Galván Bermúdez 
 * @author José Luis Plata Gallardo
 * @see Exception
 */
public class UsuarioNoAutenticado extends Exception {
	/**
	 * Identificador único de versión para la serialización de objetos de esta
	 * clase. Se utiliza para controlar la compatibilidad de versiones durante la
	 * serialización y deserialización de objetos.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto de la clase UsuarioNoAutenticado.
	 */
	public UsuarioNoAutenticado() {
	}

	/**
	 * Constructor parametrizado de la clase UsuarioNoAutenticado que permite
	 * aceptar un mensaje personalizado para explicar la situación de no
	 * autenticación del usuario.
	 * 
	 * @param msg Mensaje personalizado que explica la situación de no
	 *            autenticación.
	 */
	public UsuarioNoAutenticado(String msg) {
		super(msg);
	}

	/**
	 * Devuelve un mensaje que indica que el usuario no ha sido autenticado de
	 * manera correcta.
	 * 
	 * @return Mensaje de error indicando la no autenticación del usuario.
	 */
	@Override
	public String getMessage() {
		return "\n ERROR, el usuario NO ha sido autenticado de forma correcta.";
	}
}
