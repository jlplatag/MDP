package es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogoAdherirseBingoController implements Initializable {
	@FXML
	private TextField CantidadCartones;

	@FXML
	private DatePicker DiaBingo;

	@FXML
	private TextField cCartones;

	private MainController mc = null;
	private int nCartones;
	private Date fechaD;

	public int getnCartones() {
		return nCartones;
	}

	public void setnCartones(int nCartones) {
		this.nCartones = nCartones;
	}

	public Date getFechaD() {
		return fechaD;
	}

	public void setFechaD(Date fecha) {
		this.fechaD = fecha;
	}

	public void setM(MainController mc) {
		this.mc = mc;
	}

	@FXML
	private Button bAnadir;

	@FXML
	void PulsadoAdherir(ActionEvent event) {
		try {
			nCartones = Integer.parseInt(cCartones.getText());

			if (DiaBingo.getValue() != null) {
				LocalDate fecha = DiaBingo.getValue();
				ZoneId defaultZoneId = ZoneId.systemDefault();
				fechaD = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
			}

			closeStage(event);
		} catch (java.lang.NumberFormatException e) {
			nCartones = -1;
			closeStage(event);
		}

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
