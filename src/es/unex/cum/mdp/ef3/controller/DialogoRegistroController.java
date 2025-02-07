package es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogoRegistroController implements Initializable {

	@FXML
	private TextField Nombre;

	@FXML
	private Button bCancelar;

	@FXML
	private Button bok;

	@FXML
	private TextField nick;

	@FXML
	private PasswordField password;

	// Objeto MainController que es controlador principal para poder acceder a
	// Casino
	private MainController mc = null;

	// Solo el setter, asi se evita que se devuelva
	public void setM(MainController mc) {
		this.mc = mc;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nick.setText("");
		Nombre.setText("");
	}

	@FXML
	void PulsadoCancelar(ActionEvent event) {
		closeStage(event);
	}

	@FXML
	void PulsadoOK(ActionEvent event) {
		try {
			if (mc != null) {
				Alert alert3 = new Alert(AlertType.INFORMATION);
				if (nick.getText().equals("") || password.getText().equals("") || Nombre.getText().equals("")) {
					alert3.setContentText("Valores introducidos no validos.");
				} else {
					if (mc.getD().register(nick.getText(), Nombre.getText(), password.getText(), 0.0F)) {
						alert3.setContentText("Registrado correctamente");
					} else {
						alert3.setContentText("Problema con la autenticacion.");
					}
				}
				DialogPane dialogPane = alert3.getDialogPane();
				dialogPane.getStylesheets().add(getClass().getResource("../vista/Advert.css").toExternalForm());
				alert3.showAndWait();
			}
			closeStage(event);
		} catch (java.lang.NumberFormatException e) {
		}
	}

	private void closeStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

}
