package es.unex.cum.mdp.ef2;

import java.util.Date;
import java.util.Objects;

/**
 * La clase Movimiento es aquella que simboliza los movimientos económicos que
 * afectan a un usuario.
 * 
 * Se genera un movimiento cada vez que se ve afectado el saldo de un usuario en
 * estas determinadas situaciones: al comprar un cartón, al recargar dinero o
 * cuando le toca un premio.
 * 
 * Se crean una serie de atributos para esta clase como son: info (muestra
 * información del movimiento), monederoActual (valor monetario antes de que se
 * realizara el movimiento), valor (valor monetario que se ha realizado) y fecha
 * (la fecha en la que se ha realizado el movimiento o la fecha del Bingo
 * correspondiente).
 * 
 * @author Clara Galván Bermúdez 
 * @author José Luis Plata Gallardo
 */
public class Movimiento {
	protected String info;
	protected float monederoActual;
	protected float valor;
	protected Date fecha;

	/**
	 * Constructor por defecto que inicializa los valores por defecto. La
	 * información se inicializa como una cadena vacía, el monedero actual y el
	 * valor se establecen en 0.0, y la fecha se establece en la fecha actual.
	 */
	public Movimiento() {
		info = new String();
		monederoActual = 0.0F;
		valor = 0.0F;
		fecha = new Date();
	}

	/**
	 * Constructor que permite inicializar los valores del movimiento.
	 *
	 * @param i  Información asociada al movimiento.
	 * @param mA Estado actual del monedero después del movimiento.
	 * @param v  Valor del movimiento.
	 * @param f  Fecha del movimiento.
	 */
	public Movimiento(String i, float mA, float v, Date f) {
		info = i;
		monederoActual = mA;
		valor = v;
		fecha = f;
	}

	/**
	 * Se encarga de devolver el valor del atributo info que se corresponde la información que representa el movimiento. 
	 * 
	 * @return info La información relacionada con el movimiento. 
	 */
	public String getInfo() {
		return info;
	}
	
	/**
	 * Se encarga de modificar el valor del atributo info que se corresponde con la información del movimiento.
	 * 
	 * @param info La información a asignar al movimiento.
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Se encarga de devolver el valor del atributo monederoActual que se corresponde con el valor monetario actual del monedero del usuario al que le corresponde el movimiento determinado. 
	 * 
	 * @return monederoActual El valor monetario actual del monedero del usuario al que le corresponde el movimiento.
	 */
	public float getMonederoActual() {
		return monederoActual;
	}
	/**
	 * Se encarga de modificar el valor del atributo monederoActual que se corresponde con el el valor monetario actual del monedero del usuario al que le corresponde el movimiento determinado.
	 * 
	 * @param monederoActual El nuevo valor monetario actual del monedero del usuario a asignar al movimiento.
	 */
	public void setMonederoActual(float monederoActual) {
		this.monederoActual = monederoActual;
	}
	/**
	 * Se encarga de devolver el valor del atributo valor que se corresponde con el valor monetario realizado debido al movimiento. 
	 * 
	 * @return valor El valor monetario realizado debido al movimiento.
	 */
	public float getValor() {
		return valor;
	}
	/**
	 * Se encarga de modificar el valor del atributo valor que se corresponde con el valor monetario realizado debido al movimiento.
	 * 
	 * @param valor El nuevo valor monetario a asignar al movimiento. 
	 */
	public void setValor(float valor) {
		this.valor = valor;
	}
	/**
	 * Se encarga de devolver el valor del atributo fecha que se corresponde con la fecha en la que se ha generado el movimiento o con la fecha del Bingo.
	 * 
	 * @return fecha La fecha en la que se ha generado el movimiento o la fecha del Bing. 
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Se encarga de modificar el valor del atributo fecha que se corresponde con la fecha del movimiento.
	 * 
	 * @param fecha La nueva fecha a asignar al movimiento. 
	 */
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

    /**
     * Devuelve una representación en cadena del objeto Movimiento, mostrando sus atributos.
     *
     * @return Representación en cadena del objeto Movimiento.
     */
	@Override
	public String toString() {
		return "Movimiento [info=" + info + ", monederoActual=" + monederoActual + ", valor=" + valor + ", fecha="
				+ fecha + "]";
	}

	/**
	 * Genera un código hash único basado en los atributos del movimiento.
	 *
	 * @return Código hash del objeto Movimiento.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(fecha, info, monederoActual, valor);
	}

	/**
	 * Compara si dos objetos Movimiento son iguales basándose en sus atributos.
	 *
	 * @param obj Objeto a comparar con el Movimiento actual.
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
		Movimiento other = (Movimiento) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(info, other.info)
				&& Float.floatToIntBits(monederoActual) == Float.floatToIntBits(other.monederoActual)
				&& Float.floatToIntBits(valor) == Float.floatToIntBits(other.valor);
	}

}