package fi.hoptimus.olutseuraa.bean;

import java.sql.Timestamp;

public class PalauteImpl implements Palaute{

	private int id;
	private String palautteen_antaja;
	private String palaute;
	private String sposti;
	private Timestamp aikaleima;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPalautteenAntaja() {
		return palautteen_antaja;
	}
	public void setPalautteenAntaja(String nimi) {
		this.palautteen_antaja = nimi;
	}
	public String getPalaute() {
		return palaute;
	}
	public void setPalaute(String palaute) {
		this.palaute = palaute;
	}
	public String getSposti() {
		return sposti;
	}
	public void setSposti(String sposti) {
		this.sposti = sposti;
	}
	public Timestamp getAikaleima() {
		return aikaleima;
	}
	public void setAikaleima(Timestamp aikaleima) {
		this.aikaleima = aikaleima;
	}
	
	
	
}
