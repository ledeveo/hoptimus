package fi.hoptimus.olutseuraa.bean;

public interface Tapahtuma {

	public abstract int getId();
	public abstract void setId(int id);

	public abstract String getNimi();
	public abstract void setNimi(String nimi);

	public abstract String getPvm();
	public abstract void setPvm(String pvm);
	
	public abstract String getAika();
	public abstract void setAika(String aika);

	public abstract String getPaikka();
	public abstract void setPaikka(String paikka);

	public abstract String getTeema();
	public abstract void setTeema(String teema);
	
	public abstract int getOsallistujat();
	public abstract void setOsallistujat(int osallistujat);
	
	public abstract String getIsanta();
	public abstract void setIsanta(String isanta);
	
	public abstract String getKuvaus();
	public abstract void setKuvaus(String kuvaus);
	
}
