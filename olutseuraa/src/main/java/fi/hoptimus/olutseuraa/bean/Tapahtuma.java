package fi.hoptimus.olutseuraa.bean;

import java.sql.Date;
import java.util.List;


public interface Tapahtuma {

	public abstract int getId();
	public abstract void setId(int id);

	public abstract String getNimi();
	public abstract void setNimi(String nimi);

	public abstract Date getPvm();
	public abstract void setPvm(Date pvm);
	
	public abstract String getAika();
	public abstract void setAika(String aika);

	public abstract String getPaikka();
	public abstract void setPaikka(String paikka);

	public abstract String getTeema();
	public abstract void setTeema(String teema);
	
	public abstract List<Henkilo> getOsallistujat();
	public abstract void setOsallistujat(List<Henkilo> osallistujat);
	
	public abstract String getIsanta();
	public abstract void setIsanta(String isanta);
	
	public abstract String getKuvaus();
	public abstract void setKuvaus(String kuvaus);
	
	public abstract int getmaxOsallistujamaara();
	public abstract void setmaxOsallistujamaara(int maxOsallistujamaara);
	
}
