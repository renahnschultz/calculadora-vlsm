package entidades;

import interfaces.Classe;

public class ClasseB extends Classe {

	public String classe = "B";

	@Override
	public Integer getFaixa() {
		return 191;
	}

	@Override
	public Mascara getMascaraPadrao() {
		return new Mascara("255.255.0.0");
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
		ClasseB other = (ClasseB) obj;
		if (classe == null) {
			if (other.getClasse() != null)
				return false;
		} else if (!classe.equals(other.getClasse()))
			return false;
		return true;
	}

}
