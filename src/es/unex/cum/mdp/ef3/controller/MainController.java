package es.unex.cum.mdp.ef3.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import es.unex.cum.mdp.ef3.modelo.Bingo;
import es.unex.cum.mdp.ef3.modelo.Casino;
import es.unex.cum.mdp.ef3.modelo.CeldaCarton;
import es.unex.cum.mdp.ef3.modelo.Estadistica;
import es.unex.cum.mdp.ef3.modelo.EstadoCarton;
import es.unex.cum.mdp.ef3.modelo.ICarton;
import es.unex.cum.mdp.ef3.modelo.Movimiento;
import es.unex.cum.mdp.ef3.modelo.Reparto;
import es.unex.cum.mdp.ef3.modelo.Usuario;
import es.unex.cum.mdp.ef3.modelo.UsuarioNoAutenticado;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//../vista/xxx.fxml
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;

public class MainController implements Initializable {
	private Casino d;
	Random r = new Random();
	String nickActual = "";
	String passwdActual = "";
	boolean cambio = false;

/////////////////////////////////////// JUGAR//////////////////////////////////////////////
	Estadistica[] estadistica;
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
	private int cartonActualIndex;
	private int bingoActualIndex;
	int cont1 = 0;
	int cont2 = 0;
	int cont3 = 0;
	Date bingoJugadoActual;
	/////////////////////////////////////// JUGAR//////////////////////////////////////////////

	@FXML
	private MenuItem JugarB;

	@FXML
	private MenuItem Login;

	@FXML
	private BorderPane PanelCentral;

	@FXML
	private MenuItem RegistroUsuario;

	@FXML
	private BorderPane bpGeneral;

	@FXML
	private Menu menuAdmin;

	@FXML
	private Menu menuUsuario;

	public String getNickActual() {
		return nickActual;
	}

	public boolean isCambio() {
		return cambio;
	}

	public void setCambio(boolean cambio) {
		this.cambio = cambio;
	}

	public void setNickActual(String nickActual) {
		this.nickActual = nickActual;
	}

	public String getPasswdActual() {
		return passwdActual;
	}

	public void setPasswdActual(String passwdActual) {
		this.passwdActual = passwdActual;
	}

	@FXML
	void Salir(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void VerInfoBingo(ActionEvent event) throws IOException {

		// Se establece el fichero fxml que se va a cargar
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vista/DialogoVerBingo.fxml"));
		Parent parent = fxmlLoader.load();
		// Se crear el controlador de dialogo y se pasa el controlador principal
		DialogoVerBingoController dialogController = fxmlLoader.getController();
		dialogController.setMc(this);
		// Se establece la escena
		Scene scene = new Scene(parent, 300, 200);
		// Se establece el stage
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
		Date f = (Date) dialogController.getFechaD();
		bingoJugadoActual = f;
		PanelCentral.setRight(null);
		PanelCentral.setCenter(null);
		PanelCentral.setBottom(null);
		bpGeneral.setRight(null);
		bpGeneral.setLeft(null);
		bpGeneral.setBottom(null);
		PanelCentral.setTop(null);
		bpGeneral.setCenter(PanelCentral);
		if (f == null) {
			Alert alert3 = new Alert(AlertType.INFORMATION);
			alert3.setContentText("Se ha producido un problema con los datos introducidos.");
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.showAndWait();
		} else {
			Bingo b = d.getBingos().get(f);
			if (b == null) {
				Alert alert3 = new Alert(AlertType.INFORMATION);
				alert3.setContentText("No existe bingo el dia seleccionado.");
				DialogPane dialogPane = alert3.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
				alert3.showAndWait();
				return;
			}
			ListView<String> lv = new ListView<String>();
			Label l = new Label("Bingo " + b.getId());
			l.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
			VBox vb = new VBox();
			vb.getChildren().add(l);
			vb.getChildren().add(lv);
			vb.setSpacing(10);
			vb.setPadding(new Insets(10));
			vb.setAlignment(Pos.CENTER);
			bpGeneral.setLeft(vb);
			lv.getItems().add("Fecha: " + b.getFecha());
			lv.getItems().add("Precio: " + b.getPrecio());
			if (b.isJugado()) {
				usLinea.clear();
				usEspecial.clear();
				usBingo.clear();
				HashSet c = b.getCartones();
				Iterator it = c.iterator();

				while (it.hasNext()) {
					ICarton carton = (ICarton) it.next();
					if (carton.getEstado() == EstadoCarton.LINEA || carton.getEstado() == EstadoCarton.ESPECIAL_LINEA
							|| carton.getEstado() == EstadoCarton.ESPECIAL_LINEA_BINGO
							|| carton.getEstado() == EstadoCarton.LINEA_BINGO) {
						usLinea.add(carton.getUser());
					}
					if (carton.getEstado() == EstadoCarton.ESPECIAL || carton.getEstado() == EstadoCarton.ESPECIAL_LINEA
							|| carton.getEstado() == EstadoCarton.ESPECIAL_LINEA_BINGO
							|| carton.getEstado() == EstadoCarton.ESPECIAL_BINGO) {
						usEspecial.add(carton.getUser());
					}
					if (carton.getEstado() == EstadoCarton.BINGO || carton.getEstado() == EstadoCarton.ESPECIAL_BINGO
							|| carton.getEstado() == EstadoCarton.ESPECIAL_LINEA_BINGO
							|| carton.getEstado() == EstadoCarton.LINEA_BINGO) {
						usBingo.add(carton.getUser());
					}
				}
				lv.getItems().add("Recaudacion total: " + b.getRecaudacion());
				Reparto r = b.getReparto();
				lv.getItems().add("Recaudado en caja: " + r.getCaja());
				lv.getItems().add(
						"Reparto a especiales: " + r.getRepartoEspeciales() + ", ganadores: " + r.getNumEspeciales());
				if (r.getNumEspeciales() == 1) {
					lv.getItems().add("Ganador especial: " + usEspecial.getFirst().getNick());
				} else {
					String aux = "";
					for (int i = 0; i < usEspecial.size(); i++) {
						aux = aux + usEspecial.get(i).getNick() + ", ";
					}
					aux = aux.replaceFirst(",\\s*$", "");
					lv.getItems().add("Ganadores especial: " + aux);
				}

				lv.getItems().add("Reparto a lineas: " + r.getRepartoLinea() + ", ganadores: " + r.getNumLineas());
				if (r.getNumLineas() == 1) {
					lv.getItems().add("Ganador linea: " + usLinea.getFirst().getNick());
				} else {
					String aux = "";
					for (int i = 0; i < usLinea.size(); i++) {
						aux = aux + usLinea.get(i).getNick() + ", ";
					}
					aux = aux.replaceFirst(",\\s*$", "");
					lv.getItems().add("Ganadores linea: " + usLinea.getFirst().getNick());
				}
				lv.getItems().add("Reparto a bingos: " + r.getRepartoBingo() + ", ganadores: " + r.getNumBingo());
				if (r.getNumBingo() == 1) {
					lv.getItems().add("Ganador bingo: " + usBingo.getFirst().getNick());
				} else {
					String aux = "";
					for (int i = 0; i < usBingo.size(); i++) {
						aux = aux + usBingo.get(i).getNick() + ", ";
					}
					aux = aux.replaceFirst(",\\s*$", "");
					lv.getItems().add("Ganadores bingo: " + aux);
				}
				/////////////////////////
				Button bVerBolas = new Button("Ver Bolas");
				bVerBolas.setOnAction(e -> VerBolas(b));
				Button bVerCartones = new Button("Ver Cartones");
				bVerCartones.setOnAction(e -> VerCartones(b));

				HBox botonesBox = new HBox(20);
				botonesBox.getChildren().addAll(bVerBolas, bVerCartones);
				botonesBox.setAlignment(Pos.CENTER);

				BorderPane borderPane = new BorderPane();
				borderPane.setBottom(botonesBox);
				BorderPane.setAlignment(botonesBox, Pos.BOTTOM_CENTER);
				PanelCentral.setBottom(botonesBox);

				///////////////////////////
			} else {
				lv.getItems().add("Recaudacion actual: " + b.getRecaudacion());
				lv.getItems().add("BINGO NO JUGADO");
			}

		}
	}

	public void VerBolas(Bingo b) {
		FlowPane fpCentral = new FlowPane();
		fpCentral.setAlignment(Pos.CENTER);
		bpGeneral.setCenter(PanelCentral);
		PanelCentral.setCenter(fpCentral);
		GridPane g = new GridPane();
		g.setGridLinesVisible(true);
		int cont = 1; // Los numeros a mostrar
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 10; j++) {
				Image image;
				Label b11 = new Label();
				if (cont < 10) {
					b11.setText(" " + String.valueOf(cont) + " ");
				} else {
					b11.setText(String.valueOf(cont));
				}

				if (!b.getBolasSacadas().contains(cont)) {
					b11.setStyle("-fx-background-color: white;");
					image = new Image(this.getClass().getResourceAsStream("../resource/0.png"), 40, 40, false, false);
				} else {
					b11.setStyle("-fx-font-weight: bold; -fx-background-color: white; -fx-text-fill: red;");
					image = new Image(this.getClass().getResourceAsStream("../resource/1.png"), 40, 40, false, false);
				}

				b11.setGraphic(new ImageView(image));
				cont++;
				g.add(b11, j, i);
			}
		}
		fpCentral.getChildren().add(g);
		Button bVerCartones = new Button("Ver Cartones");
		bVerCartones.setOnAction(e -> VerCartones(b));
		bpGeneral.setBottom(null);
		PanelCentral.setBottom(null);
		bpGeneral.setBottom(bVerCartones);
	}

	public void VerCartones(Bingo b) {

		List<ICarton> cartones = new ArrayList<>();
		for (int i = 0; i < usBingo.size(); i++) {
			cartones.addAll(usBingo.get(i).getCartones());
		}
		List<ICarton> cartones2 = new ArrayList<>();
		for (int i = 0; i < b.getCartones().size(); i++) {
			cartones2.addAll(b.getCartones());
		}
		List<ICarton> cartones3 = new ArrayList<>(cartones);
		cartones3.retainAll(cartones2);
		List<ICarton> cartonesGanadores = new ArrayList<>();

		for (int i = 0; i < cartones3.size(); i++) {
			ICarton carton = cartones3.get(i);
			if (carton.getEstado() == EstadoCarton.ESPECIAL_LINEA_BINGO || carton.getEstado() == EstadoCarton.BINGO
					|| carton.getEstado() == EstadoCarton.ESPECIAL_BINGO
					|| carton.getEstado() == EstadoCarton.LINEA_BINGO) {
				cartonesGanadores.add(carton);
			}
		}
		cartonActualIndex = 0;
		GridPane nuevoGridPane = createCartonGrid(cartonesGanadores.get(cartonActualIndex), b);
		HBox botonesBox = new HBox(20);
		if (cartonesGanadores.size() != 1) {
			Button siguienteCartonButton = new Button("Siguiente Carton");
			siguienteCartonButton.setOnAction(e -> mostrarSiguienteCarton(cartonesGanadores, b, false));
			Button anteriorCartonButton = new Button("Anterior Carton");
			anteriorCartonButton.setOnAction(e -> mostrarAnteriorCarton(cartonesGanadores, b, false));
			botonesBox.getChildren().addAll(anteriorCartonButton, siguienteCartonButton);
			botonesBox.setAlignment(Pos.CENTER); // Centrar los botones en el HBox
		}

		nuevoGridPane.setAlignment(Pos.CENTER);

		PanelCentral.setCenter(nuevoGridPane);

		BorderPane borderPane = new BorderPane();
		borderPane.setBottom(botonesBox);
		BorderPane.setAlignment(botonesBox, Pos.BOTTOM_CENTER);
		cartonActualIndex = 0;
		PanelCentral.setCenter(null);

		int aux1 = cartonActualIndex + 1;
		Label la = new Label("Carton ganador " + aux1 + " de " + cartonesGanadores.size());
		la.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
		la.setAlignment(Pos.TOP_CENTER);
		bpGeneral.setCenter(PanelCentral);
		VBox vb = new VBox();
		vb.getChildren().add(la);
		vb.getChildren().add(nuevoGridPane);
		vb.getChildren().add(borderPane);
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		vb.setAlignment(Pos.CENTER);
		PanelCentral.setCenter(vb);

		Button bVerBolas = new Button("Ver Bolas");
		bVerBolas.setOnAction(e -> VerBolas(b));
		PanelCentral.setBottom(null);
		bpGeneral.setBottom(bVerBolas);
	}

	@FXML
	void MostrarCartones(ActionEvent event) throws IOException {
		// Se establece el fichero fxml que se va a cargar
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vista/DialogoMostrarCartones.fxml"));
		Parent parent = fxmlLoader.load();
		// Se crear el controlador de dialogo y se pasa el controlador principal
		DialogoMostrarCartonesController dialogController = fxmlLoader.getController();
		dialogController.setMc(this);
		// Se establece la escena
		Scene scene = new Scene(parent, 300, 200);
		// Se establece el stage
		Stage stage = new Stage();
		stage.setTitle("Cartones");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
		Date f = (Date) dialogController.getFechaD();

		PanelCentral.setRight(null);
		PanelCentral.setCenter(null);
		PanelCentral.setBottom(null);
		bpGeneral.setRight(null);
		bpGeneral.setLeft(null);
		bpGeneral.setBottom(null);
		PanelCentral.setTop(null);
		bpGeneral.setCenter(PanelCentral);
		if (f == null) {
			Alert alert3 = new Alert(AlertType.INFORMATION);
			alert3.setContentText("No has seleccionado ninguna fecha.");
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.showAndWait();
		} else {
			bingoJugadoActual = f;
			Bingo b = d.getBingos().get(f);
			if (b == null) {
				Alert alert3 = new Alert(AlertType.INFORMATION);
				alert3.setContentText("No existe el bingo solicitado.");
				DialogPane dialogPane = alert3.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
				alert3.showAndWait();
			} else {
				List<ICarton> cartones = b.getCartones().stream().filter(c -> c.getUser().getNick().equals(nickActual))
						.collect(Collectors.toList());
				if (cartones.size() != 0) {
					// Inicializar el carton actual
					cartonActualIndex = 0;
					ICarton cartonActual = cartones.get(cartonActualIndex);

					GridPane gridPane = createCartonGrid(cartonActual, b);
					if (b.isJugado()) {
						crearResultado(cartonActual, b);
					}

					// Boton para cambiar al siguiente carton
					Button siguienteCartonButton = new Button("Siguiente Carton");
					siguienteCartonButton.setOnAction(e -> mostrarSiguienteCarton(cartones, b, true));
					Button anteriorCartonButton = new Button("Anterior Carton");
					anteriorCartonButton.setOnAction(e -> mostrarAnteriorCarton(cartones, b, true));

					gridPane.setAlignment(Pos.CENTER);
					PanelCentral.setCenter(gridPane);
					HBox botonesBox = new HBox(10);
					botonesBox.getChildren().addAll(anteriorCartonButton, siguienteCartonButton);
					botonesBox.setAlignment(Pos.CENTER);

					bpGeneral.setBottom(botonesBox);
					int auxx = cartonActualIndex + 1;
					Label la = new Label("Carton " + auxx + " de " + cartones.size());
					la.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
					la.setAlignment(Pos.TOP_CENTER);

					PanelCentral.setTop(null);
					PanelCentral.setTop(la);

				} else {
					Alert alert3 = new Alert(AlertType.INFORMATION);
					alert3.setContentText("No ha jugado cartones a este bingo.");
					DialogPane dialogPane = alert3.getDialogPane();
					dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
					alert3.showAndWait();
				}

			}

		}
	}

	private void mostrarSiguienteCarton(List<ICarton> cartones, Bingo b, boolean aux) {

		cartonActualIndex = (cartonActualIndex + 1) % cartones.size();
		ICarton cartonActual = cartones.get(cartonActualIndex);

		GridPane nuevoGridPane = createCartonGrid(cartonActual, b);
		if (b.isJugado() && aux) {
			crearResultado(cartonActual, b);
		} else if (b.isJugado() && !aux) {
			Button siguienteCartonButton = new Button("Siguiente Carton");
			siguienteCartonButton.setOnAction(e -> mostrarSiguienteCarton(cartones, b, false));
			Button anteriorCartonButton = new Button("Anterior Carton");
			anteriorCartonButton.setOnAction(e -> mostrarAnteriorCarton(cartones, b, false));

			nuevoGridPane.setAlignment(Pos.CENTER);

			PanelCentral.setCenter(nuevoGridPane);
			HBox botonesBox = new HBox(10);
			botonesBox.getChildren().addAll(anteriorCartonButton, siguienteCartonButton);
			botonesBox.setAlignment(Pos.CENTER);

			BorderPane borderPane = new BorderPane();
			borderPane.setBottom(botonesBox);
			BorderPane.setAlignment(botonesBox, Pos.BOTTOM_CENTER);

			PanelCentral.setCenter(null);
			int aux1 = cartonActualIndex + 1;
			Label la = new Label("Carton ganador " + aux1 + " de " + cartones.size());
			la.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
			la.setAlignment(Pos.TOP_LEFT);
			bpGeneral.setCenter(PanelCentral);
			VBox vb = new VBox();
			vb.getChildren().add(la);
			vb.getChildren().add(nuevoGridPane);
			vb.getChildren().add(borderPane);
			vb.setSpacing(10);
			vb.setPadding(new Insets(10));
			vb.setAlignment(Pos.CENTER);
			PanelCentral.setCenter(vb);
			return;
		}

		PanelCentral.setCenter(null);
		PanelCentral.setTop(null);
		int auxx = cartonActualIndex + 1;
		Label la = new Label("Carton " + auxx + " de " + cartones.size());
		la.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
		la.setAlignment(Pos.TOP_CENTER);
		PanelCentral.setTop(la);

		PanelCentral.setCenter(nuevoGridPane);

	}

	private void crearResultado(ICarton carton, Bingo b) {
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);

		Label label = new Label("Cantado Linea");
		label.setStyle("-fx-font-weight: bold;");
		gridPane.add(label, 0, 0);
		Label label2 = new Label("Cantado Especial");
		label2.setStyle("-fx-font-weight: bold;");
		gridPane.add(label2, 0, 1);
		Label label3 = new Label("Cantado Bingo");
		label3.setStyle("-fx-font-weight: bold;");
		gridPane.add(label3, 0, 2);
		Image image;
		if (carton.getEstado() == EstadoCarton.LINEA || carton.getEstado() == EstadoCarton.ESPECIAL_LINEA
				|| carton.getEstado() == EstadoCarton.ESPECIAL_LINEA_BINGO
				|| carton.getEstado() == EstadoCarton.LINEA_BINGO) {
			image = new Image(this.getClass().getResourceAsStream("../resource/si.png"), 40, 40, false, false);
		} else {
			image = new Image(this.getClass().getResourceAsStream("../resource/no.png"), 40, 40, false, false);
		}
		ImageView imageView = new ImageView(image);
		gridPane.add(imageView, 1, 0);

		if (carton.getEstado() == EstadoCarton.ESPECIAL || carton.getEstado() == EstadoCarton.ESPECIAL_LINEA
				|| carton.getEstado() == EstadoCarton.ESPECIAL_LINEA_BINGO
				|| carton.getEstado() == EstadoCarton.ESPECIAL_BINGO) {
			image = new Image(this.getClass().getResourceAsStream("../resource/si.png"), 40, 40, false, false);
		} else {
			image = new Image(this.getClass().getResourceAsStream("../resource/no.png"), 40, 40, false, false);
		}
		ImageView imageView2 = new ImageView(image);
		gridPane.add(imageView2, 1, 1);

		if (carton.getEstado() == EstadoCarton.BINGO || carton.getEstado() == EstadoCarton.ESPECIAL_BINGO
				|| carton.getEstado() == EstadoCarton.ESPECIAL_LINEA_BINGO
				|| carton.getEstado() == EstadoCarton.LINEA_BINGO) {
			image = new Image(this.getClass().getResourceAsStream("../resource/si.png"), 40, 40, false, false);
		} else {
			image = new Image(this.getClass().getResourceAsStream("../resource/no.png"), 40, 40, false, false);
		}
		ImageView imageView3 = new ImageView(image);
		gridPane.add(imageView3, 1, 2);

		PanelCentral.setRight(null);
		HBox vb = new HBox();
		vb.getChildren().add(gridPane);
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		bpGeneral.setRight(null);
		bpGeneral.setRight(vb);

	}

	private void mostrarAnteriorCarton(List<ICarton> cartones, Bingo b, boolean aux) {

		cartonActualIndex = (cartonActualIndex - 1 + cartones.size()) % cartones.size();
		ICarton cartonActual = cartones.get(cartonActualIndex);

		GridPane nuevoGridPane = createCartonGrid(cartonActual, b);
		if (b.isJugado() && aux) {
			crearResultado(cartonActual, b);
		} else if (b.isJugado() && !aux) {
			Button siguienteCartonButton = new Button("Siguiente Carton");
			siguienteCartonButton.setOnAction(e -> mostrarSiguienteCarton(cartones, b, false));
			Button anteriorCartonButton = new Button("Anterior Carton");
			anteriorCartonButton.setOnAction(e -> mostrarAnteriorCarton(cartones, b, false));

			nuevoGridPane.setAlignment(Pos.CENTER);

			PanelCentral.setCenter(nuevoGridPane);
			HBox botonesBox = new HBox(10);
			botonesBox.getChildren().addAll(anteriorCartonButton, siguienteCartonButton);
			botonesBox.setAlignment(Pos.CENTER);

			BorderPane borderPane = new BorderPane();
			borderPane.setBottom(botonesBox);
			BorderPane.setAlignment(botonesBox, Pos.BOTTOM_CENTER);

			PanelCentral.setCenter(null);
			int aux1 = cartonActualIndex + 1;
			Label la = new Label("Carton/es ganador/es " + aux1 + " de " + cartones.size());
			la.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
			bpGeneral.setCenter(PanelCentral);
			VBox vb = new VBox();
			vb.getChildren().add(la);
			vb.getChildren().add(nuevoGridPane);
			vb.getChildren().add(borderPane);
			vb.setSpacing(10);
			vb.setPadding(new Insets(10));
			vb.setAlignment(Pos.CENTER);
			PanelCentral.setCenter(vb);
			return;
		}
		PanelCentral.setCenter(null);
		int auxx = cartonActualIndex + 1;
		Label la = new Label("Carton " + auxx + " de " + cartones.size());
		la.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
		la.setAlignment(Pos.TOP_CENTER);
		PanelCentral.setTop(null);
		PanelCentral.setTop(la);

		PanelCentral.setCenter(nuevoGridPane);

	}

	private GridPane createCartonGrid(ICarton carton, Bingo b) {
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);

		CeldaCarton[][] c = (CeldaCarton[][]) carton.getNumeros();

		gridPane.setHgap(0);
		gridPane.setVgap(0);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {

				String a = String.valueOf(c[i][j].getNumero());
				if (a.equals("0"))
					a = "-";
				Label label = new Label(a);

				label.setPrefSize(100, 100);
				String style = "-fx-alignment: center; -fx-border-color: black;";
				if (c[i][j].getEstado() == 2) {
					style = style + " -fx-background-color: lightgreen;";
				} else if (c[i][j].getNumero() != 0 && b.isJugado()) {
					style = style + " -fx-background-color: lightcoral;";
				} else {
					style = style + " -fx-background-color: white;";
				}
				if (c[i][j].isEspecial()) {
					style = style + " -fx-font-weight: bold;";
				}

				label.setStyle(style);

				gridPane.add(label, j, i);
			}
		}

		for (int i = 0; i < 4; i++) {
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(50.0 / 4);
			gridPane.getRowConstraints().add(row);
		}

		for (int j = 0; j < 8; j++) {
			ColumnConstraints col = new ColumnConstraints();
			col.setPercentWidth(50.0 / 8);
			gridPane.getColumnConstraints().add(col);
		}

		return gridPane;
	}

	@FXML
	void EditarPerfil(ActionEvent event) throws IOException {
		PanelCentral.setRight(null);
		PanelCentral.setCenter(null);
		PanelCentral.setBottom(null);
		bpGeneral.setRight(null);
		bpGeneral.setLeft(null);
		bpGeneral.setBottom(null);
		PanelCentral.setTop(null);
		bpGeneral.setCenter(PanelCentral);
		// Se establece el fichero fxml que se va a cargar
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vista/DialogoModificarPerfil.fxml"));
		Parent parent = fxmlLoader.load();
		// Se crear el controlador de dialogo y se pasa el controlador principal

		DialogoEditarPerfilController dialogController = fxmlLoader.getController();
		dialogController.setMc(this);
		dialogController.datos();
		// Se establece la escena
		Scene scene = new Scene(parent, 400, 200);
		Stage stage = new Stage();
		stage.setTitle("Editar perfil");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
		if (isCambio()) {
			passwdActual = dialogController.getPasswdN();
			cambio = false;
			VerPerfil(event);
		}
	}

	@FXML
	void VerPerfil(ActionEvent event) throws IOException {
		PanelCentral.setRight(null);
		PanelCentral.setCenter(null);
		PanelCentral.setBottom(null);
		bpGeneral.setRight(null);
		bpGeneral.setLeft(null);
		bpGeneral.setBottom(null);
		PanelCentral.setTop(null);
		bpGeneral.setCenter(PanelCentral);
		// Se establece el fichero fxml que se va a cargar
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vista/DialogoVerPerfil.fxml"));
		Parent parent = fxmlLoader.load();
		// Se crear el controlador de dialogo y se pasa el controlador principal

		DialogoVerPerfilController dialogController = fxmlLoader.getController();
		dialogController.setMc(this);
		dialogController.datos();
		// Se establece la escena
		Scene scene = new Scene(parent, 350, 200);
		// Se establece el stage
		Stage stage = new Stage();
		stage.setTitle("Perfil");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
		if (isCambio()) {
			cambio = false;
			EditarPerfil(event);
		}
	}

	@FXML
	void InfoMovimientos(ActionEvent event) throws UsuarioNoAutenticado {
		Usuario u = d.login(nickActual, passwdActual);
		List<Movimiento> m = u.getHistorico();
		// Limpiar el contenido actual del PanelCentral
		PanelCentral.setRight(null);
		PanelCentral.setCenter(null);
		PanelCentral.setBottom(null);
		bpGeneral.setRight(null);
		bpGeneral.setLeft(null);
		bpGeneral.setBottom(null);
		PanelCentral.setTop(null);
		bpGeneral.setCenter(PanelCentral);
		// Crear un FlowPane para contener el TableView
		FlowPane fpCentral = new FlowPane();
		fpCentral.setAlignment(Pos.CENTER);
		PanelCentral.setCenter(fpCentral);

		TableView<Movimiento> tableView = new TableView<>();
		tableView.setItems(FXCollections.observableList(m));

		TableColumn<Movimiento, String> infoColumna = new TableColumn<>("Informacion");
		infoColumna.setCellValueFactory(new PropertyValueFactory<>("info"));

		TableColumn<Movimiento, Float> monColumna = new TableColumn<>("Monedero antes de la operacion");
		monColumna.setCellValueFactory(new PropertyValueFactory<>("monederoActual"));
		monColumna.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

		TableColumn<Movimiento, Float> valorColumna = new TableColumn<>("Dinero retirado/Anadido");
		valorColumna.setCellValueFactory(new PropertyValueFactory<>("valor"));
		valorColumna.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

		TableColumn<Movimiento, Date> fechaColumna = new TableColumn<>("Fecha");
		fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fecha"));

		tableView.getColumns().addAll(infoColumna, monColumna, valorColumna, fechaColumna);

		tableView.setPrefWidth(720);
		fpCentral.getChildren().add(tableView);
	}

	@FXML
	void VerBingosJugados(ActionEvent event) {
		PanelCentral.setRight(null);
		PanelCentral.setCenter(null);
		PanelCentral.setBottom(null);
		bpGeneral.setRight(null);
		bpGeneral.setLeft(null);
		bpGeneral.setBottom(null);
		PanelCentral.setTop(null);
		bpGeneral.setCenter(PanelCentral);
		List<Bingo> l2 = d.getBingos().entrySet().stream().filter(map -> map.getValue().isJugado() == true)
				.map(e -> e.getValue()).collect(Collectors.toList());
		if (l2.size() != 0) {
			List<Bingo> l = new ArrayList<>();
			for (int i = 0; i < l2.size(); i++) {
				Bingo b = l2.get(i);
				HashSet<ICarton> c2 = b.getCartones();
				Iterator<ICarton> iterator = c2.iterator();
				int conta = 0;
				while (iterator.hasNext() && conta == 0) {
					ICarton carton = iterator.next();
					if (carton.getUser().getNick().equals(nickActual)) {
						l.add(b);
						conta++;
					}
				}
			}
			bingoActualIndex = 0;
			if (l.size() > 0) {
				PonerBingo(l.get(bingoActualIndex), l);
			} else {
				Alert alert3 = new Alert(AlertType.INFORMATION);
				alert3.setContentText("No has jugado ningun bingo.");
				DialogPane dialogPane = alert3.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
				alert3.showAndWait();
			}
		} else {
			Alert alert3 = new Alert(AlertType.INFORMATION);
			alert3.setContentText("No se ha jugado ningun bingo.");
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.showAndWait();
		}

	}

	void mostrarBingoSiguiente(List<Bingo> lbin) {
		bingoActualIndex = (bingoActualIndex + 1) % lbin.size();

		bpGeneral.setBottom(null);
		PanelCentral.setCenter(null);

		PonerBingo(lbin.get(bingoActualIndex), lbin);
	}

	void mostrarBingoAnterior(List<Bingo> lbin) {
		bingoActualIndex = (bingoActualIndex - 1) % lbin.size();
		bpGeneral.setBottom(null);
		PanelCentral.setCenter(null);

		PonerBingo(lbin.get(bingoActualIndex), lbin);
	}

	public void PonerBingo(Bingo b, List<Bingo> lbin) {

		ListView<String> lv = new ListView<String>();
		int auxx = bingoActualIndex + 1;
		Label l = new Label("Bingo " + auxx + " de " + lbin.size());
		l.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
		VBox vb = new VBox();
		vb.getChildren().add(l);
		vb.getChildren().add(lv);
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		vb.setAlignment(Pos.CENTER);
		PanelCentral.setCenter(vb);
		lv.getItems().add("ID: " + b.getId());
		lv.getItems().add("Fecha: " + b.getFecha());
		lv.getItems().add("Precio: " + b.getPrecio());

		usLinea.clear();
		usEspecial.clear();
		usBingo.clear();
		HashSet c = b.getCartones();
		Iterator it = c.iterator();

		while (it.hasNext()) {
			ICarton carton = (ICarton) it.next();
			if (carton.getEstado() == EstadoCarton.LINEA || carton.getEstado() == EstadoCarton.ESPECIAL_LINEA
					|| carton.getEstado() == EstadoCarton.ESPECIAL_LINEA_BINGO
					|| carton.getEstado() == EstadoCarton.LINEA_BINGO) {
				usLinea.add(carton.getUser());
			}
			if (carton.getEstado() == EstadoCarton.ESPECIAL || carton.getEstado() == EstadoCarton.ESPECIAL_LINEA
					|| carton.getEstado() == EstadoCarton.ESPECIAL_LINEA_BINGO
					|| carton.getEstado() == EstadoCarton.ESPECIAL_BINGO) {
				usEspecial.add(carton.getUser());
			}
			if (carton.getEstado() == EstadoCarton.BINGO || carton.getEstado() == EstadoCarton.ESPECIAL_BINGO
					|| carton.getEstado() == EstadoCarton.ESPECIAL_LINEA_BINGO
					|| carton.getEstado() == EstadoCarton.LINEA_BINGO) {
				usBingo.add(carton.getUser());
			}
		}
		lv.getItems().add("Recaudacion total: " + b.getRecaudacion());
		Reparto r = b.getReparto();
		lv.getItems().add("Recaudado en caja: " + r.getCaja());
		lv.getItems().add("Reparto a especiales: " + r.getRepartoEspeciales() + ", ganadores: " + r.getNumEspeciales());
		if (r.getNumEspeciales() == 1) {
			lv.getItems().add("Ganador especial: " + usEspecial.getFirst().getNick());
		} else {
			String aux = "";
			for (int i = 0; i < usEspecial.size(); i++) {
				aux = aux + usEspecial.get(i).getNick() + ", ";
			}
			aux = aux.replaceFirst(",\\s*$", "");
			lv.getItems().add("Ganadores especial: " + aux);
		}

		lv.getItems().add("Reparto a lineas: " + r.getRepartoLinea() + ", ganadores: " + r.getNumLineas());
		if (r.getNumLineas() == 1) {
			lv.getItems().add("Ganador linea: " + usLinea.getFirst().getNick());
		} else {
			String aux = "";
			for (int i = 0; i < usLinea.size(); i++) {
				aux = aux + usLinea.get(i).getNick() + ", ";
			}
			aux = aux.replaceFirst(",\\s*$", "");
			lv.getItems().add("Ganadores linea: " + usLinea.getFirst().getNick());
		}
		lv.getItems().add("Reparto a bingos: " + r.getRepartoBingo() + ", ganadores: " + r.getNumBingo());
		if (r.getNumBingo() == 1) {
			lv.getItems().add("Ganador bingo: " + usBingo.getFirst().getNick());
		} else {
			String aux = "";
			for (int i = 0; i < usBingo.size(); i++) {
				aux = aux + usBingo.get(i).getNick() + ", ";
			}
			aux = aux.replaceFirst(",\\s*$", "");
			lv.getItems().add("Ganadores bingo: " + aux);
		}
		// Boton para cambiar al siguiente carton
		Button siguienteBingoButton = new Button("Siguiente Bingo");
		siguienteBingoButton.setOnAction(e -> mostrarBingoSiguiente(lbin));
		Button anteriorBingoButton = new Button("Anterior Bingo");
		anteriorBingoButton.setOnAction(e -> mostrarBingoAnterior(lbin));

		HBox botonesBox = new HBox(10);
		botonesBox.getChildren().addAll(anteriorBingoButton, siguienteBingoButton);
		botonesBox.setAlignment(Pos.CENTER);

		bpGeneral.setBottom(botonesBox);

	}

	@FXML
	void InfoBingosActivos(ActionEvent event) {
		List<Bingo> l = d.getBingos().entrySet().stream().filter(map -> map.getValue().isJugado() == false)
				.map(e -> e.getValue()).collect(Collectors.toList());

		if (l.size() == 0) {
			Alert alert3 = new Alert(AlertType.INFORMATION);
			alert3.setContentText("No existen bingos activos actualmente.");
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.showAndWait();
		} else {

			PanelCentral.setRight(null);
			PanelCentral.setCenter(null);
			PanelCentral.setBottom(null);
			bpGeneral.setRight(null);
			bpGeneral.setLeft(null);
			bpGeneral.setBottom(null);
			PanelCentral.setTop(null);
			bpGeneral.setCenter(PanelCentral);

			FlowPane fpCentral = new FlowPane();
			fpCentral.setAlignment(Pos.CENTER);
			PanelCentral.setCenter(fpCentral);

			TableView<Bingo> tableView = new TableView<>();

			tableView.setItems(FXCollections.observableList(l));

			TableColumn<Bingo, String> idColumna = new TableColumn<>("ID");
			idColumna.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<Bingo, Date> fechaColumna = new TableColumn<>("Fecha");
			fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fecha"));

			TableColumn<Bingo, Integer> precioColumna = new TableColumn<>("Precio");
			precioColumna.setCellValueFactory(new PropertyValueFactory<>("precio"));

			TableColumn<Bingo, Float> boteColumna = new TableColumn<>("Bote");
			boteColumna.setCellValueFactory(new PropertyValueFactory<>("recaudacion"));
			boteColumna.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

			tableView.getColumns().addAll(idColumna, fechaColumna, precioColumna, boteColumna);

			fpCentral.getChildren().add(tableView);
		}
	}

	@FXML
	void CrearBingo(ActionEvent event) throws IOException {
		PanelCentral.setRight(null);
		PanelCentral.setCenter(null);
		PanelCentral.setBottom(null);
		bpGeneral.setRight(null);
		bpGeneral.setLeft(null);
		bpGeneral.setBottom(null);
		PanelCentral.setTop(null);
		bpGeneral.setCenter(PanelCentral);
		// Se establece el fichero fxml que se va a cargar
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vista/DialogoCrearBingo.fxml"));
		Parent parent = fxmlLoader.load();
		// Se crear el controlador de dialogo y se pasa el controlador principal
		DialogoCrearBingoController dialogController = fxmlLoader.getController();
		dialogController.setMc(this);
		// Se establece la escena
		Scene scene = new Scene(parent, 300, 200);
		// Se establece el stage
		Stage stage = new Stage();
		stage.setTitle("Crear bingo");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
		float b = dialogController.getBote();
		float p = dialogController.getPrecio();
		Date f = (Date) dialogController.getFechaD();
		String tb = dialogController.getTipoBingo();
		if (f == null || tb.contains("null") || b < 1 || p < 1) {
			Alert alert3 = new Alert(AlertType.INFORMATION);
			alert3.setContentText("Se ha producido un problema con los datos introducidos.");
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.showAndWait();
		} else {
			if (tb.contains("75") || tb.contains("90")) {
				Alert alert3 = new Alert(AlertType.INFORMATION);
				alert3.setContentText("Estos bingos no estan implementados.");
				DialogPane dialogPane = alert3.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
				alert3.showAndWait();
			} else {
				if (d.crearBingo(tb, f, b, p) != null) {
					d.addMonedero("Pruebas", "Pruebas", p * 20);
					for (int i = 0; i < 20; i++) {
						if (tb.equals("80"))
							tb = "80M";
						d.adherirseCarton("Pruebas", "Pruebas", f, tb);

					}
					Alert alert3 = new Alert(AlertType.INFORMATION);
					alert3.setContentText("Se ha creado el bingo correctamente.");
					DialogPane dialogPane = alert3.getDialogPane();
					dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
					alert3.showAndWait();
				} else {
					Alert alert3 = new Alert(AlertType.INFORMATION);
					alert3.setContentText("Ya existe ese tipo de bingo en esa fecha.");
					DialogPane dialogPane = alert3.getDialogPane();
					dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
					alert3.showAndWait();
				}
			}

		}
	}

	@FXML
	void AdherirseBingo(ActionEvent event) throws IOException {
		PanelCentral.setRight(null);
		PanelCentral.setCenter(null);
		PanelCentral.setBottom(null);
		bpGeneral.setRight(null);
		bpGeneral.setLeft(null);
		bpGeneral.setBottom(null);
		PanelCentral.setTop(null);
		bpGeneral.setCenter(PanelCentral);
		// Se establece el fichero fxml que se va a cargar
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vista/DialogoAdherirseBingo.fxml"));
		Parent parent = fxmlLoader.load();
		// Se crear el controlador de dialogo y se pasa el controlador principal
		DialogoAdherirseBingoController dialogController = fxmlLoader.getController();
		dialogController.setM(this);
		// Se establece la escena
		Scene scene = new Scene(parent, 300, 200);
		// Se establece el stage
		Stage stage = new Stage();
		stage.setTitle("Adherirse bingo");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
		int nc = dialogController.getnCartones();
		Date f = (Date) dialogController.getFechaD();
		if (f == null || nc < 1) {
			Alert alert3 = new Alert(AlertType.INFORMATION);
			alert3.setContentText("Se ha producido un problema con los datos introducidos.");
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.showAndWait();
		} else {
			if (d.getBingos().get(f) == null) {
				Alert alert3 = new Alert(AlertType.INFORMATION);
				alert3.setContentText("No existe el bingo seleccionado.");
				DialogPane dialogPane = alert3.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
				alert3.showAndWait();
			}
			if (d.getBingos().get(f).isJugado() == true) {
				Alert alert3 = new Alert(AlertType.INFORMATION);
				alert3.setContentText("Ya se ha jugado este bingo.");
				DialogPane dialogPane = alert3.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
				alert3.showAndWait();
			} else {
				String tb = "80M";
				int cont = 0;
				int cont2 = 0;
				for (int i = 0; i < nc; i++) {
					if (d.adherirseCarton(nickActual, passwdActual, f, tb) != null) {
						cont++;
					}
					cont2++;
				}
				Alert alert3 = new Alert(AlertType.INFORMATION);
				String text = "";
				if (cont == 1) {
					text = text + "Se jugara un carton al bingo.\n";
				} else if (cont == 0) {
					text = text + "No se jugaran cartones al bingo.\n";
				} else {
					text = text + "Se jugaran " + cont + " cartones al bingo.\n";
				}
				if (cont != cont2) {
					text = text + "No se han podido comprar todos los cartones deseados por falta de dinero.";
				}
				DialogPane dialogPane = alert3.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
				alert3.setContentText(text);
				alert3.showAndWait();
			}

		}
	}

	@FXML
	void JugarBingo(ActionEvent event) throws IOException {

		// Se establece el fichero fxml que se va a cargar
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vista/DialogoJugarBingo.fxml"));
		Parent parent = fxmlLoader.load();
		// Se crear el controlador de dialogo y se pasa el controlador principal
		DialogoJugarBingoController dialogController = fxmlLoader.getController();
		dialogController.setMc(this);
		// Se establece la escena
		Scene scene = new Scene(parent, 300, 200);
		// Se establece el stage
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
		bingoCantado = false;
		usEspecial = new LinkedList<Usuario>();
		usBingo = new LinkedList<Usuario>();
		usLinea = new LinkedList<Usuario>();
		lineaCantada = false;
		lineaCantadaAux = false;
		especialCantado = false;
		especialCantadoAux = false;
		Date f = (Date) dialogController.getFechaD();
		bingoJugadoActual = f;
		if (f == null) {
			Alert alert3 = new Alert(AlertType.INFORMATION);
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.setContentText("Se ha producido un problema con los datos introducidos.");
			alert3.showAndWait();
		} else {
			Bingo b = d.getBingos().get(f);
			if (b == null) {
				Alert alert3 = new Alert(AlertType.INFORMATION);
				DialogPane dialogPane = alert3.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
				alert3.setContentText("No existe bingo el dia seleccionado.");
				alert3.showAndWait();
				return;
			} else if (b.isJugado()) {
				Alert alert3 = new Alert(AlertType.INFORMATION);
				DialogPane dialogPane = alert3.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
				alert3.setContentText("Ya se ha jugado este bingo.");
				alert3.showAndWait();
				return;
			}
			int tipoB = b.getB().getTotalBolas();
			b.getB().desordenar();
			// 1
			PanelCentral.setRight(null);
			PanelCentral.setCenter(null);
			PanelCentral.setBottom(null);
			bpGeneral.setRight(null);
			bpGeneral.setLeft(null);
			bpGeneral.setBottom(null);
			PanelCentral.setTop(null);
			bpGeneral.setCenter(PanelCentral);
			cont1 = 0;
			cont2 = 0;
			cont3 = 0;
			// 2 En PanelCentral anadiremos un flowPane y dentro el gripPane
			FlowPane fpCentral = new FlowPane();
			fpCentral.setAlignment(Pos.CENTER);
			PanelCentral.setCenter(fpCentral);
			GridPane g = new GridPane();
			g.setGridLinesVisible(true);
			g.setOpacity(1.0);
			int cont = 1; // Los numeros a mostrar
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 10; j++) {

					// Incluir una imagen
					Image image = new Image(this.getClass().getResourceAsStream("../resource/0.png"), 40, 40, false,
							false); // Como
									// etiqueta
					Label b11 = new Label();
					if (cont < 10) {
						b11.setText(" " + String.valueOf(cont) + " "); // Como texto
					} else {
						b11.setText(String.valueOf(cont)); // Como texto
					}
					b11.setStyle("-fx-background-color: white;");
					b11.setGraphic(new ImageView(image)); // como imagen
					cont++;
					b11.getStyleClass().add("numero");

					b11.setDisable(true);

					g.add(b11, j, i);

				}
			}
			g.setStyle("-fx-opacity: 1.0;");
			g.getChildren().forEach(node -> node.setOpacity(1.0));
			fpCentral.getChildren().add(g);
			fpCentral.setOpacity(1.0);
			PanelCentral.setOpacity(1.0);
			// bpGeneral.setOpacity(1.0);

			// Paso 3. Listview para los comentario en bpGeneral
			ListView<String> lv = new ListView<String>();
			HBox vb = new HBox();
			vb.getChildren().add(lv);
			vb.setSpacing(10);
			vb.setPadding(new Insets(10));
			bpGeneral.setRight(vb);

			// Paso 4. Un boton para que se vaya viendo poco a poco
			Button b1 = new Button("Siguiente numero");
			HBox hb = new HBox();
			hb.getChildren().add(b1);
			PanelCentral.setBottom(hb);

			Button b2 = new Button("Jugar entero");
			hb.getChildren().add(b2);
			PanelCentral.setBottom(hb);
			usLinea.clear();
			usBingo.clear();
			usEspecial.clear();

			// Paso 5. Se procede a ir sacando numero y a jugar.
			// Parte de la logica del juego del bingo debe venir aqui
			b1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Recuperar por valor del texto.
					// util con bolsa bingo cuando salen los numeros

					/////////////////////////////////////// JUGAR//////////////////////////////////////////////
					b.setJugado(true);
					bolaActual = (Integer) b.getB().sacarBola();
					b.getBolasSacadas().add(bolaActual);
					e = estadistica[bolaActual];
					e.setNumSacado(e.getNumSacado() + 1); // se supone que siempre va a ver como mucho 1
					Iterator it = b.getCartones().iterator();
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

					/////////////////////////////////////// JUGAR//////////////////////////////////////////////

					Label lab = (Label) getNodeByNumber(bolaActual, g);
					if (lab != null) {

						lab.setStyle("-fx-font-weight: bold; -fx-background-color: white; -fx-text-fill: red;");

						Image image2 = new Image(this.getClass().getResourceAsStream("../resource/1.png"), 40, 40,
								false, false);
						lab.setGraphic(null);
						lab.setGraphic(new ImageView(image2));

					}

					lv.getItems().add("Numero cantado:" + bolaActual);
					if (lineaCantada && cont1 == 0) {
						for (int i = 0; i < usLinea.size(); i++) {
							Usuario u = usLinea.get(i);
							lv.getItems().add("¡Linea cantada! - " + u.getNick());
						}
						cont1++;
					}
					if (especialCantado && cont2 == 0) {
						for (int i = 0; i < usEspecial.size(); i++) {
							Usuario u = usEspecial.get(i);
							lv.getItems().add("¡Especial cantado! - " + u.getNick());
						}
						cont2++;
					}
					if (bingoCantado) {
						for (int i = 0; i < usBingo.size(); i++) {
							Usuario u = usBingo.get(i);
							lv.getItems().add("¡BINGO CANTADO! - " + u.getNick());
						}
						b1.setVisible(false);
						b2.setVisible(false);
						Reparto r = new Reparto(repartoTotales(usLinea.size(), usBingo.size(), usEspecial.size(), b));
						b.setReparto(r);
						movUsuarios(usLinea, usBingo, usEspecial, r.getRepartoLinea(), r.getRepartoBingo(),
								r.getRepartoEspeciales());

					}

					lv.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
						@Override
						public ListCell<String> call(ListView<String> param) {
							return new ListCell<String>() {
								@Override
								protected void updateItem(String item, boolean empty) {
									super.updateItem(item, empty);

									if (item != null && (item.contains("¡Especial cantado!")
											|| item.contains("¡Linea cantada!") || item.contains("¡BINGO CANTADO!"))) {
										getStyleClass().add("special-cell");
									} else {

										getStyleClass().removeAll("special-cell");
									}

									setText(item);
								}
							};
						}
					});
				}
			});

			// Paso 5. Se procede a ir sacando numero y a jugar.
			// Parte de la logica del juego del bingo debe venir aqui
			b2.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Recuperar por valor del texto.
					// util con bolsa bingo cuando salen los numeros

					/////////////////////////////////////// JUGAR//////////////////////////////////////////////
					do {

						b.setJugado(true);
						bolaActual = (Integer) b.getB().sacarBola();
						b.getBolasSacadas().add(bolaActual);
						e = estadistica[bolaActual];
						e.setNumSacado(e.getNumSacado() + 1); // se supone que siempre va a ver como mucho 1
						Iterator it = b.getCartones().iterator();
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

						/////////////////////////////////////// JUGAR//////////////////////////////////////////////

						Label lab = (Label) getNodeByNumber(bolaActual, g);
						if (lab != null) {
							lab.setStyle("-fx-font-weight: bold; -fx-background-color: white; -fx-text-fill: red;");
							Image image2 = new Image(this.getClass().getResourceAsStream("../resource/1.png"), 40, 40,
									false, false);
							lab.setGraphic(null);
							lab.setGraphic(new ImageView(image2));
						}

						lv.getItems().add("Numero cantado:" + bolaActual);
						if (lineaCantada && cont1 == 0) {
							for (int i = 0; i < usLinea.size(); i++) {
								Usuario u = usLinea.get(i);
								lv.getItems().add("¡Linea cantada! - " + u.getNick());
							}
							cont1++;
						}
						if (especialCantado && cont2 == 0) {
							for (int i = 0; i < usEspecial.size(); i++) {
								Usuario u = usEspecial.get(i);
								lv.getItems().add("¡Especial cantado! - " + u.getNick());
							}
							cont2++;
						}
					} while (!bingoCantado);
					for (int i = 0; i < usBingo.size(); i++) {
						Usuario u = usBingo.get(i);
						lv.getItems().add("¡BINGO CANTADO! - " + u.getNick());
					}
					b1.setVisible(false);
					b2.setVisible(false);
					Reparto r = new Reparto(repartoTotales(usLinea.size(), usBingo.size(), usEspecial.size(), b));
					b.setReparto(r);
					movUsuarios(usLinea, usBingo, usEspecial, r.getRepartoLinea(), r.getRepartoBingo(),
							r.getRepartoEspeciales());
					lv.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
						@Override
						public ListCell<String> call(ListView<String> param) {
							return new ListCell<String>() {
								@Override
								protected void updateItem(String item, boolean empty) {
									super.updateItem(item, empty);

									// Establecer el fondo verde solo para la celda especifica
									if (item != null && (item.contains("¡Especial cantado!")
											|| item.contains("¡Linea cantada!") || item.contains("¡BINGO CANTADO!"))) {
										getStyleClass().add("special-cell");
									} else {
										getStyleClass().removeAll("special-cell");
									}

									setText(item);
								}
							};
						}
					});
				}

			});
		}
	}

	@FXML
	void VerEstadisticas(ActionEvent event) {
		PanelCentral.setRight(null);
		PanelCentral.setCenter(null);
		PanelCentral.setBottom(null);
		bpGeneral.setRight(null);
		bpGeneral.setLeft(null);
		bpGeneral.setBottom(null);
		PanelCentral.setTop(null);
		bpGeneral.setCenter(PanelCentral);

		FlowPane fpCentral = new FlowPane();
		fpCentral.setAlignment(Pos.CENTER);
		PanelCentral.setCenter(fpCentral);
		if (estadistica != null) {

		}

		ObservableList<Estadistica> filteredData = FXCollections.observableArrayList(Arrays.stream(estadistica)
				.filter(estadistica -> estadistica.getNumero() != 0).collect(Collectors.toList()));
		TableView<Estadistica> tableView = new TableView<>();

		TableColumn<Estadistica, Integer> numeroColumn = new TableColumn<>("Numero");
		numeroColumn
				.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumero()).asObject());
		numeroColumn.setPrefWidth(100); // Ajustar el ancho de la columna

		TableColumn<Estadistica, Integer> numSacadoColumn = new TableColumn<>("Veces sacado");
		numSacadoColumn.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getNumSacado()).asObject());
		numSacadoColumn.setPrefWidth(100); // Ajustar el ancho de la columna

		TableColumn<Estadistica, Integer> numLineaColumn = new TableColumn<>("Linea cantada");
		numLineaColumn.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getNumLinea()).asObject());
		numLineaColumn.setPrefWidth(100); // Ajustar el ancho de la columna

		TableColumn<Estadistica, Integer> numBingoColumn = new TableColumn<>("Bingo cantado");
		numBingoColumn.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getNumBingo()).asObject());
		numBingoColumn.setPrefWidth(100); // Ajustar el ancho de la columna

		TableColumn<Estadistica, Integer> numEspecialColumn = new TableColumn<>("Especial cantado");
		numEspecialColumn.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getNumEspecial()).asObject());
		numEspecialColumn.setPrefWidth(100); // Ajustar el ancho de la columna

		tableView.getColumns().addAll(numeroColumn, numSacadoColumn, numLineaColumn, numBingoColumn, numEspecialColumn);

		tableView.setItems(filteredData);

		fpCentral.getChildren().add(tableView);
	}

	public Node getNodeByRowColumnIndex(int row, final int column, GridPane gridPane) {
		Label aux = (Label) gridPane.getChildren().get(row * 10 + (column + 1));
		return aux;
	}

	public Node getNodeByNumber(int number, GridPane gridPane) {
		Node result = null;
		for (Node node : gridPane.getChildren()) {
			if (node instanceof Label) {
				Label b = (Label) node;
				String a = b.getText().replace(" ", "");
				if (a.equals(String.valueOf(number))) {
					result = node;
					break;
				}
			}
		}
		return result;
	}

	public Casino getD() {
		return d;
	}

	public void setD(Casino d) {
		this.d = d;
	}

	public void CargarSerializable(String ficheroDat) throws ClassNotFoundException, IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroDat));
		// Leer el objeto Casino desde el archivo
		d = (Casino) ois.readObject();
		for (Entry<String, Usuario> entry : d.getUsuarios().entrySet()) {
			String clave = entry.getKey();
			Usuario usuario = entry.getValue();
			if (usuario.getHistorico() == null) {
				usuario.setHistorico(new LinkedList<Movimiento>());
			}
		}
		System.out.println("Casino cargado desde casino.dat");

	}

	public void SalvarSerializable(String ficheroDat) throws IOException {

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroDat));
		oos.writeObject(d);
		System.out.println("Casino guardado en casino.dat");

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			CargarSerializable("datos.dat");
			d.cargarMovimientos("movimientos.txt");
			estadistica = d.getEstadistica();
		} catch (ClassNotFoundException | IOException e) {
			Alert alert3 = new Alert(AlertType.INFORMATION);
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.setTitle("No hay fichero!!! ");
			alert3.setContentText("Casino funcionara sin datos.");
			alert3.showAndWait();
			d = new Casino();
			d.register("Pruebas", "Pruebas", "Pruebas", 1);
			d.register("admin", "admin", "admin", 0);
			estadistica = d.getEstadistica();
			if (Files.exists(Paths.get("movimientos.txt"))) {
				try {
					Files.delete(Paths.get("movimientos.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		// Image image = new
		// Image(this.getClass().getResourceAsStream("../resource/logobingo.png"));
	}

	public void ponerFondo() {
		Image image = new Image(this.getClass().getResourceAsStream("../resource/logobingo.png"));
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(1050); // Ajusta el ancho deseado
		imageView.setFitHeight(700); // Ajusta la altura deseada
		PanelCentral.setCenter(imageView);
	}

	@FXML
	void Login(ActionEvent event) throws IOException {
		PanelCentral.setRight(null);
		PanelCentral.setCenter(null);
		PanelCentral.setBottom(null);
		bpGeneral.setRight(null);
		bpGeneral.setLeft(null);
		bpGeneral.setBottom(null);
		PanelCentral.setTop(null);
		bpGeneral.setCenter(PanelCentral);
		// Se establece el fichero fxml que se va a cargar
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vista/DialogoLogin.fxml"));
		Parent parent = fxmlLoader.load();
		DialogoLoginController dialogController = fxmlLoader.getController();

		// Se establece la escena
		Scene scene = new Scene(parent, 300, 200);
		// Se establece el stage
		scene.getStylesheets().add(getClass().getResource("../vista/EstilosVentanas.css").toExternalForm());
		Stage stage = new Stage();
		stage.setTitle("Login");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
		// Una vez finalizado, se recupera la pieza (null o un objeto)
		Usuario p = dialogController.getP();
		if (p == null) {
			Alert alert3 = new Alert(AlertType.INFORMATION);
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.setContentText("Has pulsado cancel o hay datos incorrectos.");
			alert3.showAndWait();
		} else {
			Alert alert3 = new Alert(AlertType.INFORMATION);
			try {
				// Realizo el proceso de logueo
				d.login(p.getNick(), p.getPassword());
				nickActual = p.getNick();
				passwdActual = p.getPassword();
				// Si es admin habilito el menu de admin
				if (p.getNick().equals("admin")) {
					menuAdmin.setDisable(false);
					menuUsuario.setDisable(true);
					// Si no es admin habilito el menu de usuario
				} else {
					menuAdmin.setDisable(true);
					menuUsuario.setDisable(false);
				}
				alert3.setContentText("Logueado correctamente.");
			} catch (UsuarioNoAutenticado e) {
				alert3.setContentText("No se ha podido logear.");
			}
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.showAndWait();
		}
	}

	@FXML
	void AddDineroMonedero(ActionEvent event) throws IOException {
		PanelCentral.setRight(null);
		PanelCentral.setCenter(null);
		PanelCentral.setBottom(null);
		bpGeneral.setRight(null);
		bpGeneral.setLeft(null);
		bpGeneral.setBottom(null);
		PanelCentral.setTop(null);
		bpGeneral.setCenter(PanelCentral);
		// Se establece el fichero fxml que se va a cargar
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vista/DialogoAddDinero.fxml"));
		Parent parent = fxmlLoader.load();
		// Se crear el controlador de dialogo y se pasa el controlador principal
		DialogoAddDineroController dialogController = fxmlLoader.getController();
		dialogController.setM(this);
		// Se establece la escena
		Scene scene = new Scene(parent, 300, 200);
		// Se establece el stage
		Stage stage = new Stage();
		stage.setTitle("Monedero");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
		Float din = dialogController.getDineroE();
		if (din <= 0.0F) {
			Alert alert3 = new Alert(AlertType.INFORMATION);
			alert3.setContentText("Se ha introducido un numero no valido.");
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.showAndWait();
		} else {
			d.addMonedero(nickActual, passwdActual, din);
			Alert alert3 = new Alert(AlertType.INFORMATION);
			alert3.setContentText("Dinero anadido correctamente.");
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.showAndWait();
		}
	}

	@FXML
	void registerUsuario(ActionEvent event) throws IOException {
		PanelCentral.setRight(null);
		PanelCentral.setCenter(null);
		PanelCentral.setBottom(null);
		bpGeneral.setRight(null);
		bpGeneral.setLeft(null);
		bpGeneral.setBottom(null);
		PanelCentral.setTop(null);
		bpGeneral.setCenter(PanelCentral);
		// Se establece el fichero fxml que se va a cargar
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vista/DialogoRegistro.fxml"));
		Parent parent = fxmlLoader.load();
		// Se crear el controlador de dialogo y se pasa el controlador principal
		DialogoRegistroController dialogController = fxmlLoader.getController();
		dialogController.setM(this);
		// Se establece la escena
		Scene scene = new Scene(parent, 300, 200);
		// Se establece el stage
		Stage stage = new Stage();
		stage.setTitle("Registro");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
	}

	public void shutdown() {
		try {
			SalvarSerializable("datos.dat");
		} catch (IOException e) { // Alert con un mensaje de error
			Alert alert3 = new Alert(AlertType.INFORMATION);
			alert3.setContentText("No se ha podido guardar la informacion correctamente.");
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.showAndWait();
		}
		try {
			d.salvarMovimientos("movimientos.txt");
		} catch (IOException e) {
			Alert alert3 = new Alert(AlertType.INFORMATION);
			alert3.setContentText("No se ha podido guardar la informacion de los movimientos correctamente.");
			DialogPane dialogPane = alert3.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
			alert3.showAndWait();
		}
	}

	/////////////////////////// JUGAR
	/////////////////////////// ///////////////////////////////////////////////////

	/**
	 * Metodo para el reparto completo de los premios del bingo segun lo
	 * especificado: Caja se lleva el 10% de la recaudacion Los especiales se llevan
	 * 2 euros por cada especial. Las lineas se llevan un 30% de lo sobrante de la
	 * recaudacion tras retirar el dinero de la caja y de los especiales. Los bingos
	 * se llevan el 70% de lo sobrante de la recaudacion tras retirar el dinero de
	 * la caja y de los especiales. Se hace el redondeo a 2 decimales y si sobran
	 * decimales se les anade a la caja.
	 *
	 * @param nL El numero total de lineas ganadoras.
	 * @param nB El numero total de bingos ganadores.
	 * @param nE El numero total de premios especiales ganadores.
	 * @return Un objeto Reparto que contiene la distribucion detallada de los
	 *         premios.
	 */
	private Reparto repartoTotales(int nL, int nB, int nE, Bingo b) {
		float caja = (b.getRecaudacion() * 10) / 100;
		float especiales = 2 * nE;
		float lineas = ((b.getRecaudacion() - caja - especiales) * 0.3F);
		float bingos = ((b.getRecaudacion() - caja - especiales) * 0.7F);
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
	 * ganado (bingo, linea y/o especial) Dependiendo de lo ganado se anade en
	 * historico de movimientos y se da el dinero ganado al jugador.
	 *
	 * @param usLinea     Lista de usuarios ganadores en la categoria de linea.
	 * @param usBingo     Lista de usuarios ganadores en la categoria de bingo.
	 * @param usEspecial  Lista de usuarios ganadores en la categoria de premio
	 *                    especial.
	 * @param rLineas     Monto total a repartir en premios de lineas.
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
		// si estan en algunos de los linkedlist.
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
		// 3. Si solo esta el usuario en un LL, pues solo ese movimiento. Si esta en
		// mas, se suman esos movimientos.
		Iterator it = setUsuarios.iterator();
		Alert alert3 = new Alert(AlertType.INFORMATION);
		String m = "BINGO TERMINADO \n \n";
		while (it.hasNext()) {
			bLinea = false;
			bBingo = false;
			bEspecial = false;
			Usuario u = (Usuario) it.next();
			// 4. Comprobaciones de en que LL se encuentra el usuario
			if (usLinea.contains(u)) {
				bLinea = true;
			}
			if (usBingo.contains(u)) {
				bBingo = true;
			}
			if (usEspecial.contains(u)) {
				bEspecial = true;
			}

			// 5. Posibilidades de reparticion de premios. AnADIR PREMIOS
			if (bLinea && bBingo && bEspecial) {
				valor = divLineas + divBingos + divEspecial;
				u.getHistorico().add(
						new Movimiento("Premio de Linea,Bingo y Especial", u.getMonedero(), valor, bingoJugadoActual));
				u.setMonedero(u.getMonedero() + valor);
				m = m + "Premio de Linea,Bingo y Especial - " + u.getNick() + " , " + valor + "\n";
			} else if (bLinea && bBingo) {
				valor = divLineas + divBingos;
				u.getHistorico()
						.add(new Movimiento("Premio de Linea y Bingo", u.getMonedero(), valor, bingoJugadoActual));
				u.setMonedero(u.getMonedero() + valor);
				m = m + "Premio de Linea y Bingo - " + u.getNick() + " , " + valor + "\n";
			} else if (bLinea && bEspecial) {
				valor = divLineas + divEspecial;
				u.getHistorico()
						.add(new Movimiento("Premio de Linea y Especial ", u.getMonedero(), valor, bingoJugadoActual));
				u.setMonedero(u.getMonedero() + valor);
				m = m + "Premio de Linea y Especial - " + u.getNick() + " , " + valor + "\n";
			} else if (bBingo && bEspecial) {
				valor = divEspecial + divBingos;
				u.getHistorico()
						.add(new Movimiento("Premio de Especial y Bingo", u.getMonedero(), valor, bingoJugadoActual));
				u.setMonedero(u.getMonedero() + valor);
				m = m + "Premio de Especial y Bingo - " + u.getNick() + " , " + valor + "\n";
			} else if (bLinea) {
				u.getHistorico().add(new Movimiento("Premio de Linea", u.getMonedero(), divLineas, bingoJugadoActual));
				u.setMonedero(u.getMonedero() + divLineas);
				m = m + "Premio de Linea - " + u.getNick() + " , " + divLineas + "\n";
			} else if (bBingo) {
				u.getHistorico().add(new Movimiento("Premio de Bingo", u.getMonedero(), divBingos, bingoJugadoActual));
				u.setMonedero(u.getMonedero() + divBingos);
				m = m + "Premio de Bingo - " + u.getNick() + " , " + divBingos + "\n";
			} else if (bEspecial) {
				u.getHistorico()
						.add(new Movimiento("Premio de Especial", u.getMonedero(), divEspecial, bingoJugadoActual));
				u.setMonedero(u.getMonedero() + divEspecial);
				m = m + "Premio de Especial - " + u.getNick() + " , " + divEspecial + "\n";
			}
		}
		alert3.setContentText(m);
		DialogPane dialogPane = alert3.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
		alert3.showAndWait();

	}

	/**
	 * Redondea un numero decimal al numero especificado de lugares decimales.
	 * Utiliza el metodo de redondeo HALF_UP de BigDecimal para realizar el
	 * redondeo.
	 *
	 * @param d            El numero decimal que se va a redondear.
	 * @param decimalPlace El numero de decimales al que se redondeara el numero.
	 * @return El numero decimal redondeado al numero especificado de decimales.
	 */
	private float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

}
