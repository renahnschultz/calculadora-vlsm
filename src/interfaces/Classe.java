package interfaces;

import java.util.List;

import entidades.Bloco;
import entidades.ClasseC;
import entidades.IPv4;
import entidades.Mascara;

public abstract class Classe {

	public abstract Integer getFaixa();

	public abstract Mascara getMascaraPadrao();

	public String aplicarMascaraPadraoEmIp(IPv4 ip) {

		char[] ipBin = ip.ipFormatadoEmOctetos().replaceAll("\\.", "").toCharArray();
		char[] mascaraBin = new ClasseC().getMascaraPadrao().ipFormatadoEmOctetos().replaceAll("\\.", "").toCharArray();
		int contador = 0;
		for (char c : mascaraBin) {
			if (c == '0') {
				ipBin[contador] = '0';
			} else if (ipBin[contador] == '0') {
				ipBin[contador] = '0';
			} else {
				ipBin[contador] = '1';
			}
			contador++;
		}
		return IPv4.adicionarPontosAoBinario(String.valueOf(ipBin));
	}

	public Integer getQuantidadeHostsPossiveis(List<Bloco> blocos) {
		Integer valor = 256;
		for (Bloco bloco : blocos) {
			valor = valor * (256 - bloco.getValor());
		}
		return valor;
	}

}
