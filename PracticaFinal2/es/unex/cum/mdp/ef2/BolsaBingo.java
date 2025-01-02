package es.unex.cum.mdp.ef2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

/**
 * Implementación de la interfaz Bolsa que representa una bolsa de Bingo.
 * 
 * @author José Luis Plata Gallardo
 * @author Clara Galván Bermúdez
 * 
 */
public class BolsaBingo implements Bolsa {
	private Queue bolsa;
	private int numMaxBolas;

	/**
	 * Constructor por defecto de BolsaBingo.
	 */
	public BolsaBingo() {
		bolsa = new LinkedList<Integer>();
		numMaxBolas = 0;
		this.rellenarBolas();
	}

	/**
	 * Constructor parametrizado 1 de BolsaBingo.
	 *
	 * @param tam El tamaño máximo de bolas en la bolsa.
	 */
	public BolsaBingo(int tam) {
		numMaxBolas = tam;
		bolsa = new LinkedList<Integer>();
		this.rellenarBolas();
	}

	/**
	 * Constructor parametrizado 2 de BolsaBingo.
	 *
	 * @param bolsa       La cola que representa la bolsa.
	 * @param numMaxBolas El tamaño máximo de bolas en la bolsa.
	 */
	public BolsaBingo(Queue bolsa, int numMaxBolas) {
		super();
		this.bolsa = bolsa;
		this.numMaxBolas = numMaxBolas;
		this.rellenarBolas();
	}

	/**
	 * Devuelve la bolsa de bolas asociada al objeto BolsaBingo.
	 *
	 * @return La bolsa de bolas asociada al objeto BolsaBingo.
	 */
	public Queue getBolsa() {
		return bolsa;
	}

	/**
	 * Modifica la bolsa de bolas asociada al objeto BolsaBingo.
	 *
	 * @param bolsa La nueva bolsa de bolas a asignar al objeto BolsaBingo.
	 */
	public void setBolsa(Queue bolsa) {
		this.bolsa = bolsa;
	}

	/**
	 * Devuelve el número máximo de bolas permitidas en la bolsa de bingo (75, 80 o
	 * 90).
	 *
	 * @return El número máximo de bolas permitidas en la bolsa de bingo.
	 */
	public int getNumMaxBolas() {
		return numMaxBolas;
	}

	/**
	 * Modifica el número máximo de bolas permitidas en la bolsa de bingo.
	 *
	 * @param numMaxBolas El nuevo número máximo de bolas a asignar a la bolsa de
	 *                    bingo.
	 */
	public void setNumMaxBolas(int numMaxBolas) {
		this.numMaxBolas = numMaxBolas;
	}

	/**
	 * Devuelve un valor hash para la bolsa de bingo. Este método utiliza los
	 * valores de la bolsa y el número máximo de bolas para calcular el hash.
	 *
	 * @return El valor hash calculado para la bolsa de bingo.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(bolsa, numMaxBolas);
	}

	/**
	 * Compara esta bolsa de bingo con otro objeto para determinar la igualdad. Dos
	 * bolsas de bingo son iguales si tienen la misma lista de bolas y el mismo
	 * número máximo de bolas.
	 *
	 * @param obj El objeto con el que se va a comparar esta bolsa de bingo.
	 * @return {@code true} si las bolsas son iguales, {@code false} en caso
	 *         contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BolsaBingo other = (BolsaBingo) obj;
		return Objects.equals(bolsa, other.bolsa) && numMaxBolas == other.numMaxBolas;
	}

	/**
	 * Devuelve una representación en cadena de esta bolsa de bingo. La cadena
	 * resultante incluye la lista de bolas y el número máximo de bolas.
	 *
	 * @return Una cadena que representa esta bolsa de bingo.
	 */
	@Override
	public String toString() {
		return "BolsaBingo [bolsa=" + bolsa + ", numMaxBolas=" + numMaxBolas + "]";
	}

	/**
	 * Agrega un número de bola a la bolsa. Este método permite añadir una bola con
	 * el número especificado a la bolsa existente.
	 *
	 * @param bola El número de bola que se va a agregar a la bolsa.
	 */
	@Override
	public void addNumero(int bola) {
		this.bolsa.add(bola);
	}

	/**
	 * Ordena los elementos en la bolsa de manera ascendente. Se usa .sort(null)
	 * para la ordenación. Se guarda la bolsa en una lista temporal, se ordena esta
	 * lista, se borra la bolsa original y se añade la lista ordenada a la bolsa.
	 */
	@Override
	public void ordenar() {
		LinkedList<Integer> lista = new LinkedList<>(bolsa);
		lista.sort(null);
		bolsa.clear();
		bolsa.addAll(lista);
	}

	/**
	 * Desordena los elementos en la bolsa de manera aleatoria. Se guarda la bolsa
	 * en una lista temporal, se desordena esta lista, se borra la bolsa original y
	 * se añade la lista desordenada a la bolsa.
	 */
	@Override
	public void desordenar() {
		LinkedList<Integer> lista = new LinkedList<>(bolsa);
		java.util.Collections.shuffle(lista);

		bolsa.clear();

		bolsa.addAll(lista);
	}

	@Override
	public int getTotalBolas() {
		return bolsa.size();
	}

	@Override
	public boolean bolsaVacia() {
		return bolsa.isEmpty();
	}

	@Override
	public Object sacarBola() {
		return bolsa.poll();
	}

	@Override
	public Object mostrarPrimero() {
		return bolsa.peek();
	}

	/**
	 * Rellena la bolsa con bolas numeradas desde 1 hasta el número máximo de bolas
	 * permitido (75, 80 u 90).
	 */
	private void rellenarBolas() {
		for (int i = 1; i < (numMaxBolas + 1); i++) {
			bolsa.add(i);
		}

	}
}
