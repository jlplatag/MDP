package es.unex.cum.mdp.ef3.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * La clase Casino representa el entorno que permite jugar al Bingo.
 * 
 * Incluye métodos de: interacción con usuarios como loguearse y registrarse; de
 * realización de operaciones económicas; de gestión de bingos y estadísticas; y
 * de consultas de ranking.
 * 
 * Incluye los siguientes atributos para su correcto funcionamiento: usuarios
 * (mapa de usuarios que representa los usuarios que existen en el Casino),
 * bingos (mapa de bingos que representa los bingos que hay en el Casino),
 * estadistica (vector de Estadistica que representa las estadísticas de los
 * números posibles a salir al jugar en el Bingo) y r (representa el reparto
 * final de cada Bingo jugado).
 * 
 * @author Clara Galván Bermúdez
 * @author José Luis Plata Gallardo
 */
public class Casino implements Serializable {
	protected HashMap<String, Usuario> usuarios;
	protected HashMap<Date, Bingo> bingos;
	protected Estadistica[] estadistica;
	private Reparto r;

	/**
	 * Constructor por defecto de la clase Casino que inicializa las estructuras de
	 * datos y estadísticas. Dando lugar a, un mapa de usuarios vacío, un mapa de
	 * bingos vacío y un array de estadísticas con un tamaño de 91 elementos donde
	 * cada elemento es una instancia de la clase Estadistica inicializada con un
	 * índice.
	 */
	public Casino() {
		usuarios = new HashMap<String, Usuario>();
		bingos = new HashMap<Date, Bingo>();
		estadistica = new Estadistica[90 + 1];
		for (int i = 0; i < estadistica.length; i++) {
			estadistica[i] = new Estadistica(i);
		}
	}

	/**
	 * Se encarga de devolver el valor del atributo usuarios que se corresponde con
	 * el mapa de usuarios presente en el cartón.
	 * 
	 * @return usuarios El mapa de usuarios presente en el Casino.
	 */
	public HashMap<String, Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * Se encarga de modificar el valor del atributo usuarios que se corresponde con
	 * el mapa de usuarios que hay en el Casino.
	 * 
	 * @param usuarios El nuevo mapa de usuarios a asignar al Casino.
	 */
	public void setUsuarios(HashMap<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * Se encarga de devolver el valor del atributo bingos que se corresponde con el
	 * mapa de bingos presente en el cartón.
	 * 
	 * @return bingos El mapa de bingos presente en el Casino.
	 */
	public HashMap<Date, Bingo> getBingos() {
		return bingos;
	}

	/**
	 * Se encarga de modificar el valor del atributo bingos que se corresponde con
	 * el mapa de bingos que hay en el Casino.
	 * 
	 * @param bingos El nuevo mapa de bingos a asignar al Casino.
	 */
	public void setBingos(HashMap<Date, Bingo> bingos) {
		this.bingos = bingos;
	}

	/**
	 * Se encarga de devolver el valor del atributo estadistica que se corresponde
	 * con el vector de estadísticas que hay en el Casino.
	 *
	 * 
	 * @return estadistica El vector de estadística presente en el Casino.
	 */
	public Estadistica[] getEstadistica() {
		return estadistica;
	}

	/**
	 * Se encarga de modificar el valor del atributo estadistica que se corresponde
	 * con el vector de estadísticas que hay en el casino.
	 * 
	 * @param estadistica El nuevo vector de estadísticas a asignar en el Casino.
	 */
	public void setEstadistica(Estadistica[] estadistica) {
		this.estadistica = estadistica;
	}

	/**
	 * Devuelve un valor hash basado en los atributos bingos, usuarios y estadistica
	 * de la instancia Casino. Calcula y devuelve un código hash combinando los hash
	 * codes de los HashMap bingos y usuarios, así como el hash code del array
	 * estadistica.
	 *
	 * @return El código hash basado en los atributos bingos, usuarios y
	 *         estadistica.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(estadistica);
		result = prime * result + Objects.hash(bingos, usuarios);
		return result;
	}

	/**
	 * Compara si el objeto actual es igual al objeto proporcionado. Comprueba si
	 * ambos objetos son instancias de Casino y si sus bingos, usuarios y
	 * estadistica son iguales.
	 *
	 * @param obj Objeto a comparar con la instancia actual de Casino.
	 * @return true si los objetos son iguales, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Casino)) {
			return false;
		}
		Casino other = (Casino) obj;
		return Objects.equals(bingos, other.bingos) && Arrays.equals(estadistica, other.estadistica)
				&& Objects.equals(usuarios, other.usuarios);
	}

	/**
	 * Devuelve una representación en forma de cadena de la instancia actual de
	 * Casino. Ofrece información sobre los HashMap usuarios y bingos del casino y
	 * de las estadísticas del mismo.
	 *
	 * @return Cadena representativa de la instancia actual de Casino.
	 */
	@Override
	public String toString() {
		return "Casino [usuarios=" + usuarios + ", bingos=" + bingos + ", estadistica=" + estadistica + "]";
	}

	/**
	 * Autentica al usuario en el casino usando el nombre de usuario y contraseña
	 * proporcionados. En caso de que el usuario no se haya podido autenticar de
	 * forma correcta, se lanza la excepción de UsuarioNoAutenticado.
	 * 
	 * @param nick     El nombre de usuario del usuario que intenta iniciar sesión.
	 * @param password La contraseña asociada al nombre de usuario.
	 * @return El usuario autenticado si la autenticación es exitosa.
	 * @throws UsuarioNoAutenticado Si el usuario no puede ser autenticado.
	 */
	public Usuario login(String nick, String password) throws UsuarioNoAutenticado {
		Usuario u = usuarios.get(nick);
		// Buscar en el hasmap si este usuario se encuentra
		if (u == null || !u.getPassword().equals(password)) {
			throw new UsuarioNoAutenticado("El usuario no está autenticado correctamente.");
		}
		return u;
	}

	/**
	 * Registra un nuevo usuario en el casino con el nombre de usuario, nombre real,
	 * contraseña y saldo inicial del monedero. Este usuario podrá registrarse
	 * siempre y cuando, el nick sea diferente a los ya añadidos en el mapa de
	 * usuario del Casino. Una vez registrado el usuario (si ha podido), se llama al
	 * método login para comprobar que efectivamente el usuario ya puede loguearse.
	 *
	 * @param nick     El nombre de usuario del nuevo usuario.
	 * @param name     El nombre real del nuevo usuario.
	 * @param password La contraseña del nuevo usuario.
	 * @param monedero El saldo inicial del monedero del nuevo usuario.
	 * @return true si el registro es exitoso, false si no se puede registrar.
	 */
	public boolean register(String nick, String name, String password, float monedero) {
		Usuario u = new Usuario(nick, name, password, monedero);
		if (!usuarios.containsKey(nick)) {
			usuarios.put(nick, u);
			try {
				Usuario u2 = this.login(nick, password);
			} catch (UsuarioNoAutenticado e) {
				return false;
			}
		} else {
			return false;
		}

		return true;
	}

	/**
	 * Agrega dinero al monedero de un usuario autenticado. Esta acción sólo será
	 * posible si el usuario puede ser autenticado de forma exitosa y además, se
	 * añade un movimiento indicando que se ha agregado dinero al monedero del
	 * usuario correspondiente.
	 *
	 * @param nick     El nombre de usuario del usuario.
	 * @param password La contraseña del usuario.
	 * @param monedero La cantidad de dinero a agregar al monedero.
	 * @return true si la operación fue exitosa, false si falla.
	 */
	public boolean addMonedero(String nick, String password, float monedero) {
		try {
			Usuario u = this.login(nick, password);

			u.getHistorico().add(new Movimiento("Agregacion dinero a monedero", u.getMonedero(), monedero, new Date()));

			u.setMonedero(u.getMonedero() + monedero);
		} catch (UsuarioNoAutenticado e) {
			return false;
		}
		return true;
	}

	/**
	 * Crea un nuevo bingo en el Casino con el tipo de Bingo (75 u 80 en este caso),
	 * fecha (en la que se crea el Bingo), bote inicial y precio de los cartones. El
	 * bingo se podrá crear siempre y cuando el mapa de bingos no haya creado otro
	 * bingo en la misma fecha. Para la creación del bingo se utiliza la factoría de
	 * Bingo.
	 *
	 * @param tipo   El tipo de bingo a crear.
	 * @param d      La fecha del nuevo bingo.
	 * @param bote   El bote inicial del nuevo bingo.
	 * @param precio El precio de participación en el nuevo bingo.
	 * @return El nuevo bingo creado si la fecha no existe previamente, de lo
	 *         contrario devuelve null.
	 */
	public Bingo crearBingo(String tipo, Date d, float bote, float precio) {
		Bingo b = null;
		if (!bingos.containsKey(d)) {
			FactoriaBingo f = new FactoriaBingo();
			b = f.buildBingo(tipo, precio);
			b.setFecha(d);
			b.setRecaudacion(bote);
			b.setId(bingos.size());
			bingos.put(d, b);
		}
		return b;
	}

	/**
	 * Inicia el juego del bingo en la fecha d si el bingo existe y aún no se ha
	 * jugado.
	 *
	 * @param d La fecha del bingo a jugar.
	 * @return true si se inicia el juego, false si el bingo no existe o ya ha sido
	 *         jugado.
	 */
	public boolean jugar(Date d) {
		if (bingos.containsKey(d)) {
			Bingo b = bingos.get(d);
			if (!b.isJugado()) {
				b.jugar(getEstadistica());
				return true;
			}
		}
		return false;
	}

	/**
	 * Inicia el juego del bingo en la fecha d si el bingo existe y aún no se ha
	 * jugado. En este caso, se llama al método jugar que permite que se le pase por
	 * parámetro la lista de los números que simula los números sacado de la bolsa
	 * del Bingo.
	 *
	 * @param d           La fecha del bingo a jugar.
	 * @param combinacion La lista de números que simula los números sacados de la
	 *                    bolsa Bingo y de la que se van a sacar esos mismos
	 *                    números.
	 * @return true si se inicia el juego, false si el bingo no existe o ya ha sido
	 *         jugado.
	 */
	public boolean jugar(Date d, List<Integer> combinacion) {
		if (bingos.containsKey(d)) {
			Bingo b = bingos.get(d);
			if (!b.isJugado()) {
				b.jugar(getEstadistica(), combinacion);
				return true;
			}
		}
		return false;
	}

	/**
	 * Devuelve una lista ordenada de usuarios por su ranking. Es decir, primero
	 * irán los usuarios con mayor número de veces cantado Bingo, despues, los que
	 * tienen mayor número de veces cantado Línea y por último, los que tienen mayor
	 * número de veces cantado Especiales.
	 *
	 * @return La lista ordenada de usuarios por ranking si hay usuarios
	 *         registrados, de lo contrario, null.
	 */
	public List<Usuario> verRanking() {
		if (usuarios != null) {
			List<Usuario> lusu = usuarios.values().stream().collect(Collectors.toList());
			Collections.sort(lusu);
			return lusu;
		}
		return null;
	}

	/**
	 * Adhiere un cartón a un usuario en un bingo específico (si existe dentro del
	 * mapa de bingos un bingo con la fecha pasada por parámetro) si el usuario
	 * tiene suficiente saldo en el monedero y si antes de todo, el usuario existe
	 * dentro del mapa de usuarios del Casino.
	 *
	 * @param nick     El nombre de usuario del usuario.
	 * @param password La contraseña del usuario.
	 * @param fecha    La fecha del bingo al que se adhiere el cartón.
	 * @param tipo     El tipo de cartón a adherir.
	 * @return El cartón adherido al usuario si se adhiere correctamente, de lo
	 *         contrario, null.
	 */
	public ICarton adherirseCarton(String nick, String password, Date fecha, String tipo) {
		Usuario u = null;
		Bingo b = null;
		try {
			u = login(nick, password);
		} catch (UsuarioNoAutenticado e) {
			return null;
		}
		if (bingos.containsKey(fecha)) {
			if (u.getMonedero() >= bingos.get(fecha).getPrecio()) {
				b = bingos.get(fecha);

				ICarton c = b.crearCarton(tipo, u);
				return c;
			}
		}
		return null;
	}

	public void cargarMovimientos(String ficheroMov) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try (BufferedReader reader = new BufferedReader(new FileReader(ficheroMov))) {
			String linea;

			while ((linea = reader.readLine()) != null) {
				String[] partes = linea.split("#");
				if (partes.length == 6) { // Asumiendo que hay cinco '#' en la línea
					String idUsuario = partes[1];
					String info = partes[2];
					String aux = partes[3].replace(",", ".");
					float monederoActual = Float.parseFloat(aux);
					aux = partes[4].replace(",", ".");
					float valor = Float.parseFloat(aux);
					Date fecha = dateFormat.parse(partes[5]);

					// Crear Usuario si no existe TODO
					// usuarios.putIfAbsent(idUsuario, new Usuario(idUsuario));

					// Obtener la lista de movimientos del Usuario
					List<Movimiento> historico = usuarios.get(idUsuario).getHistorico();

					historico.add(new Movimiento(info, monederoActual, valor, fecha));
				}
			}
			System.out.println("Movimientos cargados desde movimientos.txt");
		} catch (IOException | ParseException e) {
			System.err.println("Error al leer el archivo movimientos.txt");
		}
	}

	public void salvarMovimientos(String ficheroMov) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		FileWriter writer = new FileWriter(ficheroMov);
		for (Usuario usuario : usuarios.values()) {
			String idUsuario = usuario.getNick();
			for (Movimiento movimiento : usuario.getHistorico()) {
				String info = movimiento.getInfo();
				float monederoActual = movimiento.getMonederoActual();
				float valor = movimiento.getValor();
				String fecha = dateFormat.format(movimiento.getFecha());

				String linea = String.format("#%s#%s#%.2f#%.2f#%s%n", idUsuario, info, monederoActual, valor, fecha);
				writer.write(linea);
			}
		}
		writer.close();

		System.out.println("Movimientos guardados en movimientos.txt");
	}

}