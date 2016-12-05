package fi.hoptimus.olutseuraa.dao;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import fi.hoptimus.olutseuraa.bean.Henkilo;
import fi.hoptimus.olutseuraa.bean.Palaute;
import fi.hoptimus.olutseuraa.bean.Tapahtuma;


public interface TapahtumaDAO {

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public abstract void talleta(Tapahtuma tapahtuma);

	public abstract Tapahtuma haeTapahtuma(int id);

	public abstract List<Tapahtuma> haeKaikki();
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public abstract void paivitaTapahtuma(Tapahtuma tapahtuma);
	
	public abstract void liityTapahtumaan(Henkilo h, int eId);
	
	public abstract List<Henkilo> haeAktivoidutOsallistujat(int tapahtumaid);
	
	public abstract Henkilo talleta(Henkilo h);

	public abstract Henkilo haeHenkilo(int id);

	public abstract void paivitaHenkilo(Henkilo oikeahenkilo);

	@PreAuthorize("hasRole('ROLE_USER')")
	public abstract List<Tapahtuma> haeHenkilonTapahtumat(Henkilo h);

	public abstract void luoWebUserTili(Henkilo oikeahenkilo);

	public abstract Henkilo haeHenkilo(String sahkoposti);

	@PreAuthorize("hasRole('ROLE_USER')")
	public abstract void poistaLiittyminen(Henkilo h, int tapahtumaId);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public abstract void poistaTapahtuma(int tapahtumaId);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public abstract void tallennaPalaute(Palaute p);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public abstract List<Palaute> haePalautteet(String kaikki);
}
