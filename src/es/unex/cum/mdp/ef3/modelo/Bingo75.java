package es.unex.cum.mdp.ef3.modelo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * La clase Bingo75 representa un juego de bingo específico que sigue las reglas
 * de un bingo con 75 bolas. Implementa las funcionalidades necesarias para
 * jugar una partida de bingo de 75 bolas, comprobar las combinaciones de
 * cartones y distribuir los premios correspondientes.
 * 
 * Se encarga de administrar las estadísticas del juego y generar los
 * movimientos asociados a cada partida, incluyendo la cantidad de bolas
 * sacadas, premios otorgados y cambios en los saldos de los jugadores. Los
 * métodos dentro de esta clase permiten jugar una partida de bingo, verificar
 * los cartones asociados y realizar el reparto de premios entre los jugadores.
 * 
 * Esta clase extiende de la clase abstracta Bingo, aprovechando la estructura
 * base para un bingo y agregando las funcionalidades específicas de un bingo de
 * 75 bolas.
 * 
 * @author Clara Galván Bermúdez
 */
public class Bingo75 extends Bingo {
	/**
	 * Constructor que inicializa un bingo de 75 bolas.
	 *
	 * @param valor El precio a asignar a cada uno de los cartones para jugar en un
	 *              bingo 75.
	 */
	public Bingo75(float valor) {
		super(valor, 75);
	}

	/**
	 * Se encarga de jugar el bingo 75.
	 * 
	 * Se le pasa por parámetro un array de Estadistica que es el array de
	 * Estadistica de cada número que representa una bola sacada. Con lo cual, a
	 * medida que se va jugando, se va actualizando esta estadística para cada
	 * número. El sacar una bola se simula llamadando al método sacarBola de la
	 * clase BolsaBingo.
	 * 
	 * En la estadistica de un número se incrementa numSacado cuando se simula que
	 * se ha sacado de la bolsa ese número. Se incrementa numBingo cuando se ha
	 * cantado Bingo al jugar en este Bingo concreto y lo mismo sucede con numLinea
	 * y numEspecial.
	 * 
	 * Una vez sacada la bola con un número concreto, se debe marcar este número en
	 * todos los cartones que juegan en este Bingo y para ello, se debe ir buscando
	 * el número determinado en cada uno de ellos. A medida que se busca el número
	 * sacado, también se comprueba si alguno de los cartones permite cantar Bingo,
	 * Línea o Especial de tal forma que si se puede, se debe actualizar la
	 * información del usuario con respecto al número de Bingo, Línea o Especial.
	 * 
	 * Más de un usuario puede cantar Bingo, Especial o Línea con el mismo número
	 * sacado pero no puede cantarlo después de sacar otro número puesto que, se
	 * supone que ya no hay más posibilades de cantar alguno de los estados dichos.
	 * 
	 * El Bingo termina cuando algún cartón permita cantar Bingo.
	 * 
	 * Finalmente, se realiza el reparto general mediante la llamada al método
	 * repartoTotales y se realiza el reparto específico para cada uno de los
	 * usarios que han obtenido un premio mediante la llamada al método movUsuarios.
	 * 
	 *
	 * @param estadistica Un array de estadísticas que contiene información de los
	 *                    números que se van a sacar de la bolsa del Bingo.
	 * @return Un objeto de tipo Reparto que representa el reparto de premios
	 *         general realizado en la partida.
	 */
	@Override
	public Reparto jugar(Estadistica[] estadistica) {
		super.setJugado(true);
		int bolaActual = 0;
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
							c.getUser().setNumLineas(c.getUser().getNumLineas() + 1);

						}
					}
					if (!especialCantado) {
						if (c.comprobarEspeciales()) {
							especialCantadoAux = true;
							usEspecial.add(c.getUser());
							c.getUser().setNumEspeciales(c.getUser().getNumEspeciales() + 1);

						}
					}
					if (c.comprobarBingo()) {
						bingoCantado = true;
						usBingo.add(c.getUser());
						c.getUser().setNumBingos(c.getUser().getNumBingos() + 1);
					}
				}
			}
			if (lineaCantadaAux) {
				e.setNumLinea(e.getNumLinea() + 1);
				lineaCantada = true;
			}
			if (especialCantadoAux) {
				e.setNumEspecial(e.getNumEspecial() + 1);
				especialCantado = true;
			}
			if (bingoCantado) {
				e.setNumBingo(e.getNumBingo() + 1);
			}

		} while (!bingoCantado);
		// ---------------------- PARTE DE REPARTO ----------------
		Reparto r = new Reparto(this.repartoTotales(usLinea.size(), usBingo.size(), usEspecial.size()));
		super.setReparto(r);
		return r;
	}

	/**
	 * Se encarga de jugar el bingo 75.
	 * 
	 * Se le pasa por parámetro un array de Estadistica que es el array de
	 * Estadistica de cada número que representa una bola sacada. Con lo cual, a
	 * medida que se va jugando, se va actualizando esta estadística para cada
	 * número. Para este método el sacar una bola se simula haciendo un poll de la
	 * lista numeros que es pasada por parámetro.
	 * 
	 * En la estadistica de un número se incrementa numSacado cuando se simula que
	 * se ha sacado de la bolsa ese número. Se incrementa numBingo cuando se ha
	 * cantado Bingo al jugar en este Bingo concreto y lo mismo sucede con numLinea
	 * y numEspecial.
	 * 
	 * Una vez sacada la bola con un número concreto, se debe marcar este número en
	 * todos los cartones que juegan en este Bingo y para ello, se debe ir buscando
	 * el número determinado en cada uno de ellos. A medida que se busca el número
	 * sacado, también se comprueba si alguno de los cartones permite cantar Bingo,
	 * Línea o Especial de tal forma que si se puede, se debe actualizar la
	 * información del usuario con respecto al número de Bingo, Línea o Especial.
	 * 
	 * Más de un usuario puede cantar Bingo, Especial o Línea con el mismo número
	 * sacado pero no puede cantarlo después de sacar otro número puesto que, se
	 * supone que ya no hay más posibilades de cantar alguno de los estados dichos.
	 * 
	 * El Bingo termina cuando algún cartón permita cantar Bingo.
	 * 
	 * Finalmente, se realiza el reparto general mediante la llamada al método
	 * repartoTotales y se realiza el reparto específico para cada uno de los
	 * usarios que han obtenido un premio mediante la llamada al método movUsuarios.
	 * 
	 *
	 * @param estadistica Un array de estadísticas que contiene información de los
	 *                    números que se van a sacar de la bolsa del Bingo.
	 * @param numeros     Una lista de números que simula la bolsa Bingo de dónde se
	 *                    sacan las bolas que representan un número concreto para
	 *                    jugar al Bingo 75.
	 * @return Un objeto de tipo Reparto que representa el reparto de premios
	 *         general realizado en la partida.
	 */
	@Override
	public Reparto jugar(Estadistica[] estadistica, List numeros) {
		super.setJugado(true);
		LinkedList nums = new LinkedList<Integer>(numeros);
		LinkedList<Usuario> usEspecial = new LinkedList<Usuario>();
		LinkedList<Usuario> usBingo = new LinkedList<Usuario>();
		LinkedList<Usuario> usLinea = new LinkedList<Usuario>();
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
						}
					}
					if (!especialCantado) {
						if (c.comprobarEspeciales()) {
							especialCantadoAux = true;
							// añadimos los usuarios que han conseguido especial
							usEspecial.add(c.getUser());
							c.getUser().setNumEspeciales(c.getUser().getNumEspeciales() + 1);
						}
					}
					if (c.comprobarBingo()) {
						bingoCantado = true;
						c.getUser().setNumBingos(c.getUser().getNumBingos() + 1);
						// añadimos los usuarios que han conseguido bingo
						usBingo.add(c.getUser());
					}

				}
			}
			if (lineaCantadaAux) {
				e.setNumLinea(e.getNumLinea() + 1);
				lineaCantada = true;
			}
			if (especialCantadoAux) {
				e.setNumEspecial(e.getNumEspecial() + 1);
				especialCantado = true;
			}
			if (bingoCantado) {
				e.setNumBingo(e.getNumBingo() + 1);
			}
		} while (!bingoCantado);
		// Mientras sea !bingoCantado no porque si no, cuando se vaya a sacar la vez 75
		// una bola, esta bola es null.
		// ---------------------- PARTE DE REPARTO ----------------
		Reparto r = new Reparto(this.repartoTotales(usLinea.size(), usBingo.size(), usEspecial.size()));
		super.setReparto(r);
		// -------------------- Reparto premios + movimientos usuarios --------------
		this.movUsuarios(usLinea, usBingo, usEspecial, r.getRepartoLinea(), r.getRepartoBingo(),
				r.getRepartoEspeciales());
		return r;
	}

	/**
	 * Calcula el reparto total de premios para una partida de bingo, basado en la
	 * cantidad de líneas, bingos y especiales alcanzados. Para este reparto se debe
	 * tener en cuenta que el dinero obtenido se debe repartir entre: la caja, los
	 * usuarios que han cantado especiales, los usuarios que han cantado línea y los
	 * usuarios que han cantado bingo.
	 * 
	 * El 10% de la recaudación se lo lleva la caja. Para los cartones con estado
	 * ESPECIAL se tendrá un premio de 2€. Para los cartones con estado LINEA se
	 * repartirá entre todos el 30% del dinero restante (excluyendo el 10% de la
	 * caja y los 2€ de todos los cartones especiales). Para los cartones con estado
	 * BINGO se repartirá entre todos el 60% del dinero restante (excluyendo el 10%
	 * de la caja y los 2€ de todos los cartones especiales).
	 * 
	 * Se debe realizar un redondeo que en caso de que sobre dinero, se lo lleva la
	 * caja. Este redondeo se realiza con la llamada al método round.
	 *
	 * @param nL El número de líneas alcanzadas en la partida.
	 * @param nB El número de bingos alcanzados en la partida.
	 * @param nE El número de premios especiales alcanzados en la partida.
	 * @return Un objeto de tipo Reparto que representa el reparto total de premios
	 *         realizado en la partida.
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
	 * Realiza el reparto de premios entre los usuarios basado en las cantidades de
	 * premios y los tipos de premios obtenidos (líneas, bingos y especiales),
	 * distribuyendo equitativamente el valor entre los usuarios correspondientes.
	 * 
	 * En primer lugar, se divide de forma equitativa las cantidades de premios
	 * según el número de usuarios que tiene cada una de las listas pasadas por
	 * parámetro. Después, en lugar de usar estas listas, se usa un HashSet pues, se
	 * debe a que se tienen que agregar los movimientos de los saldos de los
	 * usuarios y dado que, si un usuario ha conseguido Línea y también Bingo por
	 * ejemplo, solo se puede registar un movimiento que conlleve estos dos, se
	 * utiliza un HashSet para saber cuando añadir un solo movimiento.
	 * 
	 * A continuación, se añaden al HashSet todos los usuarios de cada una de las
	 * listas anteriores teniendo en cuenta que los repetidos no se agregarán.
	 * Luego, se realiza un iterator que recorre este HashSet así pues, con la ayuda
	 * de variables booleanas, se va comprobando si el usuario del HashSet es
	 * contenido en la lista de Bingo, de Línea o de Especial para saber si ha
	 * cantado alguno de los estados.
	 * 
	 * Finalmente, por cada usuario se va comprobando qué es lo que ha cantado y se
	 * agrega un movimiento del saldo además de modificar su monedero. En caso de
	 * que haya cantado más de un estado (Bingo, Línea o Especial), se agrega un
	 * movimiento indicando cuáles de los estados ha cantado y se añade el dinero
	 * correspondiente al saldo.
	 *
	 * @param usLinea     Lista de usuarios que obtuvieron línea en la partida.
	 * @param usBingo     Lista de usuarios que obtuvieron bingo en la partida.
	 * @param usEspecial  Lista de usuarios que obtuvieron premios especiales en la
	 *                    partida.
	 * @param rLineas     Dinero total destinado al premio de líneas.
	 * @param rBingos     Dinero total destinado al premio de bingos.
	 * @param rEspeciales Dinero total destinado al premio de especiales.
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
		// especial. (No va a meter los repetidos) El treeSet no porque sino, usa
		// comparable.
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
		// ..................... RECORRER EL HASHSET CON ITERATOR
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
			// 5. Posibilidades de repartición de premios.
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
	 *
	 * @param d            Número decimal que se va a redondear.
	 * @param decimalPlace Cantidad de lugares decimales a los que se quiere
	 *                     redondear el número.
	 * @return El número decimal redondeado al número especificado de lugares
	 *         decimales.
	 */
	public float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
}
