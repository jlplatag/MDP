package es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import es.unex.cum.mdp.ef3.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogoLoginController implements Initializable {

	@FXML
	private TextField Nombre;

	@FXML
	private Button bentrar;

	@FXML
	private PasswordField password;

	private Usuario p = null;

	public Usuario getP() {
		return p;
	}

	public void setP(Usuario p) {
		this.p = p;
	}

	@FXML
	void PulsadoCancelar(ActionEvent event) {
		p = null;
		closeStage(event);
	}

	@FXML
	void PulsadoEntrar(ActionEvent event) {
		try {
			p = new Usuario(Nombre.getText(), Nombre.getText(), password.getText(), 0.0F);
			closeStage(event);
		} catch (java.lang.NumberFormatException e) {
			p = null;
		}
	}

	private void closeStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Nombre.setText("");

	}

}