package es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogoAddDineroController implements Initializable {
	@FXML
	private Button bAnadir;

	@FXML
	private TextField dinero;

	private MainController mc = null;

	private float dineroE;

	public float getDineroE() {
		return dineroE;
	}

	public void setDinero(float dineroE) {
		this.dineroE = dineroE;
	}

	public void setM(MainController mc) {
		this.mc = mc;
	}

	@FXML
	void PulsadoAnadir(ActionEvent event) {
		try {
			dineroE = Float.parseFloat(dinero.getText());
			closeStage(event);
		} catch (java.lang.NumberFormatException e) {
			dineroE = -1;
		}
	}

	private void closeStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dinero.setText("0.0");

	}

}