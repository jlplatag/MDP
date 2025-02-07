package es.unex.cum.mdp.ef3.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase abstracta Celda que representa una celda genérica con un número y un
 * estado asociado. Esta clase es la base para la creación de celdas específicas
 * con funcionalidades adicionales.
 * 
 * Contiene dos atributos: numero que representa el número que representa esa
 * celda y estado, el estado en el que se encuentra esa celda o (0 - en el
 * cartón, 1- en el cartón y es un número y 2 - en el cartón y está cantado).
 * 
 * @author Clara Galván Bermúdez
 * @author José Luis Plata Gallardo
 */
public abstract class Celda implements Serializable {
	protected int numero;
	protected int estado;

	/**
	 * Constructor por defecto que inicializa numero y estado a 0.
	 */
	public Celda() {
		numero = 0;
		estado = 0;
	}

	/**
	 * Constructor que permite inicializar numero y estado con valores específicos.
	 *
	 * @param n Número a asignar a la celda.
	 * @param e Estado a asignar a la celda.
	 */

	public Celda(int n, int e) {
		numero = n;
		estado = e;
	}

	/**
	 * Se encarga de devolver el valor del atributo numero que se corresponde con el
	 * número que representa la celda.
	 * 
	 * @return numero El número que representa la celda de un cartón.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Se encarga de modificar el valor del atributo numero que se corresponde con
	 * el número de la celda correspondiente.
	 * 
	 * @param numero El número a asignar a la celda.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Se encarga de devolver el valor del atributo estado que se corresponde con el
	 * estado de la celda correspondiente.
	 * 
	 * @return estado El estado que representa la celda de un cartón.
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * Se encarga de modificar el valor del atributo estado que se corresponde con
	 * el estado de la celda correspondiente.
	 * 
	 * @param estado El valor del estado a asignar a la celda.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * Devuelve una representación en cadena de la celda, mostrando su número y
	 * estado.
	 *
	 * @return Representación en cadena de la celda.
	 */
	@Override
	public String toString() {
		return "Celda [numero=" + numero + ", estado=" + estado + "]";
	}

	/**
	 * Calcula el código hash de la celda basado en sus atributos numero y estado.
	 *
	 * @return Código hash de la celda.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(estado, numero);
	}

	/**
	 * Compara si dos celdas son iguales basándose en sus atributos numero y estado.
	 *
	 * @param obj Objeto a comparar con la celda.
	 * @return true si las celdas son iguales, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return estado == other.estado && numero == other.numero;
	}

}
