package es.unex.cum.mdp.ef3.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase donde se almacenan todas las estadísticas de los bingos del casino.
 * Sólo habrá una instancia y será la que se encargue de guardar todo.
 * 
 * @author José Luis Plata Gallardo
 * @author Clara Galván Bermúdez
 * 
 * 
 */
public class Estadistica implements Serializable {
	private int numero;
	private int numSacado;
	private int numLinea;
	private int numBingo;
	private int numEspecial;

	/**
	 * Constructor por defecto de Estadística.
	 */
	public Estadistica() {
		numero = 0;
		numSacado = 0;
		numLinea = 0;
		numBingo = 0;
		numEspecial = 0;
	}

	/**
	 * Constructor parametrizado de estadística.
	 *
	 * @param n  Número.
	 * @param nS Cantidad de veces sacado.
	 * @param nL Cantidad de veces contribuido a una línea.
	 * @param nB Cantidad de veces contribuido a un bingo.
	 * @param nE Cantidad de veces cantado en grupo como número especial.
	 */
	public Estadistica(int n, int nS, int nL, int nB, int nE) {
		numero = n;
		numSacado = nS;
		numLinea = nL;
		numBingo = nB;
		numEspecial = nE;
	}

	/**
	 * Constructor parametrizado para inicializar sólo el número.
	 *
	 * @param n Número.
	 */
	public Estadistica(int n) {
		this.numero = n;
		numSacado = 0;
		numLinea = 0;
		numBingo = 0;
		numEspecial = 0;
	}

	/**
	 * Devuelve el número asociado a la estadística.
	 *
	 * @return El número asociado a la estadística.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Modifica el número asociado a la estadística.
	 *
	 * @param numero El nuevo número a asignar a la estadística.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Devuelve la cantidad de veces que el número fue sacado.
	 *
	 * @return La cantidad de veces que el número fue sacado.
	 */
	public int getNumSacado() {
		return numSacado;
	}

	/**
	 * Modifica la cantidad de veces que el número fue sacado.
	 *
	 * @param numSacado La nueva cantidad de veces que el número fue sacado a
	 *                  asignar a la estadística.
	 */
	public void setNumSacado(int numSacado) {
		this.numSacado = numSacado;
	}

	/**
	 * Devuelve la cantidad de veces que ese número ha completado una línea.
	 *
	 * @return La cantidad de veces que ese número ha completado una línea.
	 */
	public int getNumLinea() {
		return numLinea;
	}

	/**
	 * Modifica la cantidad de veces que ese número ha completado una línea.
	 *
	 * @param numLinea La nueva cantidad de veces que ese número ha completado una
	 *                 línea a asignar a la estadística.
	 */
	public void setNumLinea(int numLinea) {
		this.numLinea = numLinea;
	}

	/**
	 * Devuelve la cantidad de veces que ese número ha completado un Bingo.
	 *
	 * @return La cantidad de veces que ese número ha completado un Bingo.
	 */
	public int getNumBingo() {
		return numBingo;
	}

	/**
	 * Modifica la cantidad de veces que ese número ha completado un Bingo.
	 *
	 * @param numBingo La nueva cantidad de veces que ese número ha completado un
	 *                 Bingo a asignar a la estadística.
	 */
	public void setNumBingo(int numBingo) {
		this.numBingo = numBingo;
	}

	/**
	 * Devuelve la cantidad de veces que ese número ha completado un especial.
	 *
	 * @return La cantidad de veces que ese número ha completado un especial.
	 */
	public int getNumEspecial() {
		return numEspecial;
	}

	/**
	 * Modifica la cantidad de veces que ese número ha completado un especial.
	 *
	 * @param numEspecial La nueva cantidad de veces que ese número ha completado un
	 *                    especial.
	 */
	public void setNumEspecial(int numEspecial) {
		this.numEspecial = numEspecial;
	}

	/**
	 * Devuelve una representación en cadena de la instancia.
	 *
	 * @return Representación en cadena de la instancia.
	 */
	@Override
	public String toString() {
		return "Estadistica [numero=" + numero + ", numSacado=" + numSacado + ", numLinea=" + numLinea + ", numBingo="
				+ numBingo + ", numEspecial=" + numEspecial + "]";
	}

	/**
	 * Calcula y devuelve el código hash basado en los campos de la instancia.
	 *
	 * @return El código hash calculado.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(numBingo, numEspecial, numLinea, numSacado, numero);
	}

	/**
	 * Compara esta instancia con otro objeto y determina si son iguales.
	 *
	 * @param obj Objeto a comparar con esta instancia.
	 * @return `true` si son iguales, `false` en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estadistica other = (Estadistica) obj;
		return numBingo == other.numBingo && numEspecial == other.numEspecial && numLinea == other.numLinea
				&& numSacado == other.numSacado && numero == other.numero;
	}

}
