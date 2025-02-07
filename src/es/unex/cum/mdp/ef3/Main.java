package es.unex.cum.mdp.ef3;

import es.unex.cum.mdp.ef3.controller.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/Main.fxml"));
//Recuperamos el controller
			Parent root = loader.load();
			MainController controller = (MainController) loader.getController();
			// DialogoRegistroController controller = (DialogoRegistroController)
			// loader.getController();
			// DialogoLoginController controller = (DialogoLoginController)
			// loader.getController();
			Screen screen = Screen.getPrimary();

			// Configurar el tamano y la posicion del escenario para que sea pantalla
			// completa
			primaryStage.setX(screen.getVisualBounds().getMinX());
			primaryStage.setY(screen.getVisualBounds().getMinY());
			primaryStage.setWidth(screen.getVisualBounds().getWidth());
			primaryStage.setHeight(screen.getVisualBounds().getHeight());

			Scene scene = new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("vista/MainEstilos.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Bingo");
			controller.ponerFondo();
//Cuando se cierre se llama al metodo shutdown de controller
			primaryStage.setOnHidden(e -> { // Capturar al pulsar Exit
				controller.shutdown();
				Platform.exit();
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
