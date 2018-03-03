package entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import interfaces.Classe;

public class IPv4 {

	private Bloco bloco1;
	private Bloco bloco2;
	private Bloco bloco3;
	private Bloco bloco4;

	private Classe classe;

	public IPv4(String ipv4) {
		String[] split = ipv4.split("\\.");
		bloco1 = new Bloco(split[0]);
		bloco2 = new Bloco(split[1]);
		bloco3 = new Bloco(split[2]);
		bloco4 = new Bloco(split[3]);
		// calculaClasse();
	}

	public Bloco getBloco1() {
		return bloco1;
	}

	public void setBloco1(Bloco bloco1) {
		this.bloco1 = bloco1;
	}

	public Bloco getBloco2() {
		return bloco2;
	}

	public void setBloco2(Bloco bloco2) {
		this.bloco2 = bloco2;
	}

	public Bloco getBloco3() {
		return bloco3;
	}

	public void setBloco3(Bloco bloco3) {
		this.bloco3 = bloco3;
	}

	public Bloco getBloco4() {
		return bloco4;
	}

	public void setBloco4(Bloco bloco4) {
		this.bloco4 = bloco4;
	}

	/**
	 * Formata o ip com pontos entre os blocos
	 * 
	 * @return
	 *
	 * @author Renahn - 14 de nov de 2017
	 */
	public String ipFormatado() {
		return new StringBuilder().append(bloco1).append(".").append(bloco2).append(".").append(bloco3).append(".")
				.append(bloco4).toString();
	}

	/**
	 * Formata o ip em binário, com pontos entre os octetos
	 * 
	 * @return
	 *
	 * @author Renahn - 14 de nov de 2017
	 */
	public String ipFormatadoEmOctetos() {
		return new StringBuilder().append(bloco1.getOcteto()).append(".").append(bloco2.getOcteto()).append(".")
				.append(bloco3.getOcteto()).append(".").append(bloco4.getOcteto()).toString();
	}

	/**
	 * Método que realiza o cálculo da classe do ip com base no IP.
	 * 
	 * @author Renahn - 14 de nov de 2017
	 */
	private void calculaClasse() {
		if (bloco1.getValor() <= new ClasseA().getFaixa()) {
			setClasse(new ClasseA());
		} else if (bloco1.getValor() <= new ClasseB().getFaixa()) {
			setClasse(new ClasseB());
		} else if (bloco1.getValor() <= new ClasseC().getFaixa()) {
			setClasse(new ClasseC());
		}
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public static String adicionarPontosAoBinario(String ipEmBinario) {
		List<String> strings = new ArrayList<String>();
		int contador = 0;
		while (contador < ipEmBinario.length()) {
			strings.add(ipEmBinario.substring(contador, Math.min(contador + 8, ipEmBinario.length())));
			contador += 8;
		}
		ipEmBinario = "";
		for (String octeto : strings) {
			if (!ipEmBinario.equals("")) {
				ipEmBinario += ".";
			}
			ipEmBinario += octeto;
		}
		return ipEmBinario;
	}

	public IPv4 proximoIp() {
		if (!this.bloco4.incrementarValor()) {
			if (!this.bloco3.incrementarValor()) {
				if (!this.bloco2.incrementarValor()) {
					throw new RuntimeException("Quantidade de hosts excedentes");
				}
			}
		}
		return this;
	}

	@Override
	public String toString() {
		return ipFormatado();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloco1 == null) ? 0 : bloco1.hashCode());
		result = prime * result + ((bloco2 == null) ? 0 : bloco2.hashCode());
		result = prime * result + ((bloco3 == null) ? 0 : bloco3.hashCode());
		result = prime * result + ((bloco4 == null) ? 0 : bloco4.hashCode());
		result = prime * result + ((classe == null) ? 0 : classe.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IPv4 other = (IPv4) obj;
		if (bloco1 == null) {
			if (other.bloco1 != null)
				return false;
		} else if (!bloco1.equals(other.bloco1))
			return false;
		if (bloco2 == null) {
			if (other.bloco2 != null)
				return false;
		} else if (!bloco2.equals(other.bloco2))
			return false;
		if (bloco3 == null) {
			if (other.bloco3 != null)
				return false;
		} else if (!bloco3.equals(other.bloco3))
			return false;
		if (bloco4 == null) {
			if (other.bloco4 != null)
				return false;
		} else if (!bloco4.equals(other.bloco4))
			return false;
		if (classe == null) {
			if (other.classe != null)
				return false;
		} else if (!classe.equals(other.classe))
			return false;
		return true;
	}

	public Integer calculaQuantidadeHostsPossiveis() {
		if (classe.toString().equals("A")) {
			return classe.getQuantidadeHostsPossiveis(Arrays.asList(bloco2, bloco3));
		} else if (classe.toString().equals("B")) {
			return classe.getQuantidadeHostsPossiveis(Arrays.asList(bloco3));
		}
		return classe.getQuantidadeHostsPossiveis(new ArrayList<>());
	}

}
