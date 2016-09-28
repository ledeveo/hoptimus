package fi.hoptimus.olutseuraa.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fi.hoptimus.olutseuraa.bean.Henkilo;
import fi.hoptimus.olutseuraa.bean.Tapahtuma;

@Repository
public class TapahtumaDAOSpringJdbcImpl implements TapahtumaDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void talleta(Tapahtuma t) {
		String sql = "INSERT INTO Tapahtuma(nimi, pvm, aika, paikka, teema, osallistujat, isanta, kuvaus) VALUES(?,?,?,?,?,?,?,?)";
		Object[] parametrit = new Object[] { t.getNimi(), t.getPvm(), t.getAika(), t.getPaikka(), t.getTeema(), t.getOsallistujat(), t.getIsanta(), t.getKuvaus()};
		
		jdbcTemplate.update(sql , parametrit);
	}
	
	public List<Tapahtuma> haeKaikki() {
		//etunimi, sukunimi, tapahtumaId FROM Tapahtuma t INNER JOIN Tapahtuman_henkilo th ON t.id = tapahtumaId
		String sql  = "SELECT id, nimi, pvm, aika, paikka, teema, isanta, kuvaus, maxOsallistujamaara from Tapahtuma";
		RowMapper<Tapahtuma> mapper = new TapahtumaRowMapper();
		List<Tapahtuma> tapahtumat = jdbcTemplate.query(sql,mapper);
		return tapahtumat;
	}
	
	public List<Henkilo> haeOsallistujat(){
		String sql = "SELECT etunimi, sukunimi FROM randomOsallistuja WHERE tapahtumaId = 1";
		RowMapper<Henkilo> mapper = new HenkiloRowMapper();
		List<Henkilo> osallistujat = jdbcTemplate.query(sql,mapper);
		return osallistujat;
		
	}

	
	public void paivitaTapahtuma(Tapahtuma t) {
		String sql = "UPDATE Tapahtuma SET nimi=?, pvm=?, aika=?, paikka=?, teema=?, osallistujat=?, isanta=?, kuvaus=? WHERE id=?";
		Object[] parametrit = new Object[] { t.getNimi(), t.getPvm(), t.getAika(), t.getPaikka(), t.getTeema(), t.getOsallistujat(), t.getIsanta(), t.getKuvaus(), t.getId() };
		
		jdbcTemplate.update(sql , parametrit);
	}
	
	public void liityTapahtumaan(String enimi, String snimi, String sposti, String tapahtumaId){
		
		int tapahtumaIdInt = Integer.parseInt(tapahtumaId);
		
		System.out.println("LiityTapahtumaan -metodin saapui: " + enimi + snimi + sposti + tapahtumaId);
		
		String sql = "INSERT INTO randomOsallistuja(etunimi, sukunimi, sahkoposti, tapahtumaid) VALUES(?,?,?,?)";
		Object[] parametrit = new Object[] { enimi, snimi, sposti, tapahtumaIdInt };
		
		jdbcTemplate.update(sql , parametrit);
		System.out.println("Inserted into database!");
	}

	public Tapahtuma haeTapahtuma(int id) {
		String sql = "SELECT id, nimi, pvm, aika, paikka, teema, osallistujat, isanta, kuvaus, maxOsallistujamaara FROM Tapahtuma WHERE id=?";
		Object[] parametrit = new Object[] { id };
		RowMapper<Tapahtuma> mapper = new TapahtumaRowMapper();
		
		Tapahtuma t = jdbcTemplate.queryForObject(sql, parametrit, mapper);
		return t;
	}

}
