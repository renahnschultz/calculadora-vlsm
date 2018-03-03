package entidades;

import interfaces.Classe;

public class ClasseC extends Classe {

	protected String classe = "C";

	@Override
	public Integer getFaixa() {
		return 223;
	}

	@Override
	public Mascara getMascaraPadrao() {
		return new Mascara("255.255.255.0");
	}

	@Override
	public String toString() {
		return classe;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ClasseC other = (ClasseC) obj;
		if (classe == null) {
			if (other.classe != null)
				return false;
		} else if (!classe.equals(other.classe))
			return false;
		return true;
	}

}
