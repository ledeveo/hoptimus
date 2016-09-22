package olutseuraa.dao;

import java.util.List;

import olutseuraa.bean.Tapahtuma;


public interface TapahtumaDAO {

	public abstract void talleta(Tapahtuma tapahtuma);

	//public abstract Tapahtuma etsi(int id);

	public abstract List<Tapahtuma> haeKaikki();
}
