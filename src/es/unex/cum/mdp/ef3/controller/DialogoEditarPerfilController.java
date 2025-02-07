package es.unex.cum.mdp.ef3.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import es.unex.cum.mdp.ef3.modelo.Usuario;
import es.unex.cum.mdp.ef3.modelo.UsuarioNoAutenticado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogoEditarPerfilController implements Initializable {

	@FXML
	private TextField nombre;

	@FXML
	private TextField passwd;

	public String passwdN;

	public String getPasswdN() {
		return passwdN;
	}

	public void setPasswdN(String passwdN) {
		this.passwdN = passwdN;
	}

	// Objeto MainController que es controlador principal para poder acceder a
	// Casino
	private MainController mc = null;

	// Solo el setter, asi se evita que se devuelva
	public void setMc(MainController mc) {
		this.mc = mc;
	}

	public void datos() {
		try {
			Usuario u = mc.getD().login(mc.getNickActual(), mc.getPasswdActual());
			nombre.setText(u.getNombre());
			passwd.setText(u.getPassword());
		} catch (UsuarioNoAutenticado e) {

		}
	}

	@FXML
	void PulsadoModificar(ActionEvent event) throws IOException {

		try {
			Usuario u = mc.getD().login(mc.getNickActual(), mc.getPasswdActual());
			if (nombre.getText() != "")
				u.setNombre(nombre.getText());
			if (passwd.getText() != "") {
				if (mc.getPasswdActual().equals("Pruebas") && mc.getNickActual().equals("Pruebas")) {
					u.setPassword(mc.getPasswdActual());
					setPasswdN(mc.getPasswdActual());
				} else {
					u.setPassword(passwd.getText());
					setPasswdN(passwd.getText());
				}
			}
		} catch (UsuarioNoAutenticado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mc.setCambio(true);
		closeStage(event);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	private void closeStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
}
