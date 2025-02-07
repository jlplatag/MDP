package es.unex.cum.mdp.ef3.modelo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * Clase que representa el cartón 80 para jugar a un bingo. Implementa la clase
 * ICarton.
 * 
 * @author José Luis Plata Gallardo
 * 
 */
public class Carton80M implements ICarton, Serializable {
	private int id;
	private Usuario user;
	private float precio;
	private float premio;
	private EstadoCarton estado;
	private CeldaCarton[][] numeros;
	private int numeroFilas;
	private int numeroColumnas;
	private int numeroMaximo;
	private int numeroAciertosBingo;
	private int numeroAciertosLinea;
	private int numEspeciales;
	private int contTodosB = 0;

	/**
	 * Constructor por defecto de Carton80M. Inicializa un cartón con CeldaCarton
	 * sin números.
	 */
	public Carton80M() {
		id = 0;
		user = new Usuario();
		precio = 0;
		premio = 0;
		numeroFilas = 4;
		numeroColumnas = 8;
		numeroMaximo = 80;
		numeroAciertosBingo = 20;
		numeroAciertosLinea = 5;
		numEspeciales = 3;
		numeros = new CeldaCarton[numeroFilas][numeroColumnas];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				numeros[i][j] = new CeldaCarton(0, 0, i, j, false);
			}
		}
		estado = EstadoCarton.NADA;
	}

	/**
	 * Constructor parametrizado de Carton80M.
	 *
	 * @param id      Identificador del cartón.
	 * @param user    Usuario asociado al cartón.
	 * @param precio  Precio del cartón.
	 * @param premio  Premio del cartón.
	 * @param estado  Estado actual del cartón.
	 * @param numeros Matriz de celdas de cartón con los números.
	 */
	public Carton80M(int id, Usuario user, float precio, float premio, EstadoCarton estado, CeldaCarton[][] numeros) {
		super();
		this.id = id;
		this.user = user;
		this.precio = precio;
		this.premio = premio;
		this.estado = estado;
		this.numeros = numeros;
	}

	/**
	 * Modifica el número de filas del cartón.
	 *
	 * @param numeroFilas El nuevo número de filas a asignar.
	 */
	public void setNumeroFilas(int numeroFilas) {
		this.numeroFilas = numeroFilas;
	}

	/**
	 * Modifica el número de columnas del cartón.
	 *
	 * @param numeroColumnas El nuevo número de columnas a asignar.
	 */
	public void setNumeroColumnas(int numeroColumnas) {
		this.numeroColumnas = numeroColumnas;
	}

	/**
	 * Modifica el número máximo permitido en el cartón.
	 *
	 * @param numeroMaximo El nuevo número máximo a asignar.
	 */
	public void setNumeroMaximo(int numeroMaximo) {
		this.numeroMaximo = numeroMaximo;
	}

	/**
	 * Modifica el número de aciertos necesarios para ganar en Bingo.
	 *
	 * @param numeroAciertosBingo El nuevo número de aciertos necesarios para ganar
	 *                            en Bingo.
	 */
	public void setNumeroAciertosBingo(int numeroAciertosBingo) {
		this.numeroAciertosBingo = numeroAciertosBingo;
	}

	/**
	 * Modifica el número de aciertos necesarios para ganar en una línea.
	 *
	 * @param numeroAciertosLinea El nuevo número de aciertos necesarios para ganar
	 *                            en una línea.
	 */
	public void setNumeroAciertosLinea(int numeroAciertosLinea) {
		this.numeroAciertosLinea = numeroAciertosLinea;
	}

	/**
	 * Modifica el número de cartones especiales.
	 *
	 * @param numEspeciales El nuevo número de cartones especiales a asignar.
	 */
	public void setNumEspeciales(int numEspeciales) {
		this.numEspeciales = numEspeciales;
	}

	/**
	 * Devuelve el número de aciertos necesarios para ganar en una línea.
	 *
	 * @return El número de aciertos necesarios para ganar en una línea.
	 */
	public int getNumeroAciertosLinea() {
		return numeroAciertosLinea;
	}

	/**
	 * Devuelve el número de cartones especiales.
	 *
	 * @return El número de cartones especiales.
	 */
	public int getNumEspeciales() {
		return numEspeciales;
	}

	/**
	 * Devuelve el identificador del cartón.
	 *
	 * @return El identificador del cartón.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Modifica el identificador del cartón.
	 *
	 * @param id El nuevo identificador a asignar al cartón.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Devuelve el usuario asociado al cartón.
	 *
	 * @return El usuario asociado al cartón.
	 */
	public Usuario getUser() {
		return user;
	}

	/**
	 * Modifica el usuario asociado al cartón.
	 *
	 * @param user El nuevo usuario a asignar al cartón.
	 */
	public void setUser(Usuario user) {
		this.user = user;
	}

	/**
	 * Devuelve el precio del cartón.
	 *
	 * @return El precio del cartón.
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * Modifica el precio del cartón.
	 *
	 * @param precio El nuevo precio a asignar al cartón.
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	/**
	 * Devuelve el premio asociado al cartón.
	 *
	 * @return El premio asociado al cartón.
	 */
	public float getPremio() {
		return premio;
	}

	/**
	 * Modifica el premio asociado al cartón.
	 *
	 * @param premio El nuevo premio a asignar al cartón.
	 */
	public void setPremio(float premio) {
		this.premio = premio;
	}

	/**
	 * Devuelve el estado actual del cartón.
	 *
	 * @return El estado actual del cartón.
	 */
	public EstadoCarton getEstado() {
		return estado;
	}

	/**
	 * Modifica el estado del cartón.
	 *
	 * @param estado El nuevo estado a asignar al cartón.
	 */
	public void setEstado(EstadoCarton estado) {
		this.estado = estado;
	}

	/**
	 * Devuelve la matriz de números del cartón.
	 *
	 * @return La matriz de números del cartón.
	 */
	public CeldaCarton[][] getNumeros() {
		return numeros;
	}

	/**
	 * Devuelve el número de filas del cartón.
	 *
	 * @return El número de filas del cartón.
	 */
	public int getNumeroFilas() {
		return numeroFilas;
	}

	/**
	 * Devuelve el número de columnas del cartón.
	 *
	 * @return El número de columnas del cartón.
	 */
	public int getNumeroColumnas() {
		return numeroColumnas;
	}

	/**
	 * Devuelve el número máximo permitido en el cartón.
	 *
	 * @return El número máximo permitido en el cartón.
	 */
	public int getNumeroMaximo() {
		return numeroMaximo;
	}

	/**
	 * Devuelve el número de aciertos necesarios para ganar en Bingo.
	 *
	 * @return El número de aciertos necesarios para ganar en Bingo.
	 */
	public int getNumeroAciertosBingo() {
		return numeroAciertosBingo;
	}

	/**
	 * Método hashCode, genera código hash.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, numEspeciales, numeroAciertosBingo, numeroAciertosLinea, numeroColumnas, numeroFilas,
				numeroMaximo, numeros, precio, premio);
	}

	/**
	 * Método equals que compara dos objetos Carton80M para determinar si son
	 * iguales.
	 *
	 * @param obj Objeto a comparar con el cartón actual.
	 * @return true si los objetos son iguales, false si no.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Carton80M)) {
			return false;
		}
		Carton80M other = (Carton80M) obj;
		return id == other.id && numEspeciales == other.numEspeciales
				&& numeroAciertosBingo == other.numeroAciertosBingo && numeroAciertosLinea == other.numeroAciertosLinea
				&& numeroColumnas == other.numeroColumnas && numeroFilas == other.numeroFilas
				&& numeroMaximo == other.numeroMaximo && Objects.equals(numeros, other.numeros)
				&& Float.floatToIntBits(precio) == Float.floatToIntBits(other.precio)
				&& Float.floatToIntBits(premio) == Float.floatToIntBits(other.premio);
	}

	/**
	 * Método toString que devuelve una representación en cadena del objeto
	 * Carton80M.
	 *
	 * @return Cadena que representa el objeto.
	 */
	@Override
	public String toString() {
		return "Carton75H [id=" + id + ", precio=" + precio + ", premio=" + premio + ", numeros=" + numeros
				+ ", numeroFilas=" + numeroFilas + ", numeroColumnas=" + numeroColumnas + ", numeroMaximo="
				+ numeroMaximo + ", numeroAciertosBingo=" + numeroAciertosBingo + ", numeroAciertosLinea="
				+ numeroAciertosLinea + ", numEspeciales=" + numEspeciales + "]";
	}

	/**
	 * Obtiene la cantidad de numeros del cartón que se han cantado.
	 *
	 * @return Número total de aciertos en el cartón.
	 */
	@Override
	public int getAciertos() {
		int cont = 0;
		for (int i = 0; i < numeroFilas; i++) {
			for (int j = 0; j < numeroColumnas; j++) {
				if (numeros[i][j].getEstado() == 2) {
					cont++;
				}
			}
		}
		return cont;
	}

	/**
	 * Verifica si hay una línea completa en el cartón.
	 *
	 * @return true si hay una línea completa, false si no.
	 */
	@Override
	public boolean comprobarLinea() {
		for (int i = 0; i < numeroFilas; i++) {
			if (comprobarLinea(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica si hay una línea completa en una fila específica del cartón. Si se
	 * encuentra una línea, actualiza el estado del cartón.
	 *
	 * @param fila Fila a verificar.
	 * @return true si hay línea en la fila especificada, false si no.
	 */
	@Override
	public boolean comprobarLinea(int fila) {
		int cont = 0;
		for (int j = 0; j < numeroColumnas; j++) {
			if (numeros[fila][j].getEstado() == 2) {
				cont++;
				if (cont == numeroAciertosLinea) {
					if (estado == EstadoCarton.ESPECIAL) {
						estado = EstadoCarton.ESPECIAL_LINEA;
					} else if (estado == EstadoCarton.NADA) {
						estado = EstadoCarton.LINEA;
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Marca la celda de cartón que contiene el número especificado como cantado.
	 * Además, incrementa el contador de todos los números marcados como cantados
	 * para comprobaciones posteriores.
	 *
	 * @param numero Número cantado a marcar en el cartón.
	 * @return true si se encuentra y marca la celda, false si no se encuentra el
	 *         número.
	 */
	@Override
	public boolean recibirNumero(int numero) {
		for (int i = 0; i < numeroFilas; i++) {
			for (int j = 0; j < numeroColumnas; j++) {
				if (numeros[i][j].getNumero() == numero) {
					numeros[i][j].setEstado(2);
					contTodosB++;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Verifica si se ha completado un bingo en el cartón. Si se ha completado,
	 * actualiza el estado del cartón dependiendo de su estado actual.
	 *
	 * @return true si se ha completado un bingo, false si no.
	 */
	@Override
	public boolean comprobarBingo() {

		if (contTodosB == numeroAciertosBingo) {
			if (estado == EstadoCarton.ESPECIAL) {
				estado = EstadoCarton.ESPECIAL_BINGO;
			} else if (estado == EstadoCarton.LINEA) {
				estado = EstadoCarton.LINEA_BINGO;
			} else if (estado == EstadoCarton.ESPECIAL_LINEA) {
				estado = EstadoCarton.ESPECIAL_LINEA_BINGO;
			} else if (estado == EstadoCarton.NADA) {
				estado = EstadoCarton.BINGO;
			}
			return true;
		}
		return false;
	}

	/**
	 * Verifica si se han completado todos los números especiales en el cartón. Si
	 * se han completado, actualiza el estado del cartón según sea necesario.
	 *
	 * @return true si se han completado todos los números especiales, false si no.
	 */
	@Override
	public boolean comprobarEspeciales() {
		int cont = 0;
		for (int i = 0; i < numeroFilas; i++) {
			for (int j = 0; j < numeroColumnas; j++) {
				if (numeros[i][j].isEspecial() == true && numeros[i][j].getEstado() == 2) {
					cont++;
				}
			}
		}
		if (cont == numEspeciales) {
			if (estado == EstadoCarton.LINEA) {
				estado = EstadoCarton.ESPECIAL_LINEA;
			} else if (estado == EstadoCarton.NADA) {
				estado = EstadoCarton.ESPECIAL;
			}
			return true;
		}
		return false;
	}

	/**
	 * Se hace el reparto de números por el cartón. Primero se ponen 2 números por
	 * cada columna, siguiendo con el patrón establecido de qué números van en cada
	 * columna. Después añade los 4 últimos números de forma aleatoria, pero
	 * evitando que se rellene una columna entera y asegurandose de que en todas las
	 * filas haya 3 espacios en blanco. Por último se ponen especiales 3 números
	 * aleatorios del cartón.
	 */
	@Override
	public boolean repartir() {
		boolean[][] matrizbool = new boolean[numeroFilas][numeroColumnas];
		int cont = 0;
		Random random = new Random();
		Random random2 = new Random();
		int aleatorio = 0;
		for (int i = 0; i < numeroFilas; i++) {
			for (int j = 0; j < numeroColumnas; j++) {
				matrizbool[i][j] = false;
			}
		}
		for (int i = 0; i < numeroColumnas; i++) {
			int indice1 = 0;
			do {
				indice1 = random.nextInt(4);
			} while (comprobarFila(indice1));

			int indice2;
			do {
				indice2 = random.nextInt(4);
			} while (indice2 == indice1 || comprobarFila(indice2));
			for (int j = 0; j < numeroFilas; j++) {
				if (j == indice1 || j == indice2) { // Aqui entra 2 veces en 2 posiciones random de 4.
					do {
						aleatorio = random2.nextInt((i + 1) * 10 - i * 10) + i * 10;
					} while (estanum(aleatorio));
					numeros[j][i] = new CeldaCarton(aleatorio, 1, j, i);
					matrizbool[j][i] = true;
				}

			}
		}
		// Ahora quedan 4 numeros random.
		do {
			int indice1 = random.nextInt(8);
			int indice2 = random2.nextInt(4);
			if (!matrizbool[indice2][indice1]) {
				if (!columnaBien(indice1) && !comprobarFila(indice2)) {
					do {
						aleatorio = random2.nextInt((indice1 + 1) * 10 - indice1 * 10) + indice1 * 10;
					} while (estanum(aleatorio));
					numeros[indice2][indice1] = new CeldaCarton(aleatorio, 1, indice2, indice1);
					matrizbool[indice2][indice1] = true;
					cont++;
				}
			}

		} while (cont < 4);

		// Ahora anado los 3 especiales random
		cont = 0;
		do {
			int indice1 = random.nextInt(8);
			int indice2 = random2.nextInt(4);
			if (numeros[indice2][indice1].estado == 1 && !numeros[indice2][indice1].isEspecial()) {
				numeros[indice2][indice1].setEspecial(true);
				cont++;
			}
		} while (cont < 3);

		return true;
	}

	/**
	 * Verifica si la fila especificada tiene cinco celdas rellenas de números. Si
	 * lo está, significa que está completa y bien.
	 * 
	 * @param fil El índice de la fila que se desea verificar.
	 * @return true si la fila tiene exactamente cinco celdas ocupadas, false si no.
	 */
	private boolean comprobarFila(int fil) {
		int cont = 0;
		for (int j = 0; j < 8; j++) {
			if (numeros[fil][j].getNumero() != 0) {
				cont++;
			}
		}
		if (cont == 5) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica si la columna especificada tiene exactamente tres celdas rellenas
	 * con números. Si tiene 3 celdas rellenas significa que la columna está
	 * correcta.
	 * 
	 * @param num El índice de la columna que se desea verificar.
	 * @return true si la columna tiene exactamente tres celdas rellenas, false si
	 *         no
	 */
	private boolean columnaBien(int num) {
		int cont = 0;
		for (int i = 0; i < 4; i++) {
			if (numeros[i][num].getNumero() != 0) {
				cont++;
			}
		}
		if (cont == 3) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica si un número específico está presente en el cartón, comprobando
	 * todas las celdas hasta encontrarlo
	 * 
	 * @param num El número que se quiere buscar
	 * @return true si el número está en el cartón, false si no.
	 */
	private boolean estanum(int num) {
		for (int i = 0; i < numeroFilas; i++) {
			for (int j = 0; j < numeroColumnas; j++) {
				if (numeros[i][j].getNumero() == num) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Añade una celda de cartón con un número pasado por parametro a la matriz de
	 * celdas de cartón.
	 * 
	 * @param c La celda de cartón que se desea agregar a la matriz.
	 * @return true si la celda se agrega, false si no.
	 */
	@Override
	public boolean addNumero(CeldaCarton c) {
		numeros[c.getFila()][c.getColumna()] = c;
		return true;
	}

	/**
	 * Añade un número especial a la matriz de celdas de cartón.
	 * 
	 * Recorre la matriz para encontrar la celda que contiene el número pasado por
	 * parametro y marca esa celda como especial.
	 * 
	 * @param num El número especial que se quiere añadir.
	 * @return true si se encontró y marcó la celda como especial, false si no se ha
	 *         encontrado el numero.
	 */
	@Override
	public boolean addEspecial(int num) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				if (numeros[i][j].getNumero() == num) {
					numeros[i][j].setEspecial(true);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Establece la matriz de celdas de cartón con los números proporcionados.
	 * 
	 * @param numeros Una matriz bidimensional de tipo CeldaCarton que representa
	 *                los números del cartón.
	 */
	public void setNumeros(CeldaCarton[][] numeros) {
		this.numeros = numeros;
	}

	/**
	 * Implementación de la interfaz que permite establecer la matriz de celdas de
	 * cartón utilizando un objeto. El objeto proporcionado será una matriz
	 * bidimensional de tipo CeldaCarton.
	 * 
	 * @param numeros Una matriz bidimensional de tipo Object, que será CeldaCarton
	 *                y representa los números del cartón.
	 */
	@Override
	public void setNumeros(Object numeros) {
		this.numeros = (CeldaCarton[][]) numeros;
	}

}
