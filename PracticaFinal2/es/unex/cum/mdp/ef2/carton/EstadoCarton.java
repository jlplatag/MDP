package es.unex.cum.mdp.ef2.carton;

/**
 * Enumeración que representa los posibles estados de un cartón en un juego de
 * bingo.
 * 
 * @author José Luis Plata Gallardo
 * @author Clara Galván Bermúdez
 * 
 */
public enum EstadoCarton {
	/**
	 * NADA (Cartón no premiado)
	 * 
	 * ESPECIAL (Cartón con todos los especiales cantados)
	 * 
	 * LINEA (Cartón con linea cantada)
	 * 
	 * ESPECIAL_LINEA (Cartón con todos los especiales cantados y linea cantada)
	 * 
	 * ESPECIAL_BINGO (Cartón con bingo cantado y especiales cantados)
	 * 
	 * LINEA_BINGO (Cartón con bingo cantado y linea cantada)
	 * 
	 * BINGO (Cartón con bingo cantado)
	 * 
	 * ESPECIAL_LINEA_BINGO (Cartón con bingo cantado, especiales cantados y linea
	 * cantada)
	 */
	/**
	 * NADA (Cartón no premiado)
	 */
	NADA,

	/**
	 * ESPECIAL (Cartón con todos los especiales cantados)
	 */
	ESPECIAL,

	/**
	 * LINEA (Cartón con línea cantada)
	 */
	LINEA,

	/**
	 * ESPECIAL_LINEA (Cartón con todos los especiales cantados y línea cantada)
	 */
	ESPECIAL_LINEA,

	/**
	 * ESPECIAL_BINGO (Cartón con bingo cantado y todos los especiales cantados)
	 */
	ESPECIAL_BINGO,

	/**
	 * LINEA_BINGO (Cartón con bingo cantado y línea cantada)
	 */
	LINEA_BINGO,

	/**
	 * BINGO (Cartón con bingo cantado)
	 */
	BINGO,

	/**
	 * ESPECIAL_LINEA_BINGO (Cartón con bingo cantado, todos los especiales cantados
	 * y línea cantada)
	 */
	ESPECIAL_LINEA_BINGO;
}