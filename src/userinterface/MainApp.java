package userinterface;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private static Stage stagePrimario;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.stagePrimario = primaryStage;
		this.stagePrimario.setTitle("Calculadora ClassLess");
		primaryStage.getIcons()
				.add(new Image("/imagens/burger.png"));

		initRootLayout();

		showPersonOverview();
	}

	/**
	 * Inicializa o layout base.
	 */
	public void initRootLayout() {
		try {
			// Carrega o root layout do arquivo fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Principal.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Mostra a scene (cena) contendo o root layout.
			Scene scene = new Scene(rootLayout);
			stagePrimario.setScene(scene);
			stagePrimario.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostra o person overview dentro do root layout.
	 */
	public void showPersonOverview() {
		try {
			// Carrega o person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PrincipalPane.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			PrincipalController controller = new PrincipalController();

			// Define o person overview dentro do root layout.
			rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStagePrimario() {
		return stagePrimario;
	}

	public static void setStagePrimario(Stage stagePrimario) {
		MainApp.stagePrimario = stagePrimario;
	}

}
