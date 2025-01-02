package es.unex.cum.mdp.ef2;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import es.unex.cum.mdp.ef2.carton.ICarton;

/**
 * Clase Bingo80 donde se realiza toda la lógica de jugar: sacar números,
 * comprobar cartones, repartir premios y añadir movimientos. Extiende de la
 * clase Bingo.
 * 
 * @author José Luis Plata Gallardo
 * 
 */
public class Bingo80 extends Bingo {

	/**
	 * Constructor de la clase Bingo80. Llama al constructor de la clase Bingo para
	 * indicar el precio del bingo y el tipo de bingo.
	 *
	 * @param valor El precio del bingo.
	 */
	public Bingo80(float valor) {
		super(valor, 80);
	}

	/**
	 * Metodo donde se juega el bingo. Primero ponemos el bingo jugado a true,
	 * inicializamos variables necesarias a lo largo del método.
	 * 
	 * Desordenamos las bolas de la bolsa de bolas y empezamos a sacarlas una a una
	 * en un do while.
	 * 
	 * Dentro del do while vamos añadiendo en estadística la bola sacada y vamos
	 * comprobando todos los cartones por si se ha conseguido linea, especial o
	 * bingo. Si se han conseguido añadimos los usuarios que lo han conseguido al
	 * linkedlist correspondiente.
	 * 
	 * Tras acabar el do while cuando alguien haya cantado bingo se llama al método
	 * de reparto, al metodo para añadir movimientos y se actualiza.
	 * 
	 * Por último se devuelve el reparto de premios.
	 */
	@Override
	public Reparto jugar(Estadistica[] estadistica) {
		super.setJugado(true);
		int bolaActual = 0;
		int veces = 0;
		Estadistica e = null;
		LinkedList<Usuario> usEspecial = new LinkedList<Usuario>();
		LinkedList<Usuario> usBingo = new LinkedList<Usuario>();
		LinkedList<Usuario> usLinea = new LinkedList<Usuario>();
		boolean bingoCantado = false;
		boolean lineaCantada = false;
		boolean lineaCantadaAux = false;
		boolean especialCantado = false;
		boolean especialCantadoAux = false;
		// -------------- GESTIONAR BOLAS -----------------------------
		super.getB().desordenar();
		do {
			bolaActual = (Integer) getB().sacarBola();
			this.getBolasSacadas().add(bolaActual);
			e = estadistica[bolaActual];
			e.setNumSacado(e.getNumSacado() + 1); // se supone que siempre va a ver como mucho 1
			Iterator it = super.getCartones().iterator();
			while (it.hasNext()) {
				ICarton c = (ICarton) it.next();
				if (c.recibirNumero(bolaActual)) {
					if (!lineaCantada) {
						if (c.comprobarLinea()) {
							lineaCantadaAux = true;

							usLinea.add(c.getUser());
						}
					}
					if (!especialCantado) {
						if (c.comprobarEspeciales()) {
							especialCantadoAux = true;

							usEspecial.add(c.getUser());
						}
					}
					if (c.comprobarBingo()) {
						bingoCantado = true;

						usBingo.add(c.getUser());
					}
				}
			}
			if (lineaCantadaAux) {
				lineaCantada = true;
				e.setNumLinea(e.getNumLinea() + 1);
			}
			if (especialCantadoAux) {
				especialCantado = true;
				e.setNumEspecial(e.getNumEspecial() + 1);
			}
			if (bingoCantado) {
				e.setNumBingo(e.getNumBingo() + 1);
			}
			veces++;
		} while (!bingoCantado);
		// ---------------------- PARTE DE REPARTO ----------------
		Reparto r = new Reparto(this.repartoTotales(usLinea.size(), usBingo.size(), usEspecial.size()));
		super.setReparto(r);
		return r;
	}

	/**
	 * Metodo donde se juega el bingo. Es el usado en la batería de test para que
	 * funcionase correctamente.
	 * 
	 * Aquí nos dan la lista de bolas por lo que solo tenemos que ir sacandolas en
	 * un do while.
	 * 
	 * Dentro del do while vamos añadiendo en estadística la bola sacada y vamos
	 * comprobando todos los cartones por si se ha conseguido linea, especial o
	 * bingo. Si se han conseguido añadimos los usuarios que lo han conseguido al
	 * linkedlist correspondiente.
	 * 
	 * Tras acabar el do while cuando alguien haya cantado bingo se llama al método
	 * de reparto, al metodo para añadir movimientos y se actualiza.
	 * 
	 * Por último se devuelve el reparto de premios.
	 */
	@Override
	public Reparto jugar(Estadistica[] estadistica, List numeros) {
		super.setJugado(true);
		LinkedList nums = new LinkedList<Integer>(numeros);
		LinkedList<Usuario> usEspecial = new LinkedList<Usuario>();
		LinkedList<Usuario> usBingo = new LinkedList<Usuario>();
		LinkedList<Usuario> usLinea = new LinkedList<Usuario>();
		int contEspecial = 0;
		int contLinea = 0;
		int contBingo = 0;
		int veces = 0;
		int bolaActual = 0;
		Estadistica e = null;
		boolean bingoCantado = false;
		boolean lineaCantada = false;
		boolean lineaCantadaAux = false;
		boolean especialCantado = false;
		boolean especialCantadoAux = false;
		// -------------- GESTIONAR BOLAS -----------------------------
		do {// ------------ DO: para cada bola, se examina la lista de cartones
			// --------------------- PARTE DE JUGAR
			bolaActual = (Integer) nums.poll();
			// System.out.println("vez sacada " + veces + " bola " + bolaActual);
			e = estadistica[bolaActual];
			e.setNumSacado(e.getNumSacado() + 1); // se supone que siempre va a ver como mucho 1
			// -------------------- ITERATOR (el while examina cada uno de los cartones)
			Iterator it = super.getCartones().iterator();
			while (it.hasNext()) {
				// System.out.println("pintamos " + );
				ICarton c = (ICarton) it.next();
				if (c.recibirNumero(bolaActual)) {
					if (!lineaCantada) {
						if (c.comprobarLinea()) {
							lineaCantadaAux = true; // si con esta bola es cantada, ya esta la linea cantada
							// añadimos los usuarios que han conseguido linea
							usLinea.add(c.getUser());
							c.getUser().setNumLineas(c.getUser().getNumLineas() + 1);
							contLinea++;
						}
					}
					if (!especialCantado) {
						if (c.comprobarEspeciales()) {
							especialCantadoAux = true;
							// añadimos los usuarios que han conseguido especial
							usEspecial.add(c.getUser());
							c.getUser().setNumEspeciales(c.getUser().getNumEspeciales() + 1);
							contEspecial++;
						}
					}
					if (c.comprobarBingo()) {
						bingoCantado = true;
						// añadimos los usuarios que han conseguido bingo
						usBingo.add(c.getUser());
						c.getUser().setNumBingos(c.getUser().getNumBingos() + 1);
						contBingo++;
					}

				}
			}
			if (lineaCantadaAux) {
				lineaCantada = true;
				e.setNumLinea(e.getNumLinea() + 1);
			}
			if (especialCantadoAux) {
				especialCantado = true;
				e.setNumEspecial(e.getNumEspecial() + 1);
			}
			if (bingoCantado) {
				e.setNumBingo(e.getNumBingo() + 1);
			}
			veces++;
		} while (!bingoCantado);
		// Mientras sea !bingoCantado no porque si no, cuando se vaya a sacar la vez 75
		// una bola, esta bola es null.
		// ---------------------- PARTE DE REPARTO ----------------
		Reparto r = new Reparto(this.repartoTotales(usLinea.size(), usBingo.size(), usEspecial.size()));
		// -------------------- Reparto premios + movimientos usuarios --------------
		this.movUsuarios(usLinea, usBingo, usEspecial, r.getRepartoLinea(), r.getRepartoBingo(),
				r.getRepartoEspeciales());
		super.setReparto(r);
		return r;
	}

	/**
	 * Método para el reparto completo de los premios del bingo según lo
	 * especificado: Caja se lleva el 10% de la recaudación Los especiales se llevan
	 * 2 euros por cada especial. Las líneas se llevan un 30% de lo sobrante de la
	 * recaudación tras retirar el dinero de la caja y de los especiales. Los bingos
	 * se llevan el 70% de lo sobrante de la recaudación tras retirar el dinero de
	 * la caja y de los especiales. Se hace el redondeo a 2 decimales y si sobran
	 * decimales se les añade a la caja.
	 *
	 * @param nL El número total de líneas ganadoras.
	 * @param nB El número total de bingos ganadores.
	 * @param nE El número total de premios especiales ganadores.
	 * @return Un objeto Reparto que contiene la distribución detallada de los
	 *         premios.
	 */
	private Reparto repartoTotales(int nL, int nB, int nE) {
		float caja = (this.getRecaudacion() * 10) / 100;
		float especiales = 2 * nE;
		float lineas = ((this.getRecaudacion() - caja - especiales) * 0.3F);
		float bingos = ((this.getRecaudacion() - caja - especiales) * 0.7F);
		// Al obtener el resto del resultado de la resta entre lineas - redondeoLinea,
		// como ese resto se va a sumar a caja, se debe restar a lineas.
		float rLineas = this.round(lineas, 2);
		float rBingo = this.round(bingos, 2);
		float restaLineas = lineas - rLineas;
		float restaBingo = bingos - rBingo;

		caja = caja + restaLineas + restaBingo;
		caja = this.round(caja, 2);

		float total = caja + especiales + lineas + bingos;
		Reparto r = new Reparto(nL, nB, nE, especiales, rLineas, rBingo, caja, total);
		return r;
	}

	/**
	 * Realiza los movimientos y premios correspondientes a los usuarios ganadores
	 * en un juego de bingo. Primero dividimos los premios dependiendo de cuantos
	 * ganadores haya. Guardamos en un hashsets los jugadores dependiendo de si han
	 * ganado bingo, linea y/o especial. Recorremos el hashsets comprobando que han
	 * ganado (bingo, linea y/o especial) Dependiendo de lo ganado se añade en
	 * histórico de movimientos y se da el dinero ganado al jugador.
	 *
	 * @param usLinea     Lista de usuarios ganadores en la categoría de línea.
	 * @param usBingo     Lista de usuarios ganadores en la categoría de bingo.
	 * @param usEspecial  Lista de usuarios ganadores en la categoría de premio
	 *                    especial.
	 * @param rLineas     Monto total a repartir en premios de líneas.
	 * @param rBingos     Monto total a repartir en premios de bingos.
	 * @param rEspeciales Monto total a repartir en premios especiales.
	 */
	private void movUsuarios(LinkedList<Usuario> usLinea, LinkedList<Usuario> usBingo, LinkedList<Usuario> usEspecial,
			float rLineas, float rBingos, float rEspeciales) {
		float valor = 0;
		// 0. dividir premios.
		float divLineas = rLineas / usLinea.size();
		float divBingos = rBingos / usBingo.size();
		float divEspecial = rEspeciales / usEspecial.size();
		// 1. Crear booleanas que se inicializan a false para cada usuario y que buscan
		// si están en algunos de los linkedlist.
		boolean bLinea = false;
		boolean bBingo = false;
		boolean bEspecial = false;
		// 2. Hacer un hashset que me meta todos los usuarios tanto de linea, bingo y
		// especial. (No va a meter los repetidos)
		// Set<Usuario> setUsuarios = new TreeSet<Usuario>();
		HashSet<Usuario> setUsuarios = new HashSet<Usuario>();
		for (int i = 0; i < usLinea.size(); i++) {
			setUsuarios.add(usLinea.get(i));
		}
		for (int i = 0; i < usBingo.size(); i++) {
			setUsuarios.add(usBingo.get(i));
		}
		for (int i = 0; i < usEspecial.size(); i++) {
			setUsuarios.add(usEspecial.get(i));
		}
		// ..................... RECORRER EL TREESET CON ITERATOR
		// .......................
		// 3. Si solo está el usuario en un LL, pues solo ese movimiento. Si está en
		// más, se suman esos movimientos.
		Iterator it = setUsuarios.iterator();
		while (it.hasNext()) {
			bLinea = false;
			bBingo = false;
			bEspecial = false;
			Usuario u = (Usuario) it.next();
			// 4. Comprobaciones de en qué LL se encuentra el usuario
			if (usLinea.contains(u)) {
				bLinea = true;
			}
			if (usBingo.contains(u)) {
				bBingo = true;
			}
			if (usEspecial.contains(u)) {
				bEspecial = true;
			}
			// 5. Posibilidades de repartición de premios. AÑADIR PREMIOS
			if (bLinea && bBingo && bEspecial) {
				valor = divLineas + divBingos + divEspecial;
				u.getHistorico().add(
						new Movimiento("Premio de Linea,Bingo y Especial", u.getMonedero(), valor, this.getFecha()));
				u.setMonedero(u.getMonedero() + valor);
			} else if (bLinea && bBingo) {
				valor = divLineas + divBingos;
				u.getHistorico()
						.add(new Movimiento("Premio de Linea y Bingo", u.getMonedero(), valor, this.getFecha()));
				u.setMonedero(u.getMonedero() + valor);
			} else if (bLinea && bEspecial) {
				valor = divLineas + divEspecial;
				u.getHistorico()
						.add(new Movimiento("Premio de Linea y Especial ", u.getMonedero(), valor, this.getFecha()));
				u.setMonedero(u.getMonedero() + valor);
			} else if (bBingo && bEspecial) {
				valor = divEspecial + divBingos;
				u.getHistorico()
						.add(new Movimiento("Premio de Especial y Bingo", u.getMonedero(), valor, this.getFecha()));
				u.setMonedero(u.getMonedero() + valor);
			} else if (bLinea) {
				u.getHistorico().add(new Movimiento("Premio de Linea", u.getMonedero(), divLineas, this.getFecha()));
				u.setMonedero(u.getMonedero() + divLineas);
			} else if (bBingo) {
				System.out.println("premio bingo  " + u.getHistorico().size());
				u.getHistorico().add(new Movimiento("Premio de Bingo", u.getMonedero(), divBingos, this.getFecha()));
				u.setMonedero(u.getMonedero() + divBingos);
			} else if (bEspecial) {
				u.getHistorico()
						.add(new Movimiento("Premio de Especial", u.getMonedero(), divEspecial, this.getFecha()));
				u.setMonedero(u.getMonedero() + divEspecial);
			}
		}

	}

	/**
	 * Redondea un número decimal al número especificado de lugares decimales.
	 * Utiliza el método de redondeo HALF_UP de BigDecimal para realizar el
	 * redondeo.
	 *
	 * @param d            El número decimal que se va a redondear.
	 * @param decimalPlace El número de decimales al que se redondeará el número.
	 * @return El número decimal redondeado al número especificado de decimales.
	 */
	public float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
}