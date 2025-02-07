package es.unex.cum.mdp.ef3.modelo;



/**
 * La interfaz ICarton es implementada por las clases que hacen referencia a un
 * cartón del Bingo. Este cartón puede ser cartón 75, cartón 80 y cartón 90.
 * Además, la simulación de cada cartón puede ser diferente, es decir, el cartón
 * puede ser simulado mediante una matriz, un HashMap o un vector. En este caso,
 * las clases implementadoras son: Carton75H (cartón 75 con un HashMap) y
 * Carton80M (cartón 80 con una matriz).
 * 
 * En la interfaz se definen los métodos que después las clases implementadoras
 * deben desarrollar según el tipo de cartón. Estos métodos son los esenciales
 * para el correcto funcionamiento del cartón dentro del juego.
 * 
 * @author Clara Galván Bermúdez 
 * @author José Luis Plata Gallardo 
 */
public interface ICarton {

	/**
	 * Permite obtener el estado actual del cartón.
	 * 
	 * @return Estado actual del cartón.
	 */
	public EstadoCarton getEstado();

	/**
	 * Permite obtener la cantidad de aciertos total en el cartón hasta el momento
	 * de la llamada al método.
	 * 
	 * @return Cantidad de aciertos total en el cartón.
	 */
	public int getAciertos();

	/**
	 * Permite obtener el identificador propio del cartón.
	 * 
	 * @return Identificador único del cartón.
	 */
	public int getId();

	/**
	 * Permite modificar el estado del cartón estableciendo como estado del mismo el
	 * pasado por parámetro.
	 * 
	 * @param id Es el nuevo identificador del cartón.
	 */
	public void setId(int id);

	/**
	 * Permite obtener el usuario del cartón.
	 * 
	 * @return Usuario del cartón.
	 */
	public Usuario getUser();

	/**
	 * Permite modificar el usuario del cartón estableciendo como usuario del mismo
	 * el pasado por parámetro.
	 * 
	 * @param user El nuevo usuario del cartón.
	 */
	public void setUser(Usuario user);

	/**
	 * Se encarga de comprobar si se ha completado una línea cualquiera en el cartón
	 * y por tanto, se puede cantar bingo.
	 * 
	 * @return true si se ha completado una línea, false en caso contrario.
	 */
	public boolean comprobarLinea();

	/**
	 * Se encarga de comprobar si se ha completado una línea en una fila específica
	 * del cartón. En caso de que sí, quiere decir que se puede cantar línea.
	 * 
	 * @param fila Fila a comprobar con la que se puede cantar línea.
	 * @return true si se ha completado una línea en la fila especificada, false en
	 *         caso contrario.
	 */
	public boolean comprobarLinea(int fila);

	/**
	 * /** Verifica si el número recibido por parámetro está presente en el cartón y
	 * por tanto, pueda ser cantado en caso de encontrarse dentro del mismo.Además,
	 * en caso positivo se actualiza el estado de la celda correspondiente a ese
	 * número.
	 * 
	 * @param numero Número a comprobar su existencia dentro del cartón.
	 * @return true si el número está presente y por tanto, puede ser cantado, false
	 *         en caso adverso.
	 */
	public boolean recibirNumero(int numero);

	/**
	 * Se encarga de comprobar si se han marcado todos los números del cartón (los
	 * 20 números) pues, se canta bingo cuando todos los números de un cartón han
	 * sido marcados dentro del mismo.
	 * 
	 * @return true si se han marcado todos los números del cartón, false en caso
	 *         contrario.
	 */
	public boolean comprobarBingo();

	/**
	 * Se encarga de comprobar si el cartón contiene la cantidad total de números
	 * especiales correspondientes de acuerdo al tipo de cartón (en caso del cartón
	 * 75, habrá 2 números especiales y en el caso del 80, 3).
	 * 
	 * @return true si el cartón contiene la cantidad total de números especiales
	 *         correspondiente, falso en caso adverso.
	 */
	public boolean comprobarEspeciales();

	/**
	 * Es el método que se encarga de crear un cartón de forma automática. Es decir,
	 * crea un cartón repartiendo números aleatorios (pero siempre dentro del rango
	 * de cada uno de los cartones) en el mismo. Además, incluye los números
	 * especiales que hay en el cartón.
	 * 
	 * @return true si se ha creado el cartón mediante la repartición de números de
	 *         forma correcta, false en caso contrario.
	 */
	public boolean repartir();

	/**
	 * Se encarga de añadir una celda específica al cartón.
	 * 
	 * @param c Celda que se va adherir al cartón.
	 * @return true en caso de que sí se añade correctamente la celda al cartón,
	 *         false si no se añade.
	 */
	public boolean addNumero(CeldaCarton c);

	/**
	 * Se encarga de añadir como especial un número al cartón. Esto será posible si
	 * el número existe dentro del cartón y si la cantidad de números especiales del
	 * mismo, es inferior al número máximo de números especiales.
	 * 
	 * @param num Numero especial a agregar al cartón.
	 * @return true si se ha podido agregar correctamente como especial,false en
	 *         caso adverso.
	 */
	public boolean addEspecial(int num);

	/**
	 * Permite obtener el precio del cartón.
	 * 
	 * @return Valor del precio del cartón.
	 */
	public float getPrecio();

	/**
	 * Permite modificar el valor del premio del cartón estableciendo como valor del
	 * premio el pasado por parámetro.
	 * 
	 * @param valor Valor del premio a establecer.
	 */
	public void setPremio(float valor);

	/**
	 * Permite obtener el valor del premio del cartón.
	 * 
	 * @return Valor del premio del cartón.
	 */
	public float getPremio();

	/**
	 * Devuelve una representación en cadena del cartón.
	 * 
	 * @return Una cadena que muestra los valores del cartón.
	 */
	public String toString();

	/**
	 * Permite obtener los números que hay en el cartón.
	 * 
	 * @return Los números del cartón.
	 */
	public Object getNumeros();

	/**
	 * Permite modificar los números del cartón estableciendo como los números del
	 * mismo el objeto pasado por parámetro.
	 * 
	 * @param o Los números nuevos a establecer en el cartón.
	 */
	public void setNumeros(Object o);

	/**
	 * Permite modificar el valor del precio del cartón estableciendo como valor del
	 * precio el pasado por parámetro.
	 * 
	 * @param valor Valor del precio a establecer.
	 */
	public void setPrecio(float valor);

}
