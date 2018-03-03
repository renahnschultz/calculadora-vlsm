package userinterface;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import calculos.CalculaClassLess;
import entidades.IPv4;
import entidades.QuantidadeDeHostsNecessaria;
import entidades.Subnet;
import interfaces.Classe;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrincipalController implements Initializable {
	@FXML
	private TextField ipParaCalculo;
	@FXML
	private TextField hostsText;

	// TABELA DE QUANTIDADE DE HOSTS
	@FXML
	private TableView<QuantidadeDeHostsNecessaria> hosts;
	@FXML
	private TableColumn<QuantidadeDeHostsNecessaria, Integer> qtdHosts;
	@FXML
	private TableColumn<QuantidadeDeHostsNecessaria, Integer> maisDois;
	@FXML
	private TableColumn<QuantidadeDeHostsNecessaria, Integer> proximaPotencia;

	private List<QuantidadeDeHostsNecessaria> adicionados = new ArrayList<QuantidadeDeHostsNecessaria>();

	// TEXTOS
	@FXML
	private Text ipBase;
	@FXML
	private Text ipBaseBin;
	@FXML
	private Text classe;
	@FXML
	private Text mascaraCustomizada;
	@FXML
	private Text mascaraCustomizadaBin;
	@FXML
	private Text mascaraPadrao;
	@FXML
	private Text mascaraPadraoBin;

	private CalculaClassLess calculaClassLess;

	private Integer qtdHostsNecessarios = 0;

	// TABELA DE QUANTIDADE DE HOSTS
	@FXML
	private TableView<Subnet> tableSubnets;
	@FXML
	private TableColumn<QuantidadeDeHostsNecessaria, Integer> numero;
	@FXML
	private TableColumn<QuantidadeDeHostsNecessaria, String> idRedeSubnet;
	@FXML
	private TableColumn<QuantidadeDeHostsNecessaria, String> broadcastSubnet;
	@FXML
	private TableColumn<QuantidadeDeHostsNecessaria, String> broadcastBinario;
	@FXML
	private TableColumn<QuantidadeDeHostsNecessaria, String> idRedeBinario;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		qtdHosts.setCellValueFactory(new PropertyValueFactory<>("qtdHosts"));
		maisDois.setCellValueFactory(new PropertyValueFactory<>("maisDois"));
		proximaPotencia.setCellValueFactory(new PropertyValueFactory<>("proximaPotencia"));
		numero.setCellValueFactory(new PropertyValueFactory<>("posicao"));
		idRedeSubnet.setCellValueFactory(new PropertyValueFactory<>("idRede"));
		broadcastSubnet.setCellValueFactory(new PropertyValueFactory<>("broadcast"));
		broadcastBinario.setCellValueFactory(new PropertyValueFactory<>("broadcastOcteto"));
		idRedeBinario.setCellValueFactory(new PropertyValueFactory<>("idRedeOcteto"));

	}

	@FXML
	private void adicionarHosts(MouseEvent event) {
		try {
			if (hostsText.getText().isEmpty()) {
				return;
			}
			Integer qtdHosts = Integer.parseInt(hostsText.getText());
			QuantidadeDeHostsNecessaria novoHost = new QuantidadeDeHostsNecessaria(qtdHosts);
			qtdHostsNecessarios = qtdHostsNecessarios + novoHost.getProximaPotencia();
			adicionados.add(novoHost);
			hosts.setItems(FXCollections.observableArrayList(adicionados));
		} catch (Exception e) {
			mostrarAlerta(event, "Digite uma quantidade de hosts válida.");
		}
	}

	@FXML
	private void realizaCalculo(MouseEvent event) {
		if (validacoes(event)) {
			this.calculaClassLess = new CalculaClassLess();
			calculaClassLess.calcular(new IPv4(ipParaCalculo.getText()), adicionados, qtdHostsNecessarios);
			inserirTextosNaTela();
		}
	}

	/**
	 * Método realiza as validações necessárias para o bom funcionamento do
	 * programa.
	 * 
	 *
	 * @author Renahn - 19 de nov de 2017
	 */
	private boolean validacoes(MouseEvent event) {
		if (ipParaCalculo.getText().isEmpty()) {
			mostrarAlerta(event, "O IP não pode ser vazio.");
			return false;
		} else {
			IPv4 ip = null;
			try {
				ip = new IPv4(ipParaCalculo.getText());
			} catch (Exception e) {
				mostrarAlerta(event, "Digite um IP válido.");
				return false;
			}
		}
		return true;

	}

	/**
	 * Insere os valores na tela.
	 *
	 * @author Renahn - 18 de nov de 2017
	 */
	private void inserirTextosNaTela() {
		IPv4 ipBaseCalculo = calculaClassLess.getIpBase();
		Classe classeIpBase = ipBaseCalculo.getClasse();
		this.ipBase.setText(ipBaseCalculo.ipFormatado());
		this.ipBaseBin.setText(ipBaseCalculo.ipFormatadoEmOctetos());
		this.classe.setText(calculaClassLess.getClasseDaRede().toString());
		this.mascaraPadrao.setText(calculaClassLess.getClasseDaRede().getMascaraPadrao().ipFormatado());
		this.mascaraPadraoBin.setText(calculaClassLess.getClasseDaRede().getMascaraPadrao().ipFormatadoEmOctetos());
		tableSubnets.setItems(FXCollections.observableArrayList(calculaClassLess.getSubnets()));
	}

	public TextField getIpParaCalculo() {
		return ipParaCalculo;
	}

	public void setIpParaCalculo(TextField ipParaCalculo) {
		this.ipParaCalculo = ipParaCalculo;
	}

	public TableView<Subnet> getTableSubnets() {
		return tableSubnets;
	}

	public void setTableSubnets(TableView<Subnet> tableSubnets) {
		this.tableSubnets = tableSubnets;
	}

	public TableColumn<QuantidadeDeHostsNecessaria, Integer> getNumero() {
		return numero;
	}

	public void setNumero(TableColumn<QuantidadeDeHostsNecessaria, Integer> numero) {
		this.numero = numero;
	}

	/**
	 * Mostra o person overview dentro do root layout.
	 */
	public void mostrarAlerta(MouseEvent event, String mensagem) {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Alerta.fxml"));
			Stage stage = new Stage();
			root = loader.load();
			AlertaController controller = loader.getController();
			stage.setScene(new Scene(root));
			stage.setTitle("Alerta");
			Image image = new Image("/imagens/alerta.png");
			stage.getIcons().add(image);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Node) event.getSource()).getScene().getWindow());
			stage.show();
			controller.setStage(stage);
			controller.getTexto().setText(mensagem);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void recomecar() {
		ipParaCalculo.setText("");
		ipBase.setText("");
		ipBaseBin.setText("");
		classe.setText("");
		mascaraCustomizada.setText("");
		mascaraCustomizadaBin.setText("");
		mascaraPadrao.setText("");
		mascaraPadraoBin.setText("");
		qtdHostsNecessarios = 0;
		hostsText.setText("");
		adicionados = new ArrayList<QuantidadeDeHostsNecessaria>();
		hosts.setItems(FXCollections.observableArrayList(adicionados));
		tableSubnets.setItems(FXCollections.observableArrayList(new ArrayList<Subnet>()));
	}

}
