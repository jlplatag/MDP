package es.unex.cum.mdp.ef2;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import es.unex.cum.mdp.ef2.carton.ICarton;

/**
 * La clase Usuario representa a un usuario del Casino con sus datos
 * correspondientes.
 * 
 * Contiene información sobre el usuario mediante los siguientes atributos: nick
 * (nombre de usuario único), nombre (nombre real), password (contraseña),
 * monedero (saldo del monedero), numBingo (número de veces que ha cantado
 * Bingo), numEspeciales (número de veces que ha cantado Especial), numLineas
 * (número de veces que ha cantado Linea), cartones (los cartones asignados a
 * este usuario), cartonesHist (historial de cartones), historico (lista de
 * movimientos que han afectado al saldo del usuario).
 * 
 * @author Clara Galván Bermúdez 
 * @author José Luis Plata Gallardo
 */
public class Usuario implements Comparable {
	protected String nick;
	protected String nombre;
	protected String password;
	protected float monedero;
	protected int numBingos;
	protected int numEspeciales;
	protected int numLineas;
	protected List<ICarton> cartones;
	protected Queue cartonesHist;
	protected List<Movimiento> historico;

	/**
	 * Constructor por defecto de la clase Usuario.
	 * 
	 * * Inicializa los atributos de la instancia de Usuario en sus valores por
	 * defecto. El nombre de usuario, nombre real y contraseña se inicializan como
	 * null. El saldo del monedero se establece en 0. Los conteos de bingos, juegos
	 * especiales y líneas se inician en 0. La lista de cartones se inicializa como
	 * una nueva LinkedList vacía. El historial de movimientos y el historial de
	 * cartones se inicializan como nuevas LinkedList vacías.
	 */
	public Usuario() {
		nick = null;
		nombre = null;
		password = null;
		monedero = 0;
		numBingos = 0;
		numEspeciales = 0;
		numLineas = 0;
		cartones = new LinkedList<ICarton>();
		historico = new LinkedList<Movimiento>();
		cartonesHist = new LinkedList<ICarton>();
	}

	/**
	 * Constructor de Usuario que recibe nick, nombre, contraseña y saldo del
	 * monedero como parámetros. Inicializa los demás atributos del usuario en sus
	 * valores por defecto.
	 * 
	 * @param nick     Nombre de usuario.
	 * @param nombre   Nombre real del usuario.
	 * @param password Contraseña.
	 * @param monedero Saldo del monedero.
	 */
	public Usuario(String nick, String nombre, String password, float monedero) {
		this.nick = nick;
		this.nombre = nombre;
		this.password = password;
		this.monedero = monedero;
		numBingos = 0;
		numEspeciales = 0;
		numLineas = 0;
		cartones = new LinkedList<ICarton>();
		historico = new LinkedList<Movimiento>();
		cartonesHist = new LinkedList<ICarton>();

	}

	/**
	 * Constructor completo de Usuario que recibe todos los atributos como
	 * parámetros.
	 * 
	 * @param nick          Nombre de usuario.
	 * @param nombre        Nombre real.
	 * @param password      Contraseña.
	 * @param monedero      Saldo del monedero.
	 * @param numBingos     Número de veces cantado bingo.
	 * @param numEspeciales Número de veces cantado especiales.
	 * @param numLineas     Número de veces cantado línea.
	 * @param cartones      Lista de cartones asociados al usuario.
	 * @param cartonesHist  Cola que guarda el historial de cartones.
	 * @param historico     Lista que guarda el historial de movimientos económicos
	 *                      del usuario.
	 */
	public Usuario(String nick, String nombre, String password, float monedero, int numBingos, int numEspeciales,
			int numLineas, List<ICarton> cartones, Queue cartonesHist, List<Movimiento> historico) {
		super();
		this.nick = nick;
		this.nombre = nombre;
		this.password = password;
		this.monedero = monedero;
		this.numBingos = numBingos;
		this.numEspeciales = numEspeciales;
		this.numLineas = numLineas;
		this.cartones = cartones;
		this.cartonesHist = cartonesHist;
		this.historico = historico;
		cartonesHist = new LinkedList<ICarton>();

	}

	/**
	 * Se encarga de devolver el valor del atributo nick que se corresponde con el
	 * nombre del usuario en el Casino.
	 * 
	 * @return nick Nombre del usuario en el Casino.
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * Se encarga de modificar el valor del atributo nick que se corresponde con el
	 * nombre del usuario en el Casino.
	 * 
	 * @param nick Nuevo nombre de usuario a asignar al usuario.
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Se encarga de devolver el valor del atributo nombre que se corresponde con el
	 * nombre real del usuario.
	 * 
	 * @return nombre Nombre real del usuario.
	 */

	public String getNombre() {
		return nombre;
	}

	/**
	 * Se encarga de modificar el valor del atributo nombre que se corresponde con
	 * el nombre real del usuario.
	 * 
	 * @param nombre Nuevo nombre real a asignar al usuario.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Se encarga de devolver el valor del atributo password que se corresponde con
	 * la contraseña del usuario.
	 * 
	 * @return password La contraseña del usuario.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Se encarga de modificar el valor del atributo password que se corresponde con
	 * la contraseña del usuario.
	 * 
	 * @param password La nueva contraseña a asignar al usuario.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Se encarga de devolver el valor del atributo monedero que se corresponde con
	 * el saldo monetario del usuario.
	 * 
	 * @return monedero El saldo monetario del usuario.
	 */
	public float getMonedero() {
		return monedero;
	}

	/**
	 * Se encarga de modificar el valor del atributo monedero que se corresponde con el saldo monetario del usuario.
	 * 
	 * @param monedero El nuevo saldo monetario a asignar al usuario.
	 */
	public void setMonedero(float monedero) {
		this.monedero = monedero;
	}

	/**
	 * Se encarga de devolver el valor del atributo numBingos que se corresponde con
	 * el número de veces que el usuario ha cantado Bingo.
	 * 
	 * @return nunBingos El número de veces que el usuario ha cantado Bingo.
	 */
	public int getNumBingos() {
		return numBingos;
	}

	/**
	 * Se encarga de modificar el valor del atributo numBingos que se corresponde con el
	 * número de veces que el usuario ha cantado Bingo.
	 * 
	 * @param numBingos El nuevo número de veces el usuario ha cantado Bingo.
	 */
	public void setNumBingos(int numBingos) {
		this.numBingos = numBingos;
	}

	/**
	 * Se encarga de devolver el valor del atributo numEspeciales que se corresponde
	 * con el número de veces que el usuario ha cantado Especial.
	 * 
	 * @return numEspeciales El número de veces que el usuario ha cantado Especial.
	 */
	public int getNumEspeciales() {
		return numEspeciales;
	}

	/**
	 * Se encarga de modificar el valor del atributo numEspeciales que se corresponde con el
	 * número de veces que el usuario ha cantado Especial.
	 * 
	 * @param numEspeciales El nuevo número de veces que el usuario ha cantado Especial.
	 */
	public void setNumEspeciales(int numEspeciales) {
		this.numEspeciales = numEspeciales;
	}

	/**
	 * Se encarga de devolver el valor del atributo numLineas que se corresponde con
	 * el número de veces que el usuario ha cantado Linea.
	 * 
	 * @return numLinea El número de veces que el usuario ha cantado Linea.
	 */
	public int getNumLineas() {
		return numLineas;
	}

	/**
	 * Se encarga de modificar el valor del atributo numLineas que se corresponde con el
	 * número de veces que el usuario ha cantado Linea.
	 * 
	 * @param numLineas El nuevo número de veces que el usuario ha cantado Linea.
	 */
	public void setNumLineas(int numLineas) {
		this.numLineas = numLineas;
	}

	/**
	 * Se encarga de devolver el valor del atributo cartones que se corresponde con
	 * la lista de cartones asignados al usuario.
	 * 
	 * @return cartones Lista de cartones asignados al usuario.
	 */
	public List<ICarton> getCartones() {
		return cartones;
	}

	/**
	 * Se encarga de modificar el valor del atributo cartones que se corresponde con
	 * la lista de cartones asignados al usuario.
	 * 
	 * @param cartones La nueva lista de cartones a asignar al usuario.
	 */
	public void setCartones(List<ICarton> cartones) {
		this.cartones = cartones;
	}

	/**
	 * Se encarga de devolver el valor del atributo cartonesHist que se corresponde
	 * con el historial de cartones del usuario.
	 * 
	 * @return cartonesHist Historial de cartones del usuario.
	 */
	public Queue getCartonesHist() {
		return cartonesHist;
	}

	/**
	 * Se encarga de modificar el valor del atributo cartonesHist que se corresponde con
	 * el historial de cartones del usuario.
	 * 
	 * @param cartonesHist El nuevo historial de cartones a asignar al usuario.
	 */
	public void setCartonesHist(Queue cartonesHist) {
		this.cartonesHist = cartonesHist;
	}

	/**
	 * Se encarga de devolver el valor del atributo historico que se corresponde con
	 * la lista de movimientos que afectan al saldo del usuario.
	 * 
	 * @return historico Lista de movimientos que afectan al saldo del usuario.
	 */
	public List<Movimiento> getHistorico() {
		return historico;
	}

	/**
	 * Se encarga de modificar el valor del atributo historico que se corresponde con
	 * la lista de movimientos que afectan al saldo del usuario.
	 * 
	 * @param historico La nueva lista de movimientos que afectan al usuario.
	 */
	public void setHistorico(List<Movimiento> historico) {
		this.historico = historico;
	}

	/**
	 * Devuelve un código hash basado en los atributos relevantes del objeto
	 * Usuario.
	 * 
	 * @return El código hash generado para la instancia actual de Usuario.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cartones, cartonesHist, historico, monedero, nick, nombre, numBingos, numEspeciales,
				numLineas, password);
	}

	/**
	 * Compara si el objeto actual es igual al objeto Usuario proporcionado.
	 * 
	 * @param obj El objeto a comparar con la instancia actual de Usuario.
	 * @return true si los objetos son iguales, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		return Objects.equals(cartones, other.cartones) && Objects.equals(cartonesHist, other.cartonesHist)
				&& Objects.equals(historico, other.historico)
				&& Float.floatToIntBits(monedero) == Float.floatToIntBits(other.monedero)
				&& Objects.equals(nick, other.nick) && Objects.equals(nombre, other.nombre)
				&& numBingos == other.numBingos && numEspeciales == other.numEspeciales && numLineas == other.numLineas
				&& Objects.equals(password, other.password);
	}

	/**
	 * Devuelve una representación en forma de cadena de la instancia actual de
	 * Usuario mostrando información de los atributos del mismo.
	 * 
	 * @return Una cadena representativa de la instancia actual de Usuario.
	 */

	@Override
	public String toString() {
		return "Usuario [nick=" + nick + ", nombre=" + nombre + ", password=" + password + ", monedero=" + monedero
				+ ", numBingos=" + numBingos + ", numEspeciales=" + numEspeciales + ", numLineas=" + numLineas
				+ ", cartones=" + cartones + ", cartonesHist=" + cartonesHist + ", historico=" + historico + "]";
	}

	/**
	 * Compara dos objetos Usuario basándose en su número de bingos, líneas y juegos
	 * especiales. Este método es llamado cuando se requiere ver el Ranking en el
	 * Casino.
	 * 
	 * El objetivo es que los usuarios sean ordenados de tal forma que, primero
	 * vayan los usuarios con mayor número de veces cantado Bingo después, los
	 * usuarios con mayor número de veces cantado Línea y por último, los usarios
	 * con mayor número de veces cantado Especial.
	 * 
	 * La dinámica de este método funciona de la siguiente forma: Si los usaurios
	 * tienen el mismo número de Bingos, se compara el número de líneas y si, tanto
	 * el número de bingo como número de líneas son iguales, compara el número de
	 * especiales.
	 * 
	 * @param o El objeto Usuario a comparar.
	 * @return Un valor negativo si la instancia actual es menor que 'o', cero si
	 *         son iguales, y un valor positivo si es mayor.
	 */
	public int compareTo(Object o) {
		Usuario u = (Usuario) o;
		if (this.numBingos != u.numBingos) {
			return u.numBingos - this.numBingos;
		} else {
			// Si el número de bingos es igual, comparar el número de líneas
			if (this.numLineas != u.numLineas) {
				return u.numLineas - this.numLineas;
			} else {
				// Si tanto el número de bingos como el número de líneas son iguales, comparar
				// el número de especiales
				return u.numEspeciales - this.numEspeciales;
			}
		}
	}

}
