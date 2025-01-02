package es.unex.cum.mdp.ef2.carton;

import es.unex.cum.mdp.ef2.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * La clase Carton75H representa un cartón de bingo con 75 números. Cada cartón
 * tiene asignados una serie de atributos: identificador único, un usuario, un
 * precio, un premio, estado (refleja su situación actual durante el juego),
 * numeros (mapa que almacena las celdas de números que componen el cartón),
 * número de filas y columnas del cartón, número de especiales que puede haber,
 * los números requeridos para lograr un Bingo o una Línea, número máximo
 * permitido en el cartón y contadores para saber la cantidad de números
 * marcados para poder cantar bingo, línea y especiales.
 * 
 * Ofrece métodos para gestionar, verificar y marcar los números cantados, así
 * como para comprobar y cambiar el estado del cartón durante el juego.Así pues:
 * 
 * Los métodos de gestión permiten añadir celdas al cartón, marcar números como
 * especiales, verificar si se han marcado los números especiales y comprobar si
 * se ha logrado una línea o un bingo en el cartón.
 * 
 * Los métodos para verificar y marcar los números cantados incluyen la
 * funcionalidad para comprobar si un número ha sido cantado, contar el número
 * de aciertos en el cartón y cambiar el estado del cartón.
 * 
 * Los métodos para comprobar y cambiar el estado del cartón durante el juego
 * incluyen funcionalidades para verificar si se ha logrado una línea o un bingo
 * en el cartón y cambiar el estado del cartón en consecuencia.
 * 
 * Esta clase implementa la interfaz ICarton, que define métodos esenciales para
 * el funcionamiento del cartón dentro del juego.
 * 
 * @author Clara Galván Bermúdez
 */
public class Carton75H implements ICarton {
	private int id;
	private Usuario user;
	private float precio;
	private float premio;
	private EstadoCarton estado;
	private HashMap<Integer, CeldaCarton> numeros;
	private int numeroFilas;
	private int numeroColumnas;
	private int numeroMaximo;
	private int numeroAciertosBingo;
	private int numeroAciertosLinea;
	private int numEspeciales;
	private int contCantadoBingo = 0;
	private int contEspeciales = 0;
	private int contLinea[] = { 0, 0, 0, 0, 0 };

	/**
	 * Constructor por defecto de la clase Carton75H que inicializa los atributos
	 * con valores predeterminados: Constructor por defecto de la clase Carton75H.
	 * Inicializa los atributos con valores predeterminados: - id: 0 - user:
	 * instancia de la clase Usuario - precio: 0 - premio: 0 - numeros: HashMap
	 * vacío - numeroFilas: 5 (cartón 75 es similar a una matriz 5x5) -
	 * numeroColumnas: 5 (cartón 75 es similar a una matriz 5x5) - numeroMaximo: 75
	 * (número máximo posible en el cartón) - numeroAciertosBingo: 20 (número máximo
	 * de números cantados en el cartón) - numeroAciertosLinea: 4 (número máximo de
	 * números cantados para una línea en el cartón) - numEspeciales: 2 - estado:
	 * EstadoCarton.NADA
	 * 
	 */
	public Carton75H() {
		id = 0;
		user = new Usuario();
		precio = 0;
		premio = 0;
		numeros = new HashMap<Integer, CeldaCarton>();
		numeroFilas = 5;
		numeroColumnas = 5;
		numeroMaximo = 75;
		numeroAciertosBingo = 20;
		numeroAciertosLinea = 4;
		numEspeciales = 2;
		estado = EstadoCarton.NADA;
	}

	/**
	 * Constructor parametrizado de la clase Carton75H. Permite establecer valores
	 * específicos para los atributos del cartón.
	 *
	 * @param id                  Identificador del cartón.
	 * @param user                Usuario asociado al cartón.
	 * @param precio              Precio del cartón.
	 * @param premio              Premio del cartón.
	 * @param estado              Estado actual del cartón.
	 * @param numeros             Números presentes en el cartón.
	 * @param numeroFilas         Número de filas del cartón.
	 * @param numeroColumnas      Número de columnas del cartón.
	 * @param numeroMaximo        Número máximo permitido en el cartón.
	 * @param numeroAciertosBingo Número total de aciertos para lograr Bingo.
	 */
	public Carton75H(int id, Usuario user, float precio, float premio, EstadoCarton estado,
			HashMap<Integer, CeldaCarton> numeros, int numeroFilas, int numeroColumnas, int numeroMaximo,
			int numeroAciertosBingo) {
		super();
		this.id = id;
		this.user = user;
		this.precio = precio;
		this.premio = premio;
		this.estado = estado;
		this.numeros = numeros;
		this.numeroFilas = numeroFilas;
		this.numeroColumnas = numeroColumnas;
		this.numeroMaximo = numeroMaximo;
		this.numeroAciertosBingo = numeroAciertosBingo;
	}

	/**
	 * Se encarga de devolver el valor del atributo id que se corresponde con el
	 * identificador del cartón.
	 * 
	 * @return id Valor del identificador del cartón.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Se encarga de modificar el valor del atributo id que se corresponde con el
	 * identificador del cartón.
	 * 
	 * @param id El identificador a a asignar al cartón.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Se encarga de devolver el valor del atributo user que se corresponde con el
	 * usuario del cartón.
	 * 
	 * @return user Valor del usuario del cartón.
	 */
	public Usuario getUser() {
		return user;
	}

	/**
	 * Se encarga de modificar el valor del atributo user que se corresponde con el
	 * usuario del cartón.
	 * 
	 * @param user El usuario a asignar al cartón.
	 */
	public void setUser(Usuario user) {
		this.user = user;
	}

	/**
	 * Se encarga de devolver el valor del atributo precio que se corresponde con el
	 * precio del cartón.
	 * 
	 * @return precio Valor del precio del cartón.
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * Se encarga de modificar el valor del atributo precio que se corresponde con
	 * el precio del cartón.
	 * 
	 * @param valor El precio a asignar al cartón.
	 */
	public void setPrecio(float valor) {
		this.precio = valor;
	}

	/**
	 * Se encarga de devolver el valor del atributo premio que se corresponde con el
	 * premio del cartón.
	 * 
	 * @return premio Valor del premio del cartón.
	 */
	public float getPremio() {
		return premio;
	}

	/**
	 * Se encarga de modificar el valor del atributo premio que se corresponde con
	 * el premio del cartón.
	 * 
	 * @param premio El premio a asignar al cartón.
	 */
	public void setPremio(float premio) {
		this.premio = premio;
	}

	/**
	 * Se encarga de devolver el valor del atributo estado que se corresponde con el
	 * estado del cartón.
	 * 
	 * @return estado Valor del estado del cartón.
	 */
	public EstadoCarton getEstado() {
		return estado;
	}

	/**
	 * Se encarga de modificar el valor del atributo estado que se corresponde con
	 * el estado del cartón.
	 * 
	 * @param estado El estado a asignar al cartón.
	 */
	public void setEstado(EstadoCarton estado) {
		this.estado = estado;
	}

	/**
	 * Se encarga de devolver el valor del atributo numeros que se corresponde con
	 * el mapa presente en el cartón.
	 * 
	 * @return numeros El mapa de números presentes en el cartón.
	 */
	public HashMap<Integer, CeldaCarton> getNumeros() {
		return numeros;
	}

	/**
	 * Se encarga de modificar el valor del atributo numeros que se corresponde con
	 * el mapa presente en el cartón.
	 * 
	 * @param numeros El mapa a asignar al cartón.
	 */
	public void setNumeros(HashMap<Integer, CeldaCarton> numeros) {
		this.numeros = numeros;
	}

	/**
	 * Se encarga de devolver el valor del atributo numeroFilas que se corresponde
	 * con el número de filas del cartón.
	 * 
	 * @return numeroFilas El número de filas de un cartón.
	 */
	public int getNumeroFilas() {
		return numeroFilas;
	}

	/**
	 * Se encarga de modificar el valor del atributo numeroFilas que se corresponde
	 * con el número de filas del cartón.
	 * 
	 * @param numeroFilas El número de filas a asignar al cartón.
	 */
	public void setNumeroFilas(int numeroFilas) {
		this.numeroFilas = numeroFilas;
	}

	/**
	 * Se encarga de devolver el valor del atributo numeroColumnas que se
	 * corresponde con el número de columnas del cartón.
	 * 
	 * @return numeroColumnas El número de columnas de un cartón.
	 */
	public int getNumeroColumnas() {
		return numeroColumnas;
	}

	/**
	 * Se encarga de modificar el valor del atributo numeroColumnas que se
	 * corresponde con el número de columnas del cartón.
	 * 
	 * @param numeroColumnas El número de columnas a asignar al cartón.
	 */
	public void setNumeroColumnas(int numeroColumnas) {
		this.numeroColumnas = numeroColumnas;
	}

	/**
	 * Se encarga de devolver el valor del atributo numeroMaximo que se corresponde
	 * con el número máximo posible del cartón.
	 * 
	 * @return numeroMaximo Valor del número máximo posible del cartón.
	 */
	public int getNumeroMaximo() {
		return numeroMaximo;
	}

	/**
	 * Se encarga de modificar el valor del atributo numeroMaximo que se corresponde
	 * con el número máximo posible del cartón.
	 * 
	 * @param numeroMaximo El número máximo posible a asignar al cartón.
	 */
	public void setNumeroMaximo(int numeroMaximo) {
		this.numeroMaximo = numeroMaximo;
	}

	/**
	 * Se encarga de devolver el valor del atributo numeroAciertosBingo que se
	 * corresponde con el número máximo posible de números cantados para cantar
	 * bingo dentro del cartón.
	 * 
	 * @return numeroAciertosBingo Valor del número máximo posible de números
	 *         cantados para cantar bingo dentro del cartón.
	 */
	public int getNumeroAciertosBingo() {
		return numeroAciertosBingo;
	}

	/**
	 * Se encarga de devolver el valor del atributo numeroAciertosLinea que se
	 * corresponde con el número máximo posible de números cantados para cantar
	 * línea dentro del cartón.
	 * 
	 * @return numeroAciertosLinea Valor del número máximo posible de números
	 *         cantados para cantar línea dentro del cartón.
	 */
	public int getNumeroAciertosLinea() {
		return numeroAciertosLinea;
	}

	/**
	 * Se encarga de modificar el valor del atributo numeroAciertosLinea que se
	 * corresponde con el número máximo posible de números cantados para cantar
	 * línea dentro del cartón.
	 * 
	 * @param numeroAciertosLinea Valor del número máximo posible de números
	 *                            cantados para cantar línea a asignar al cartón.
	 */
	public void setNumeroAciertosLinea(int numeroAciertosLinea) {
		this.numeroAciertosLinea = numeroAciertosLinea;
	}

	/**
	 * Se encarga de modificar el valor del atributo numeroAciertosBingo que se
	 * corresponde con el número máximo posible de números cantados para cantar
	 * bingo dentro del cartón.
	 * 
	 * @param numeroAciertosBingo Valor del número máximo posible de números
	 *                            cantados para cantar bingo a asignar al cartón.
	 */
	public void setNumeroAciertosBingo(int numeroAciertosBingo) {
		this.numeroAciertosBingo = numeroAciertosBingo;
	}

	/**
	 * Calcula y devuelve el código hash para el objeto Carton75H. Este código hash
	 * se basa en los atributos relevantes del objeto.
	 *
	 * @return El código hash calculado para el objeto Carton75H.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, numEspeciales, numeroAciertosBingo, numeroAciertosLinea, numeroColumnas, numeroFilas,
				numeroMaximo, numeros, precio, premio);
	}

	/**
	 * Se encarga de comparar el objeto Carton75H con otro objeto proporcionado.
	 * Devuelve true si ambos objetos son iguales en términos de atributos, de lo
	 * contrario, devuelve false.
	 *
	 * @param obj El objeto a comparar con la instancia de Carton75H.
	 * @return true si los objetos son iguales, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Carton75H)) {
			return false;
		}
		Carton75H other = (Carton75H) obj;
		return id == other.id && numEspeciales == other.numEspeciales
				&& numeroAciertosBingo == other.numeroAciertosBingo && numeroAciertosLinea == other.numeroAciertosLinea
				&& numeroColumnas == other.numeroColumnas && numeroFilas == other.numeroFilas
				&& numeroMaximo == other.numeroMaximo && Objects.equals(numeros, other.numeros)
				&& Float.floatToIntBits(precio) == Float.floatToIntBits(other.precio)
				&& Float.floatToIntBits(premio) == Float.floatToIntBits(other.premio);
	}

	/**
	 * Devuelve una representación en forma de cadena (String) del objeto Carton75H.
	 * Esta representación incluye información sobre los atributos del objeto.
	 *
	 * @return una cadena que muetra los valores del objeto de la clase Carton75H.
	 */
	@Override
	public String toString() {
		return "Carton75H [id=" + id + ", precio=" + precio + ", premio=" + premio + ", numeros=" + numeros
				+ ", numeroFilas=" + numeroFilas + ", numeroColumnas=" + numeroColumnas + ", numeroMaximo="
				+ numeroMaximo + ", numeroAciertosBingo=" + numeroAciertosBingo + ", numeroAciertosLinea="
				+ numeroAciertosLinea + ", numEspeciales=" + numEspeciales + "]";
	}

	/**
	 * Verifica si el número recibido por parámetro está presente en el cartón y por
	 * tanto, pueda ser cantado en caso de encontrarse dentro del mismo. En el caso
	 * de que el número pueda ser cantado, se actualiza el estado de la celda
	 * correspondiente, es decir, se cambia el estado de la celda a 2 lo cual indica
	 * que el número existe en el cartón y es cantado. Además, se incrementan los
	 * contadores de Bingo, especiales y línea para posteriormente, facilitar las
	 * comprobaciones.
	 * 
	 * @param numero El número extraído de la bola.
	 * @return true si el número es válido y se encuentra en el cartón, false en
	 *         caso adverso.
	 */
	public boolean recibirNumero(int numero) {
		if (numeros.containsKey(numero)) {
			CeldaCarton c = numeros.get(numero);
			c.setEstado(2);
			contCantadoBingo++;
			if (c.isEspecial()) {
				contEspeciales++;
			}
			contLinea[c.getFila()]++;
			return true;
		}
		return false;
	}

	/**
	 * Añade una celda al mapa del cartón en caso que esta no existiese todavía en
	 * el mapa. Para ello, se comprueba si el mapa contiene la clave (número
	 * asignado) de la celda que se quiere añadir y si no la tiene, se agrega.
	 * 
	 * @param c La celda a añadir al cartón.
	 * @return true si se añade correctamente, false si ya existe.
	 */
	public boolean addNumero(CeldaCarton c) {
		if (!numeros.containsKey(c.getNumero())) {
			numeros.put(c.getNumero(), c);
			return true;
		}
		return false;
	}

	/**
	 * Marca un número como especial en el cartón. Para ello, este número ha de
	 * existir dentro del mapa de cartón y por eso, se busca si el mapa contiene la
	 * clave correspondiente a ese número. Si la tiene, el número pasa a ser
	 * especial.
	 * 
	 * @param num El número que se marcará como especial.
	 * @return true si se marca correctamente, false si no se encuentra en el
	 *         cartón.
	 */
	public boolean addEspecial(int num) {
		CeldaCarton c = null;
		if (numeros.containsKey(num) && contEspeciales < 2) {
			c = numeros.get(num);
			c.setEspecial(true);
			return true;
		}

		return false;
	}

	/**
	 * Comprueba si existen y son especiales los dos números elegidos como
	 * especiales para el cartón. Además, en caso afirmativo el estado del cartón
	 * debe cambiar pues, si el estado del cartón antes era LINEA ahora pasa a ser
	 * ESPECIAL_LINEA y si tenía un estado diferente a LINEA, pasa a ser ESPECIAL.
	 * 
	 * @return true si se encuentran dos números especiales, false en caso
	 *         contrario.
	 */
	public boolean comprobarEspeciales() {
		/*
		 * int contEspecial = 0; for (Iterator it = numeros.keySet().iterator();
		 * it.hasNext();) { Integer c = (Integer) it.next(); CeldaCarton c1 =
		 * (CeldaCarton) numeros.get(c); if (c1.isEspecial() && c1.getEstado() == 2) {
		 * contEspecial++; } }
		 */
		if (contEspeciales == 2) {
			if (this.getEstado().equals(EstadoCarton.LINEA)) {
				this.setEstado(EstadoCarton.ESPECIAL_LINEA);
			} else {
				this.setEstado(EstadoCarton.ESPECIAL);
			}
			return true;
		}
		return false;
	}

	/*
	 * public boolean comprobarLinea(int fila) { int contadorFila = 0; for (Iterator
	 * it = numeros.keySet().iterator(); it.hasNext();) { Integer clave = (Integer)
	 * it.next(); CeldaCarton celda = (CeldaCarton) numeros.get(clave); if
	 * (celda.getFila() == fila && celda.getEstado() == 2) { contadorFila++; } } if
	 * (contadorFila == numeroAciertosLinea) { if
	 * (this.getEstado().equals(EstadoCarton.ESPECIAL)) {
	 * this.setEstado(EstadoCarton.ESPECIAL_LINEA); } else {
	 * this.setEstado(EstadoCarton.LINEA); } return true; }
	 * 
	 * return false; }
	 * 
	 * public boolean comprobarLinea() { for (int i = 0; i < numeroFilas; i++) { if
	 * (this.comprobarLinea(i)) { return true; } } return false; }
	 */

	/**
	 * Verifica si se ha completado una línea en una fila específica del cartón y
	 * por tanto, se puede cantar Línea. Para ello, se compara si el contador global
	 * de Línea en la posición de fila determinada tiene el mismo número que el
	 * número de aciertos para poder cantar Línea.
	 * 
	 * @param fila La fila del cartón que se va a verificar para determinar si hay
	 *             una línea completa.
	 * @return true si se ha completado la línea en la fila especificada, false de
	 *         lo contrario.
	 */
	public boolean comprobarLinea(int fila) {
		if (contLinea[fila] == numeroAciertosLinea) {
			if (this.getEstado().equals(EstadoCarton.ESPECIAL)) {
				this.setEstado(EstadoCarton.ESPECIAL_LINEA);
			} else {
				this.setEstado(EstadoCarton.LINEA);
			}
			return true;
		}
		return contLinea[fila] >= 4;
	}

	/**
	 * Verifica si se ha completado alguna línea en el cartón. Para eso, llama al
	 * método comprobarLinea donde se le pasa por parámetro una fila específica.
	 * 
	 * @return true si se ha completado alguna línea en el cartón, false de lo
	 *         contrario.
	 */
	public boolean comprobarLinea() {
		for (int i = 0; i < numeroFilas; i++) {
			if (this.comprobarLinea(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Comprueba si se ha cantado Bingo en el cartón. Para ello, el cartón tiene que
	 * tener marcados los 20 números que lo componen. Además, en caso de que se haya
	 * cantado bingo, el estado del cartón ha de cambiar según su estado anterior:
	 * si antes su estado era ESPECIAL_LINEA ahora es ESPECIAL_LINEA_BINGO; si antes
	 * era ESPECIAL ahora es ESPECIAL_BINGO; si antes era LINEA ahora es
	 * LINEA_BINGO; y finalmente, si no era ninguno de los casos anteriores antes de
	 * cantar bingo, su estado pasa a ser BINGO.
	 * 
	 * @return true si se ha cantado Bingo, false si no.
	 */
	public boolean comprobarBingo() {
		EstadoCarton e = this.getEstado();
		if (contCantadoBingo == 20) {
			if (e.equals(EstadoCarton.ESPECIAL_LINEA)) {
				this.setEstado(EstadoCarton.ESPECIAL_LINEA_BINGO);
			} else if (e.equals(EstadoCarton.ESPECIAL)) {
				this.setEstado(EstadoCarton.ESPECIAL_BINGO);
			} else if (e.equals(EstadoCarton.LINEA)) {
				this.setEstado(EstadoCarton.LINEA_BINGO);
			} else {
				this.setEstado(EstadoCarton.BINGO);
			}
			return true;
		}
		return false;
	}

	public boolean repartir() {
		Random random = new Random();
		int numeroAleatorio = 0;
		int jFila = 0;
		int contEspecial = 0;
		Set<Integer> setNumeros = new TreeSet<Integer>();
		int[] rango75 = { 1, 16, 31, 46, 61 };
		CeldaCarton[][] matriz = new CeldaCarton[numeroFilas][numeroColumnas];
		// ---------------------Inicialización MATRIZ TEMPORAL
		for (int i = 0; i < numeroFilas; i++) {
			for (int j = 0; j < numeroColumnas; j++) {
				matriz[i][j] = new CeldaCarton(0, 0, i, j);
			}
		}
		// ------------------- Asignación números aleatorios, meto en el Set los números
		// aleatorios de cada columna
		for (int i = 0; i < numeroFilas; i++) {
			setNumeros.clear();
			while (setNumeros.size() < 4) {
				setNumeros.add(numeroAleatorio = random.nextInt(15) + rango75[i]);
			}
			// Recorro el set y guardo los números
			jFila = 0;
			Iterator it = setNumeros.iterator();
			while (it.hasNext()) {
				if (jFila != i) {
					matriz[jFila][i].setNumero((Integer) it.next());
					matriz[jFila][i].setEstado(1);
				}
				jFila++;
			}
			// ------ fila y columna al azar donde guardo los especiales
			do {
				int randomFila = random.nextInt(numeroFilas);
				int randomColumna = random.nextInt(numeroColumnas);
				if (matriz[randomFila][randomColumna].getEstado() == 1
						&& !matriz[randomFila][randomColumna].isEspecial()) {
					matriz[randomFila][randomColumna].setEspecial(true);
					contEspecial++;
				}
			} while (contEspecial < 2);

		}
		// guardar matriz dentro del mapa ....................................
		for (int i = 0; i < numeroFilas; i++) {
			for (int j = 0; j < numeroColumnas; j++) {
				if (i != j) {
					CeldaCarton celdaMapa = new CeldaCarton(matriz[i][j].getNumero(), matriz[i][j].getEstado(),
							matriz[i][j]);
					numeros.put(celdaMapa.getNumero(), celdaMapa);
				}
			}
		}
		return true;
	}

	/**
	 * Obtiene la cantidad de aciertos en el cartón. Es decir, recorre el mapa del
	 * cartón (mediante iterator) y cuenta el número de celdas cuyo estado es 2, lo
	 * que indica que el número correspondiente existe en el cartón y ha sido
	 * cantado. Para contar el número de celdas con estado 2 se crea una variable
	 * local que inicialmente tiene valor 0 pero que a medida que se recorre el mapa
	 * y se encuentran celdas con ese estado, se incrementa.
	 * 
	 * @return El número de aciertos en el cartón guardado en la variable local
	 *         creada.
	 */
	public int getAciertos() {
		int contAciertos = 0;
		for (Iterator it = numeros.keySet().iterator(); it.hasNext();) {
			Integer clave = (Integer) it.next();
			CeldaCarton celda = (CeldaCarton) numeros.get(clave);
			if (celda.getEstado() == 2) {
				contAciertos++;
			}
		}
		return contAciertos;
	}

	/**
	 * Establece el mapa de números del cartón a partir de un objeto genérico.
	 * 
	 * @param o Objeto que contiene el mapa de números a asignar al cartón. Se
	 *          espera que este objeto sea de tipo HashMap (Integer, CeldaCarton).
	 * 
	 * @throws ClassCastException Si el objeto pasado como parámetro no es de tipo
	 *                            HashMap (Integer, CeldaCarton), se lanzará una
	 *                            ClassCastException.
	 */
	@Override
	public void setNumeros(Object o) {
		HashMap<Integer, CeldaCarton> numeros = (HashMap<Integer, CeldaCarton>) o;
		this.numeros = numeros;

	}

}
