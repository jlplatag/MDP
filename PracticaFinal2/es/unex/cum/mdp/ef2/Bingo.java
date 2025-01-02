package es.unex.cum.mdp.ef2;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Delayed;

import es.unex.cum.mdp.ef2.carton.CartonBuilder;
import es.unex.cum.mdp.ef2.carton.ICarton;

/**
 * Clase donde se crea el bingo y llama a sus clases derivadas para jugarlo.
 * Tiene todos los parámetros para la construcción del bingo.
 * 
 * @author José Luis Plata Gallardo
 * @author Clara Galván Bermúdez
 * 
 */
public abstract class Bingo {
	private int id;
	private Date fecha;
	private float recaudacion;
	private HashSet<ICarton> cartones;
	private ArrayList<Integer> bolasSacadas;
	private Bolsa b;
	private float precio;
	private boolean jugado;
	private Reparto reparto;

	/**
	 * Constructor por defecto de bingo.
	 */
	public Bingo() {
		this.b = new BolsaBingo();
		this.precio = 0;
		jugado = false;
		reparto = new Reparto();
	}

	/**
	 * Constructor parametrizado 1 de bingo.
	 *
	 * @param id El identificador único para el juego de Bingo.
	 */
	public Bingo(int id) {
		super();
		this.id = id;
		this.fecha = null;
		this.recaudacion = 0;
		this.cartones = new HashSet<ICarton>();
		this.bolasSacadas = new ArrayList<Integer>();
		precio = 0;
		b = new BolsaBingo();
		jugado = false;
		reparto = new Reparto();
	}

	/**
	 * Constructor parametrizado 2 de bingo.
	 *
	 * @param p    El precio del juego de Bingo.
	 * @param tipo El tipo de juego de Bingo.
	 */
	public Bingo(float p, int tipo) {
		super();
		this.id = 0;
		this.fecha = null;
		this.recaudacion = 0;
		this.cartones = new HashSet<ICarton>();
		this.bolasSacadas = new ArrayList<Integer>();
		precio = p;
		b = new BolsaBingo(tipo);
		jugado = false;
		reparto = new Reparto();
	}

	/**
	 * Constructor parametrizado 3 de bingo.
	 *
	 * @param id           El identificador único para el juego de Bingo.
	 * @param fecha        La fecha del juego de Bingo.
	 * @param recaudacion  Los ingresos generados por el juego de Bingo.
	 * @param cartones     El conjunto de cartones de Bingo asociados al juego.
	 * @param bolasSacadas La lista de números extraídos durante el juego.
	 * @param b            La bolsa de Bingo utilizada en el juego.
	 * @param precio       El precio del juego de Bingo.
	 */
	public Bingo(int id, Date fecha, float recaudacion, HashSet<ICarton> cartones, ArrayList<Integer> bolasSacadas,
			Bolsa b, float precio) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.recaudacion = recaudacion;
		this.cartones = cartones;
		this.bolasSacadas = bolasSacadas;
		this.b = b;
		this.precio = precio;
		jugado = false;
		reparto = new Reparto();
	}

	/**
	 * Devuelve el objeto de reparto asociado al juego de bingo.
	 *
	 * @return El objeto de reparto asociado al juego de bingo.
	 */
	public Reparto getReparto() {
		return reparto;
	}

	/**
	 * Modifica el objeto de reparto asociado al juego de bingo.
	 *
	 * @param reparto El nuevo objeto de reparto a asignar al juego de bingo.
	 */
	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}

	/**
	 * Devuelve el precio del juego de bingo.
	 *
	 * @return El precio del juego de bingo.
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * Modifica el precio del juego de bingo.
	 *
	 * @param precio El nuevo precio a asignar al juego de bingo.
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	/**
	 * Devuelve el identificador del juego de bingo.
	 *
	 * @return El identificador del juego de bingo.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Modifica el identificador del juego de bingo.
	 *
	 * @param id El nuevo identificador a asignar al juego de bingo.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Devuelve la fecha asociada al juego de bingo.
	 *
	 * @return La fecha asociada al juego de bingo.
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Modifica la fecha asociada al juego de bingo.
	 *
	 * @param fecha La nueva fecha a asignar al juego de bingo.
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve la recaudación total del juego de bingo.
	 *
	 * @return La recaudación total del juego de bingo.
	 */
	public float getRecaudacion() {
		return recaudacion;
	}

	/**
	 * Modifica la recaudación total del juego de bingo.
	 *
	 * @param recaudacion La nueva recaudación total a asignar al juego de bingo.
	 */
	public void setRecaudacion(float recaudacion) {
		this.recaudacion = recaudacion;
	}

	/**
	 * Devuelve el conjunto de cartones asociados al juego de bingo.
	 *
	 * @return El conjunto de cartones asociados al juego de bingo.
	 */
	public HashSet<ICarton> getCartones() {
		return cartones;
	}

	/**
	 * Modifica el conjunto de cartones asociados al bingo.
	 *
	 * @param cartones El nuevo conjunto de cartones a asignar al bingo.
	 */
	public void setCartones(HashSet<ICarton> cartones) {
		this.cartones = cartones;
	}

	/**
	 * Devuelve la lista de bolas sacadas durante el juego de bingo.
	 *
	 * @return La lista de bolas sacadas durante el juego de bingo.
	 */
	public ArrayList<Integer> getBolasSacadas() {
		return bolasSacadas;
	}

	/**
	 * Modifica la lista de bolas sacadas durante el juego de bingo.
	 *
	 * @param bolasSacadas La nueva lista de bolas sacadas a asignar al bingo.
	 */
	public void setBolasSacadas(ArrayList<Integer> bolasSacadas) {
		this.bolasSacadas = bolasSacadas;
	}

	/**
	 * Devuelve la bolsa asociada al bingo.
	 *
	 * @return La bolsa asociada al bingo.
	 */
	public Bolsa getB() {
		return b;
	}

	/**
	 * Modifica la bolsa asociada al bingo.
	 *
	 * @param b La nueva bolsa a asignar al bingo.
	 */
	public void setB(Bolsa b) {
		this.b = b;
	}

	/**
	 * Verifica si el bingo ha sido jugado.
	 *
	 * @return true si el bingo ha sido jugado, false si no ha sido jugado.
	 */
	public boolean isJugado() {
		return jugado;
	}

	/**
	 * Modifica el estado de juego del bingo.
	 *
	 * @param jugado true si el bingo ha sido jugado, false si no ha sido jugado.
	 */
	public void setJugado(boolean jugado) {
		this.jugado = jugado;
	}

	/**
	 * Calcula y devuelve el código hash de este objeto Bingo.
	 *
	 * @return Código hash calculado a partir de los atributos id, fecha,
	 *         recaudacion, cartones, bolasSacadas, b, precio.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(b, bolasSacadas, cartones, fecha, id, precio, recaudacion);
	}

	/**
	 * Compara este objeto Bingo con otro objeto y determina si son iguales.
	 *
	 * @param obj El objeto con el que se va a comparar.
	 * @return true si los objetos son iguales, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bingo other = (Bingo) obj;
		return Objects.equals(b, other.b) && Objects.equals(bolasSacadas, other.bolasSacadas)
				&& Objects.equals(cartones, other.cartones) && Objects.equals(fecha, other.fecha) && id == other.id
				&& Float.floatToIntBits(precio) == Float.floatToIntBits(other.precio)
				&& Float.floatToIntBits(recaudacion) == Float.floatToIntBits(other.recaudacion);
	}

	/**
	 * Devuelve una representación de cadena de este objeto Bingo.
	 *
	 * @return Cadena que representa el objeto Bingo, incluyendo id, fecha,
	 *         recaudacion, cartones, bolasSacadas, b y precio.
	 */
	@Override
	public String toString() {
		return "Bingo [id=" + id + ", fecha=" + fecha + ", recaudacion=" + recaudacion + ", cartones=" + cartones
				+ ", bolasSacadas=" + bolasSacadas + ", b=" + b + ", precio=" + precio + "]";
	}

	/**
	 * Agrega un cartón al conjunto de cartones si no es nulo.
	 *
	 * @param c El cartón a agregar al conjunto.
	 * @return true si el cartón se agrega con éxito, false si el cartón es nulo o
	 *         no se puede agregar.
	 */
	public boolean add(ICarton c) {
		if (c != null) {
			return cartones.add(c);
		}
		return false;
	}

	/**
	 * Método para crear cartones y añadirlos al usuario. Primero se comprueba si el
	 * usuario contiene el dinero suficiente como para comprar un cartón del bingo.
	 * Se procede a crear el cartón y a añadirlo tanto al array de cartones del
	 * usuario como al del bingo. Se añade el movimiento de comprar cartón al
	 * histórico del usuario, se le modifica el dinero del monedero y se incrementa
	 * la recaudación del bingo. Si se ha completado todo con éxito devuelve el
	 * cartón, sino devolverá null.
	 * 
	 * @param tipo Tipo del cartón a jugar (75,80,90) (H,M,V)
	 * @param u    Usuario al que añadir el cartón
	 * @return Cartón si se ha creado correctamente, null si no.
	 */
	public ICarton crearCarton(String tipo, Usuario u) {
		if (u.getMonedero() >= this.precio) {
			CartonBuilder c = new CartonBuilder(tipo);
			ICarton carton = c.withUser(u).withId(cartones.size()).build();
			carton.setPrecio(this.precio);
			// ----------------------- Añadimos cartones tanto en gartones de aquí como del
			// usuario
			u.getCartones().add(carton);
			this.add(carton);
			// ------------------------ Movimiento para el usuario al comprar cartón
			// ----------------------
			u.getHistorico().add(new Movimiento("Comprar carton con id " + carton.getId(), u.getMonedero(), this.precio,
					new Date()));
			u.setMonedero(u.getMonedero() - this.precio);
			// -------------------------------------------------------------------------------------------
			this.setRecaudacion(this.recaudacion + this.precio);
			return carton;
		}
		return null;
	}

	/**
	 * Método abstracto hecho en Bingo80 y Bingo75, representa la acción de jugar el
	 * bingo. Va sacando los números y comprobando los cartones hasta llegar a
	 * Bingo.
	 *
	 * @param estadistica Un array de objetos Estadistica que representa las
	 *                    estadísticas de los números en el juego.
	 * @return Un objeto Reparto que contiene información sobre el reparto de
	 *         premios y la situación financiera del juego.
	 */
	public abstract Reparto jugar(Estadistica[] estadistica);

	/**
	 * Método abstracto hecho en Bingo80 y Bingo75, representa la acción de jugar el
	 * bingo. Va sacando los números y comprobando los cartones hasta llegar a
	 * Bingo. Se diferencia con el método que solo lleva como parámetro estadística
	 * en que la lista de números utilizada en este juego está predeterminada para
	 * la correcta funcionalidad de la bateria de test del profesor. En las partidas
	 * reales no se usará esta.
	 *
	 * @param estadistica Un array de objetos Estadistica que representa las
	 *                    estadísticas de los números en el juego.
	 * @param numeros     Lista de números ordenada para la batería de test
	 * @return Un objeto Reparto que contiene información sobre el reparto de
	 *         premios y la situación financiera del juego.
	 */
	public abstract Reparto jugar(Estadistica[] estadistica, List numeros);

}