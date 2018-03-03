package userinterface;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AlertaController implements Initializable {

	@FXML
	private Text texto;

	private Stage stage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void fecharJanela(MouseEvent event) {
		stage.close();
	}

	public Text getTexto() {
		return texto;
	}

	public void setTexto(Text texto) {
		this.texto = texto;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
