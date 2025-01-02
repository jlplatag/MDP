package es.unex.cum.mdp.ef2;

import java.util.Queue;

/**
 * Interfaz que define el comportamiento de una bolsa utilizada en un juego de
 * Bingo. 
 * 
 * @author José Luis Plata Gallardo
 * @author Clara Galván Bermúdez
 * 
 */
public interface Bolsa {

	/**
	 * Añade un número a la bolsa.
	 *
	 * @param bola El número a añadir a la bolsa.
	 */
	public void addNumero(int bola);

	/**
	 * Obtiene la cola (Queue) que representa la bolsa.
	 *
	 * @return La cola que representa la bolsa.
	 */
	public Queue getBolsa();

	/**
	 * Ordena los elementos de la bolsa según algún criterio definido.
	 */
	public void ordenar();

	/**
	 * Desordena los elementos de la bolsa.
	 */
	public void desordenar();

	/**
	 * Obtiene el total de bolas en la bolsa.
	 *
	 * @return El número total de bolas en la bolsa.
	 */
	public int getTotalBolas();

	/**
	 * Verifica si la bolsa está vacía.
	 *
	 * @return true si la bolsa está vacía, false en caso contrario.
	 */
	public boolean bolsaVacia();

	/**
	 * Saca una bola de la bolsa.
	 *
	 * @return La bola extraída de la bolsa.
	 */
	public Object sacarBola();

	/**
	 * Muestra el primer elemento de la bolsa sin extraerlo.
	 *
	 * @return El primer elemento de la bolsa.
	 */
	public Object mostrarPrimero();

}
