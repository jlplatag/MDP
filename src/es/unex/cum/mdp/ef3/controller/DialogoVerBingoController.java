package es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class DialogoVerBingoController implements Initializable {
	@FXML
	private DatePicker DiaBingo;

	private Date fechaD;
	private MainController mc = null;

	public MainController getMc() {
		return mc;
	}

	public void setMc(MainController mc) {
		this.mc = mc;
	}

	public Date getFechaD() {
		return fechaD;
	}

	public void setFechaD(Date fechaD) {
		this.fechaD = fechaD;
	}

	@FXML
	void PulsadoVerInfo(ActionEvent event) {
		if (DiaBingo.getValue() != null) {
			LocalDate fecha = DiaBingo.getValue();
			ZoneId defaultZoneId = ZoneId.systemDefault();
			fechaD = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
		}

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
