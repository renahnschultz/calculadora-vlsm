package calculos;

import java.util.ArrayList;
import java.util.List;

import entidades.ClasseA;
import entidades.ClasseB;
import entidades.ClasseC;
import entidades.IPv4;
import entidades.Mascara;
import entidades.QuantidadeDeHostsNecessaria;
import entidades.Subnet;
import interfaces.Classe;

public class CalculaClassLess {

	private IPv4 ipBase;
	private IPv4 idRedePorSubnet;
	private Mascara mascaraCustomizada;
	private List<Subnet> subnets;

	private Classe classeDaRede;

	public void calcular(IPv4 ip, List<QuantidadeDeHostsNecessaria> hosts, Integer qtdHostsNecessaria) {
		this.ipBase = ip;
		this.idRedePorSubnet = new IPv4(ip.ipFormatado());

		// String mascaraCustomizada = descobreMascaraCustomizada(maiorPotencia, ip);
		// this.mascaraCustomizada = new Mascara(mascaraCustomizada);
		if (qtdHostsNecessaria <= 256) {
			classeDaRede = new ClasseC();
		} else if (qtdHostsNecessaria <= 256 * 256) {
			classeDaRede = new ClasseB();
		} else if (qtdHostsNecessaria <= 256 * 256 * 256) {
			classeDaRede = new ClasseA();
		}
		subnets = new ArrayList<Subnet>();
		calculasSubnetsPossiveis(hosts, qtdHostsNecessaria);

	}

	/**
	 * Calcula todas as subnets possíveis.
	 * 
	 * @param qtdHostsNecessaria
	 *
	 * @author Renahn - 18 de nov de 2017
	 */
	private void calculasSubnetsPossiveis(List<QuantidadeDeHostsNecessaria> hosts, Integer qtdHostsNecessaria) {
		// Integer quantidadeHostsPossiveis = ipBase.calculaQuantidadeHostsPossiveis();
		// Integer quantidadeSubnets = quantidadeHostsPossiveis / qtdHostsNecessaria;

		for (QuantidadeDeHostsNecessaria qtdHosts : hosts) {
			Subnet subnet = new Subnet();
			subnet.setPosicao(hosts.indexOf(qtdHosts));
			subnet.adicionaHosts(new IPv4(idRedePorSubnet.ipFormatado()), qtdHosts.getProximaPotencia() - 1);
			if (hosts.indexOf(qtdHosts) != hosts.size() - 1) {
				idRedePorSubnet = new IPv4(subnet.getBroadcast().ipFormatado()).proximoIp();
			}
			subnets.add(subnet);
		}

	}

	private String descobreMascaraCustomizada(Integer maiorPotencia, IPv4 ip) {
		char[] mascaraPadrao = mascaraEmOctetoToCharArray(ip);
		char[] mascaraCustomizada = mascaraEmOctetoToCharArray(ip);
		int casasRestantes = 8 - qualPotenciaDeDois(maiorPotencia);
		int contador = 0;
		for (char c : mascaraPadrao) {
			if (c == '0' && casasRestantes > 0) {
				mascaraCustomizada[contador] = '1';
				casasRestantes--;
			}
			contador++;
		}
		return IPv4.adicionarPontosAoBinario(String.valueOf(mascaraCustomizada));
	}

	/**
	 * Remove os pontos da mascara e transforma em char array.
	 * 
	 * @param ip
	 * @return
	 *
	 * @author Renahn - 18 de nov de 2017
	 */
	private char[] mascaraEmOctetoToCharArray(IPv4 ip) {
		return ip.getClasse().getMascaraPadrao().ipFormatadoEmOctetos().replaceAll("\\.", "").toCharArray();
	}

	private static int qualPotenciaDeDois(Integer qtdHosts) {
		return qtdHosts == 0 ? 0 : 32 - Integer.numberOfLeadingZeros(qtdHosts - 1);
	}

	public IPv4 getIpBase() {
		return ipBase;
	}

	public void setIpBase(IPv4 ipBase) {
		this.ipBase = ipBase;
	}

	public IPv4 getIdRedePorSubnet() {
		return idRedePorSubnet;
	}

	public void setIdRedePorSubnet(IPv4 idRedePorSubnet) {
		this.idRedePorSubnet = idRedePorSubnet;
	}

	public Mascara getMascaraCustomizada() {
		return mascaraCustomizada;
	}

	public void setMascaraCustomizada(Mascara mascaraCustomizada) {
		this.mascaraCustomizada = mascaraCustomizada;
	}

	public List<Subnet> getSubnets() {
		return subnets;
	}

	public void setSubnets(List<Subnet> subnets) {
		this.subnets = subnets;
	}

	public Classe getClasseDaRede() {
		return classeDaRede;
	}

	public void setClasseDaRede(Classe classeDaRede) {
		this.classeDaRede = classeDaRede;
	}

}
