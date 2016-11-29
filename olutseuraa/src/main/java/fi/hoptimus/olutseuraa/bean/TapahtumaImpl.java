package fi.hoptimus.olutseuraa.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



@Entity
@Table(name = "Tapahtuma")
public class TapahtumaImpl implements Tapahtuma {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Size(min = 1, max = 255)
	private String nimi;
	private Date pvm;
	private String aika;
	@Size(min = 1, max = 255)
	private String paikka;
	@Size(min = 1, max = 255)
	private String teema;
	private List<Henkilo> osallistujat;
	private int maxOsallistujamaara;
	@Size(min = 1, max = 255)
	private String isanta;
	@Size(min = 1, max = 255)
	private String kuvaus;
	private int osallistujamaara;

	public int getOsallistujamaara() {
		return osallistujamaara;
	}

	public void setOsallistujamaara(int osallistujamaara) {
		this.osallistujamaara = osallistujamaara;
	}

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

	public Date getPvm() {
		return pvm;
	}

	public void setPvm(Date pvm) {
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

	public List<Henkilo> getOsallistujat() {
		return osallistujat;
	}

	public void setOsallistujat(List<Henkilo> osallistujat) {
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

	public int getmaxOsallistujamaara() {
		return maxOsallistujamaara;
	}

	public void setmaxOsallistujamaara(int maxOsallistujamaara) {
		this.maxOsallistujamaara = maxOsallistujamaara;
	}	

}
