package olutseuraa.dao;

import java.util.List;

import olutseuraa.bean.Tapahtuma;


public interface TapahtumaDAO {

	public abstract void talleta(Tapahtuma tapahtuma);

	public abstract Tapahtuma haeTapahtuma(int id);
	
	public abstract void paivitaOsallistujat(int tapahtumaid, int osallistujat);

	public abstract List<Tapahtuma> haeKaikki();
	
	
}
