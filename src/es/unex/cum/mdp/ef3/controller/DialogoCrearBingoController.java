package es.unex.cum.mdp.ef3.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
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

public class DialogoCrearBingoController implements Initializable {
	@FXML
	private TextField Bote;

	@FXML
	private DatePicker DiaBingo;

	@FXML
	private TextField Precio;

	@FXML
	private Button bAnadir;

	@FXML
	private ComboBox<String> tBingo;

	private MainController mc = null;
	private float precio;
	private float bote;
	private Date fechaD;
	private String tipoBingo = "null";

	public MainController getMc() {
		return mc;
	}

	public void setMc(MainController mc) {
		this.mc = mc;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getBote() {
		return bote;
	}

	public void setBote(float bote) {
		this.bote = bote;
	}

	public Date getFechaD() {
		return fechaD;
	}

	public void setFechaD(Date fechaD) {
		this.fechaD = fechaD;
	}

	public String getTipoBingo() {
		return tipoBingo;
	}

	public void setTipoBingo(String tipoBingo) {
		this.tipoBingo = tipoBingo;
	}

	@FXML
	void PulsadoAdherir(ActionEvent event) {
		try {

			precio = Float.parseFloat(Precio.getText());
			bote = Float.parseFloat(Bote.getText());

			if (DiaBingo.getValue() != null) {
				LocalDate fecha = DiaBingo.getValue();
				ZoneId defaultZoneId = ZoneId.systemDefault();
				fechaD = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
			}

			closeStage(event);
		} catch (java.lang.NumberFormatException e) {
			precio = -1;
			bote = -1;
			closeStage(event);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> opciones = FXCollections.observableArrayList("75", "80", "90");
		tBingo.setItems(opciones);
		tBingo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				tipoBingo = tBingo.getSelectionModel().getSelectedItem();

			}
		});
	}

	private void closeStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
}
