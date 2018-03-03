package entidades;

public class QuantidadeDeHostsNecessaria {

	private Integer qtdHosts;
	private Integer maisDois;
	private Integer proximaPotencia;

	public QuantidadeDeHostsNecessaria(Integer qtdHosts) {
		this.qtdHosts = qtdHosts;
		this.maisDois = qtdHosts + 2;
		this.proximaPotencia = proximaPotenciaDeDois(maisDois);
	}

	/**
	 * Este método calcula qual a proxima potência de dois a partir de um numero.
	 * 
	 * @param qtdHosts
	 *            quantidade de hosts mais dois.
	 * @return proxima potencia de dois a partir do valor passado
	 *
	 * @author Renahn - 18 de nov de 2017
	 */
	private static Integer proximaPotenciaDeDois(Integer valor) {
		return valor == 1 ? 1 : Integer.highestOneBit(valor - 1) * 2;
	}

	public Integer getQtdHosts() {
		return qtdHosts;
	}

	public void setQtdHosts(Integer qtdHosts) {
		this.qtdHosts = qtdHosts;
	}

	public Integer getMaisDois() {
		return maisDois;
	}

	public void setMaisDois(Integer maisDois) {
		this.maisDois = maisDois;
	}

	public Integer getProximaPotencia() {
		return proximaPotencia;
	}

	public void setProximaPotencia(Integer proximaPotencia) {
		this.proximaPotencia = proximaPotencia;
	}

}
