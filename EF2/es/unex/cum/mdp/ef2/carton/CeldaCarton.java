package es.unex.cum.mdp.ef2.carton;

import java.util.Objects;

/**
 * Clase que representa una celda en un cartón de bingo. Además de heredar la
 * funcionalidad básica de una celda, esta clase incluye información adicional
 * específica de un cartón de bingo, como la fila y columna en la que se
 * encuentra, si es especial, y las rutas de las imágenes asociadas.
 * 
 * @author José Luis Plata Gallardo
 * @author Clara Galván Bermúdez
 * 
 */
public class CeldaCarton extends Celda {

	private int fila;
	private int columna;
	private boolean especial;
	private String RutaImagen;
	private String RutaReverso;

	/**
	 * Constructor por defecto de CeldaCarton.
	 */
	public CeldaCarton() {
		fila = 0;
		columna = 0;
		especial = false;
		RutaImagen = new String();
		RutaReverso = new String();
	}

	/**
	 * Constructor parametrizado 1 de CeldaCarton.
	 *
	 * @param f  La fila en la que se encuentra la celda.
	 * @param c  La columna en la que se encuentra la celda.
	 * @param e  Indica si la celda es especial.
	 * @param ri La ruta de la imagen asociada a la celda.
	 * @param rr La ruta de la imagen en el reverso de la celda.
	 */
	public CeldaCarton(int f, int c, boolean e, String ri, String rr) {
		fila = f;
		columna = c;
		especial = e;
		RutaImagen = ri;
		RutaReverso = rr;
	}

	/**
	 * Constructor parametrizado 1 de CeldaCarton.
	 *
	 * @param num Número asociado a la celda.
	 * @param est Estado de la celda.
	 * @param fil Fila en la que se encuentra la celda.
	 * @param col Columna en la que se encuentra la celda.
	 * @param esp Indica si la celda es especial.
	 */
	public CeldaCarton(int num, int est, int fil, int col, boolean esp) {
		super(num, est);
		fila = fil;
		especial = esp;
		columna = col;
	}

	/**
	 * Constructor parametrizado 3 de CeldaCarton.
	 *
	 * @param num Número asociado a la celda.
	 * @param est Estado de la celda.
	 * @param c   CeldaCarton existente a partir de la cual se inicializan los
	 *            atributos.
	 */
	public CeldaCarton(int num, int est, CeldaCarton c) {
		super.numero = num;
		super.estado = est;
		this.fila = c.fila;
		this.columna = c.columna;
		this.especial = c.especial;
		this.RutaImagen = c.RutaImagen;
		this.RutaReverso = c.RutaReverso;
	}

	/**
	 * Constructor parametrizado 4 de CeldaCarton.
	 *
	 * @param num Número asociado a la celda.
	 * @param est Estado de la celda.
	 * @param fil Fila en la que se encuentra la celda.
	 * @param col Columna en la que se encuentra la celda.
	 */
	public CeldaCarton(int num, int est, int fil, int col) {
		super(num, est);
		fila = fil;
		columna = col;
	}

	/**
	 * Devuelve el valor del atributo fila.
	 *
	 * @return El valor de la fila.
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * Modifica el valor del atributo fila.
	 *
	 * @param fila El valor de la fila a asignar.
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**
	 * Devuelve el valor del atributo columna.
	 *
	 * @return El valor de la columna.
	 */
	public int getColumna() {
		return columna;
	}

	/**
	 * Modifica el valor del atributo columna.
	 *
	 * @param columna El valor de la columna a asignar.
	 */
	public void setColumna(int columna) {
		this.columna = columna;
	}

	/**
	 * Devuelve el valor del atributo especial.
	 *
	 * @return true si el cartón es especial, false si no lo es.
	 */
	public boolean isEspecial() {
		return especial;
	}

	/**
	 * Modifica el valor del atributo especial.
	 *
	 * @param especial true si el cartón es especial, false si no lo es.
	 */
	public void setEspecial(boolean especial) {
		this.especial = especial;
	}

	/**
	 * Devuelve la ruta de la imagen asociada al cartón.
	 *
	 * @return La ruta de la imagen.
	 */
	public String getRutaImagen() {
		return RutaImagen;
	}

	/**
	 * Modifica la ruta de la imagen asociada al cartón.
	 *
	 * @param rutaImagen La nueva ruta de la imagen a asignar.
	 */
	public void setRutaImagen(String rutaImagen) {
		RutaImagen = rutaImagen;
	}

	/**
	 * Devuelve la ruta del reverso del cartón.
	 *
	 * @return La ruta del reverso.
	 */
	public String getRutaReverso() {
		return RutaReverso;
	}

	/**
	 * Modifica la ruta del reverso del cartón.
	 *
	 * @param rutaReverso La nueva ruta del reverso a asignar.
	 */
	public void setRutaReverso(String rutaReverso) {
		RutaReverso = rutaReverso;
	}

	/**
	 * Clase que representa una celda en un cartón de bingo. Además de heredar la
	 * funcionalidad básica de una celda, esta clase incluye información adicional
	 * específica de un cartón de bingo, como la fila y columna en la que se
	 * encuentra, si es especial, y las rutas de las imágenes asociadas.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(RutaImagen, RutaReverso, columna, especial, fila);
		return result;
	}

	/**
	 * Compara esta celda de cartón con otro objeto para determinar si son iguales.
	 * Dos celdas de cartón son iguales si tienen los mismos atributos: fila,
	 * columna, si son especiales, y las rutas de imagen y reverso.
	 *
	 * @param obj El objeto con el que se va a comparar.
	 * @return true si las celdas son iguales, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CeldaCarton other = (CeldaCarton) obj;
		return Objects.equals(RutaImagen, other.RutaImagen) && Objects.equals(RutaReverso, other.RutaReverso)
				&& columna == other.columna && especial == other.especial && fila == other.fila;
	}

	/**
	 * Devuelve una representación en cadena de la celda de cartón.
	 *
	 * @return Cadena que representa la celda de cartón.
	 */
	@Override
	public String toString() {
		return "CeldaCarton [fila=" + fila + ", columna=" + columna + ", especial=" + especial + ", RutaImagen="
				+ RutaImagen + ", RutaReverso=" + RutaReverso + "]";
	}

}
