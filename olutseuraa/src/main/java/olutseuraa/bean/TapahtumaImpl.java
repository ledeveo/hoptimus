package olutseuraa.bean;

public class TapahtumaImpl implements Tapahtuma {

	private int id;
	private String nimi;
	private String pvm;
	private String aika;
	private String paikka;
	private String teema;
	private int osallistujat;
	private String isanta;
	private String kuvaus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getPvm() {
		return pvm;
	}

	public void setPvm(String pvm) {
		this.pvm = pvm;
	}

	public String getAika() {
		return aika;
	}

	public void setAika(String aika) {
		this.aika = aika;
	}

	public String getPaikka() {
		return paikka;
	}

	public void setPaikka(String paikka) {
		this.paikka = paikka;
	}

	public String getTeema() {
		return teema;
	}

	public void setTeema(String teema) {
		this.teema = teema;
	}

	public int getOsallistujat() {
		return osallistujat;
	}

	public void setOsallistujat(int osallistujat) {
		this.osallistujat = osallistujat;
	}

	public String getIsanta() {
		return isanta;
	}

	public void setIsanta(String isanta) {
		this.isanta = isanta;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

}
