package entidades;

public class Subnet {

	private Integer posicao;
	private IPv4 idRede;
	private IPv4 broadcast;

	/**
	 * Este método realiza as iterações e verificações necessárias para adição da
	 * quantidade necessária de hosts a partir do ip base.
	 * 
	 * @param ipBase
	 * @param qtdHosts
	 *
	 * @author Renahn - 17 de nov de 2017
	 */
	public void adicionaHosts(IPv4 ipBase, Integer qtdHosts) {
		this.idRede = ipBase;
		ipBase = new IPv4(ipBase.ipFormatado());
		for (int i = 0; i < qtdHosts; i++) {
			ipBase = ipBase.proximoIp();
		}
		this.broadcast = ipBase;
	}

	public IPv4 getIdRede() {
		return idRede;
	}

	public void setIdRede(IPv4 idRede) {
		this.idRede = idRede;
	}

	public IPv4 getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(IPv4 broadcast) {
		this.broadcast = broadcast;
	}

	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}

	public String getBroadcastOcteto() {
		return broadcast.ipFormatadoEmOctetos();
	}

	public String getIdRedeOcteto() {
		return idRede.ipFormatadoEmOctetos();
	}

}
