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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DialogoVerPerfilController implements Initializable {

	@FXML
	private Label Monedero;

	@FXML
	private CheckBox VerContrasena;

	@FXML
	private Label nick;

	@FXML
	private Label nombre;

	@FXML
	private Label passwd;

	// Objeto MainController que es controlador principal para poder acceder a
	// Casino
	private MainController mc = null;

	// Solo el setter, asi se evita que se devuelva
	public void setMc(MainController mc) {
		this.mc = mc;
	}

	@FXML
	void PulsadoEditar(ActionEvent event) throws IOException {
		mc.setCambio(true);
		closeStage(event);
	}

	@FXML
	void VerPasswd(ActionEvent event) {
		if (VerContrasena.isSelected()) {
			passwd.setText(mc.getPasswdActual());
		} else {
			String passwd1 = "";
			for (int i = 0; i < mc.getPasswdActual().length(); i++) {
				passwd1 = passwd1 + "*";
			}
			passwd.setText(passwd1);
		}
	}

	public void datos() {
		try {
			Usuario u = mc.getD().login(mc.getNickActual(), mc.getPasswdActual());
			nick.setText(u.getNick());
			nombre.setText(u.getNombre());
			String passwd1 = "";
			for (int i = 0; i < mc.getPasswdActual().length(); i++) {
				passwd1 = passwd1 + "*";
			}
			passwd.setText(passwd1);
			System.out.println(u.getMonedero());
			String a = String.valueOf(u.getMonedero());
			Monedero.setText(a);
		} catch (UsuarioNoAutenticado e) {
			// NO entra nunca
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	private void closeStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
}
