package fi.hoptimus.olutseuraa.bean;

import java.sql.Timestamp;

public class PalauteImpl implements Palaute{

	private int id;
	private String palautteenAntaja;
	private String otsikko;
	private String palaute;
	private boolean luettu;
	private String sposti;
	private Timestamp aikaleima;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPalautteenAntaja() {
		return palautteenAntaja;
	}
	public void setPalautteenAntaja(String nimi) {
		this.palautteenAntaja = nimi;
	}
	
	public String getOtsikko() {
		return otsikko;
	}
	public void setOtsikko(String otsikko) {
		this.otsikko = otsikko;
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
	public boolean isLuettu() {
		return luettu;
	}
	public void setLuettu(boolean luettu) {
		this.luettu = luettu;
	}
	
	
	
	
	
}
