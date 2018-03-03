package entidades;

import java.math.BigInteger;

public class Bloco {

	private Integer valor;
	private String octeto;

	public Bloco(String valor) {
		if (valor.length() == 8) {
			setOcteto(valor);
		} else {
			setValor(Integer.parseInt(valor));
		}
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		if (valor > 255) {
			throw new RuntimeException("O valor de um bloco não pode ser maior que 255.");
		}
		this.valor = valor;
		calculaOcteto();
	}

	public String getOcteto() {
		return octeto;
	}

	public void setOcteto(String octeto) {
		this.octeto = octeto;
		calculaValor();
	}

	/**
	 * 
	 * Este método utiliza o valor da classe para calcular e setar o octeto.
	 *
	 * @author Renahn - 14 de nov de 2017
	 */
	private void calculaOcteto() {
		octeto = new String();
		Integer valorManipulado = new Integer(valor);
		valorManipulado = manipuladoMenorQueValor(valorManipulado, 128);
		valorManipulado = manipuladoMenorQueValor(valorManipulado, 64);
		valorManipulado = manipuladoMenorQueValor(valorManipulado, 32);
		valorManipulado = manipuladoMenorQueValor(valorManipulado, 16);
		valorManipulado = manipuladoMenorQueValor(valorManipulado, 8);
		valorManipulado = manipuladoMenorQueValor(valorManipulado, 4);
		valorManipulado = manipuladoMenorQueValor(valorManipulado, 2);
		valorManipulado = manipuladoMenorQueValor(valorManipulado, 1);
	}

	/**
	 * Métdoo recebe um valor a ser manipulado e o valor para comparação em bits
	 * 
	 * @param valorManipulado
	 * @param valorBits
	 * @return valorManipoulado - valorBits caso valorManipulado seja maior ou igual
	 *         ao valorBits
	 *
	 * @author Renahn - 14 de nov de 2017
	 */
	private Integer manipuladoMenorQueValor(Integer valorManipulado, Integer valorBits) {
		if (valorManipulado >= valorBits) {
			octeto = octeto + "1";
			valorManipulado -= valorBits;
		} else {
			octeto = octeto + "0";
		}
		return valorManipulado;
	}

	@Override
	public String toString() {
		return valor.toString();
	}

	public boolean incrementarValor() {
		if (this.valor == 255) {
			this.setValor(0);
			return false;
		}
		this.setValor(this.valor + 1);
		return true;
	}

	/**
	 * Este método converte o octeto do bloco para um valor inteiro.
	 *
	 * @author Renahn - 18 de nov de 2017
	 */
	private void calculaValor() {
		this.valor = new BigInteger(octeto, 2).and(new BigInteger("11111111", 2))
				.intValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((octeto == null) ? 0 : octeto.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Bloco other = (Bloco) obj;
		if (octeto == null) {
			if (other.octeto != null)
				return false;
		} else if (!octeto.equals(other.octeto))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

}
