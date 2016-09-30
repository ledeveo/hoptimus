package fi.hoptimus.olutseuraa.dao;

import java.util.List;

import fi.hoptimus.olutseuraa.bean.Henkilo;
import fi.hoptimus.olutseuraa.bean.Tapahtuma;


public interface TapahtumaDAO {

	public abstract void talleta(Tapahtuma tapahtuma);

	public abstract Tapahtuma haeTapahtuma(int id);

	public abstract List<Tapahtuma> haeKaikki();
	
	public abstract void paivitaTapahtuma(Tapahtuma tapahtuma);
	
	public abstract void liityTapahtumaan(Henkilo h, String tapahtumaid);
	
	public abstract List<Henkilo> haeOsallistujat(int tapahtumaid);
	
	public abstract Henkilo talleta(Henkilo h);
}
