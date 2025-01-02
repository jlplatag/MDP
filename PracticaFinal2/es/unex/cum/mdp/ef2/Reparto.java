package es.unex.cum.mdp.ef2;

import java.util.Objects;

/**
 * Clase que representa el reparto tras acabar una partida de Bingo. En ella se
 * guarda el dinero recaudado y las veces que se han cantado linea, bingo y
 * especiales.
 * 
 * @author José Luis Plata Gallardo
 * @author Clara Galván Bermúdez
 * 
 */
public class Reparto {
	private int numLineas;
	private int numBingo;
	private int numEspeciales;
	private float repartoEspeciales;
	private float repartoLinea;
	private float repartoBingo;
	private float caja;
	private float recaudacion;

	/**
	 * Constructor por defecto de Reparto.
	 */
	public Reparto() {
		this.numLineas = 0;
		this.numBingo = 0;
		this.numEspeciales = 0;
		this.repartoEspeciales = 0;
		this.repartoLinea = 0;
		this.repartoBingo = 0;
		this.caja = 0;
		this.recaudacion = 0;
	}

	/**
	 * Constructor parametrizado de Reparto.
	 *
	 * @param numLineas         Número de líneas.
	 * @param numBingo          Número de bingos.
	 * @param numEspeciales     Número de especiales.
	 * @param repartoEspeciales Monto de reparto para especiales.
	 * @param repartoLinea      Monto de reparto para líneas.
	 * @param repartoBingo      Monto de reparto para bingos.
	 * @param caja              Monto total en la caja.
	 * @param recaudacion       Monto total recaudado.
	 */
	public Reparto(int numLineas, int numBingo, int numEspeciales, float repartoEspeciales, float repartoLinea,
			float repartoBingo, float caja, float recaudacion) {
		super();
		this.numLineas = numLineas;
		this.numBingo = numBingo;
		this.numEspeciales = numEspeciales;
		this.repartoEspeciales = repartoEspeciales;
		this.repartoLinea = repartoLinea;
		this.repartoBingo = repartoBingo;
		this.caja = caja;
		this.recaudacion = recaudacion;
	}

	/**
	 * Constructor de copia que crea una nueva instancia de Reparto a partir de otra
	 * instancia dada.
	 *
	 * @param r Instancia de Reparto a copiar.
	 */
	public Reparto(Reparto r) {
		super();
		this.numLineas = r.numLineas;
		this.numBingo = r.numBingo;
		this.numEspeciales = r.numEspeciales;
		this.repartoEspeciales = r.repartoEspeciales;
		this.repartoLinea = r.repartoLinea;
		this.repartoBingo = r.repartoBingo;
		this.caja = r.caja;
		this.recaudacion = r.recaudacion;
	}

	/**
	 * Devuelve el número de personas que han cantado línea.
	 *
	 * @return El número de personas que han cantado línea.
	 */
	public int getNumLineas() {
		return numLineas;
	}

	/**
	 * Modifica el número de personas que han cantado línea.
	 *
	 * @param numLineas El nuevo número de personas que han cantado línea.
	 */
	public void setNumLineas(int numLineas) {
		this.numLineas = numLineas;
	}

	/**
	 * Devuelve el número de personas que han cantado bingo.
	 *
	 * @return El número de personas que han cantado bingo.
	 */
	public int getNumBingo() {
		return numBingo;
	}

	/**
	 * Modifica el número de personas que han cantado bingo.
	 *
	 * @param numBingo El nuevo número de personas que han cantado bingo.
	 */
	public void setNumBingo(int numBingo) {
		this.numBingo = numBingo;
	}

	/**
	 * Devuelve el número de personas que han cantado un especial.
	 *
	 * @return El número de personas que han cantado un especial.
	 */
	public int getNumEspeciales() {
		return numEspeciales;
	}

	/**
	 * Modifica el número de personas que han cantado un especial.
	 *
	 * @param numEspeciales El nuevo número de personas que han cantado un especial.
	 */
	public void setNumEspeciales(int numEspeciales) {
		this.numEspeciales = numEspeciales;
	}

	/**
	 * Devuelve el dinero que se reparte por cantar especiales.
	 *
	 * @return El dinero que se reparte por cantar especiales.
	 */
	public float getRepartoEspeciales() {
		return repartoEspeciales;
	}

	/**
	 * Modifica el dinero que se reparte por cantar especiales.
	 *
	 * @param repartoEspeciales El nuevo dinero que se reparte por cantar
	 *                          especiales.
	 */
	public void setRepartoEspeciales(float repartoEspeciales) {
		this.repartoEspeciales = repartoEspeciales;
	}

	/**
	 * Devuelve el dinero que se reparte por cantar línea.
	 *
	 * @return El dinero que se reparte por cantar línea.
	 */
	public float getRepartoLinea() {
		return repartoLinea;
	}

	/**
	 * Modifica el dinero que se reparte por cantar línea.
	 *
	 * @param repartoLinea El nuevo dinero que se reparte por cantar línea.
	 */
	public void setRepartoLinea(float repartoLinea) {
		this.repartoLinea = repartoLinea;
	}

	/**
	 * Devuelve el dinero que se reparte por cantar bingo.
	 *
	 * @return El dinero que se reparte por cantar bingo.
	 */
	public float getRepartoBingo() {
		return repartoBingo;
	}

	/**
	 * Modifica el dinero que se reparte por cantar bingo.
	 *
	 * @param repartoBingo El nuevo dinero que se reparte por cantar bingo.
	 */
	public void setRepartoBingo(float repartoBingo) {
		this.repartoBingo = repartoBingo;
	}

	/**
	 * Devuelve el dinero que se lleva la caja.
	 *
	 * @return El dinero que se lleva la caja.
	 */
	public float getCaja() {
		return caja;
	}

	/**
	 * Modifica el dinero que se lleva la caja.
	 *
	 * @param caja El nuevo dinero que se lleva la caja.
	 */
	public void setCaja(float caja) {
		this.caja = caja;
	}

	/**
	 * Devuelve el dinero recaudado en el bingo.
	 *
	 * @return El dinero dinero recaudado en el bingo.
	 */
	public float getRecaudacion() {
		return recaudacion;
	}

	/**
	 * Modifica el dinero recaudado en el bingo.
	 *
	 * @param recaudacion El nuevo dinero recaudado en el bingo.
	 */
	public void setRecaudacion(float recaudacion) {
		this.recaudacion = recaudacion;
	}

	/**
	 * Calcula y devuelve el código hash basado en los campos de la instancia.
	 *
	 * @return El código hash calculado.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(caja, numBingo, numEspeciales, numLineas, recaudacion, repartoBingo, repartoEspeciales,
				repartoLinea);
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
		Reparto other = (Reparto) obj;
		return Float.floatToIntBits(caja) == Float.floatToIntBits(other.caja) && numBingo == other.numBingo
				&& numEspeciales == other.numEspeciales && numLineas == other.numLineas
				&& Float.floatToIntBits(recaudacion) == Float.floatToIntBits(other.recaudacion)
				&& Float.floatToIntBits(repartoBingo) == Float.floatToIntBits(other.repartoBingo)
				&& Float.floatToIntBits(repartoEspeciales) == Float.floatToIntBits(other.repartoEspeciales)
				&& Float.floatToIntBits(repartoLinea) == Float.floatToIntBits(other.repartoLinea);
	}

	/**
	 * Devuelve una representación en cadena de la instancia.
	 *
	 * @return Representación en cadena de la instancia.
	 */
	@Override
	public String toString() {
		return "Reparto [numLineas=" + numLineas + ", numBingo=" + numBingo + ", numEspeciales=" + numEspeciales
				+ ", repartoEspeciales=" + repartoEspeciales + ", repartoLinea=" + repartoLinea + ", repartoBingo="
				+ repartoBingo + ", caja=" + caja + ", recaudacion=" + recaudacion + "]";
	}

}
