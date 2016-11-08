package fi.hoptimus.olutseuraa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

		final String sql = "INSERT INTO Tapahtuma(nimi, pvm, aika, paikka, teema, isanta, kuvaus, maxOsallistujamaara) VALUES(?,?,?,?,?,?,?,?)";

		final String nimi, aika, paikka, teema, isanta, kuvaus;
		final Date pvm;
		final int maxOsallistujamaara;
		nimi = t.getNimi();
		pvm = t.getPvm();
		aika = t.getAika();
		paikka = t.getPaikka();
		teema = t.getTeema();
		isanta = t.getIsanta();
		kuvaus = t.getKuvaus();
		maxOsallistujamaara = t.getmaxOsallistujamaara();

		// jdbc pist�� generoidun id:n t�nne talteen
		KeyHolder idHolder = new GeneratedKeyHolder();

		// suoritetaan p�ivitys itse m��ritellyll� PreparedStatementCreatorilla
		// ja KeyHolderilla
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						new String[] { "id" });
				ps.setString(1, nimi);
				ps.setDate(2, pvm);
				ps.setString(3, aika);
				ps.setString(4, paikka);
				ps.setString(5, teema);
				ps.setString(6, isanta);
				ps.setString(7, kuvaus);
				ps.setInt(8, maxOsallistujamaara);
				return ps;
			}
		}, idHolder);

		// tallennetaan id takaisin beaniin, koska
		// kutsujalla pit�isi olla viittaus samaiseen olioon
		t.setId(idHolder.getKey().intValue());

	}

	public List<Tapahtuma> haeKaikki() {
		// etunimi, sukunimi, tapahtumaId FROM Tapahtuma t INNER JOIN
		// Tapahtuman_henkilo th ON t.id = tapahtumaId	
		//WHERE pvm >= CURDATE()AND aika > CURTIME()
		
		
		String sql = "SELECT id, nimi, pvm, aika, paikka, teema, isanta, kuvaus, maxOsallistujamaara from Tapahtuma WHERE pvm >= CURDATE() ORDER BY pvm ASC";
		RowMapper<Tapahtuma> mapper = new TapahtumaRowMapper();
		List<Tapahtuma> tapahtumat = jdbcTemplate.query(sql, mapper);

		// aseta osallistujat tapahtumiin
		for (int i = 0; i < tapahtumat.size(); i++) {
			List<Henkilo> osallistujat = haeOsallistujat(tapahtumat.get(i)
					.getId());
			tapahtumat.get(i).setOsallistujat(osallistujat);
		}

		return tapahtumat;
	}

	public List<Henkilo> haeOsallistujat(int tapId) {
		// hakee tapahtuman kaikki osallistujat
		String sql = "SELECT h.etunimi, h.sukunimi, h.sahkoposti, h.id as henkiloId, h.aktivoitu, t.id as tapahtumaId"
				+ " FROM Henkilo h"
				+ " LEFT JOIN tapOsallistuja o ON h.id = o.henkiloId"
				+ " LEFT JOIN Tapahtuma t ON t.id = o.tapahtumaId "
				+ " WHERE o.tapahtumaId = ?";

		RowMapper<Henkilo> mapper = new HenkiloRowMapper();
		Object[] parametrit = new Object[] { tapId };
		List<Henkilo> osallistujat = jdbcTemplate.query(sql, parametrit, mapper);

		return osallistujat;

	}

	public void paivitaTapahtuma(Tapahtuma t) {
		String sql = "UPDATE Tapahtuma SET nimi=?, pvm=?, aika=?, paikka=?, teema=?, osallistujat=?, isanta=?, kuvaus=? WHERE id=?";
		Object[] parametrit = new Object[] { t.getNimi(), t.getPvm(),
				t.getAika(), t.getPaikka(), t.getTeema(), t.getOsallistujat(),
				t.getIsanta(), t.getKuvaus(), t.getId() };

		jdbcTemplate.update(sql, parametrit);
	}

	public void talleta(Henkilo h) {
		final String sql = "insert into Henkilo(etunimi, sukunimi, sahkoposti) values(?,?,?)";

		// anonyymi sis�luokka tarvitsee vakioina v�litett�v�t arvot,
		// jotta roskien keruu onnistuu t�m�n metodin suorituksen p��ttyess�.
		final String etunimi = h.getEtunimi();
		final String sukunimi = h.getSukunimi();
		final String sahkoposti = h.getSahkoposti();

		// jdbc pist�� generoidun id:n t�nne talteen
		KeyHolder idHolder = new GeneratedKeyHolder();

		// suoritetaan p�ivitys itse m��ritellyll� PreparedStatementCreatorilla
		// ja KeyHolderilla
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						new String[] { "id" });
				ps.setString(1, etunimi);
				ps.setString(2, sukunimi);
				ps.setString(3, sahkoposti);
				return ps;
			}
		}, idHolder);

		// tallennetaan id takaisin beaniin, koska
		// kutsujalla pit�isi olla viittaus samaiseen olioon
		h.setId(idHolder.getKey().intValue());
	}

	public void liityTapahtumaan(Henkilo h, String tapahtumaid) {

		final int tapahtumaIdInt = Integer.parseInt(tapahtumaid);
		final int henkId = h.getId();
		final String sql = "INSERT INTO tapOsallistuja(henkiloId, tapahtumaid) VALUES(?,?)";

		// jdbc pist�� generoidun id:n t�nne talteen
		KeyHolder idHolder = new GeneratedKeyHolder();

		// suoritetaan p�ivitys itse m��ritellyll� PreparedStatementCreatorilla
		// ja KeyHolderilla
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						new String[] { "id" });
				ps.setInt(1, henkId);
				ps.setInt(2, tapahtumaIdInt);
				return ps;
			}
		}, idHolder);

		// asettaa tietokannassa generoidun id:n henkil�lle
		h.setId(idHolder.getKey().intValue());

		System.out.println("lis�ttiin k�ytt�j� " + h.getEtunimi()
				+ " tapahtumaan id:ll�: " + tapahtumaid);
	}

	public Tapahtuma haeTapahtuma(int id) {
		String sql = "SELECT id, nimi, pvm, aika, paikka, teema, osallistujat, isanta, kuvaus, maxOsallistujamaara FROM Tapahtuma WHERE id=?";
		Object[] parametrit = new Object[] { id };
		RowMapper<Tapahtuma> mapper = new TapahtumaRowMapper();

		Tapahtuma t = jdbcTemplate.queryForObject(sql, parametrit, mapper);
		return t;
	}

	public Henkilo haeHenkilo(int id) {
		String sql = "SELECT h.id AS henkiloId, h.etunimi, h.sukunimi, h.sahkoposti, h.aktivoitu FROM Henkilo h WHERE h.id=?";
		Object[] parametrit = new Object[] { id };
		RowMapper<Henkilo> mapper = new HenkiloRowMapper();
		
		Henkilo h = jdbcTemplate.queryForObject(sql, parametrit, mapper);
		return h;
	}

	public void paivitaHenkilo(Henkilo h) {
		String sql = "UPDATE Henkilo SET etunimi=?,sukunimi=?,sahkoposti=?,aktivoitu=?,salasana=? WHERE id=?";
		Object[] parametrit = new Object[] { h.getEtunimi(), h.getSukunimi(), h.getSahkoposti(), h.isAktivoitu(), h.getSalasana(), h.getId()};
		
		jdbcTemplate.update(sql, parametrit);
	}

	public List<Tapahtuma> haeHenkilonTapahtumat(Henkilo h) {
		String sql = "SELECT Tapahtuma.id, nimi, pvm, aika, paikka, teema, osallistujat, isanta, kuvaus, maxOsallistujamaara "
				+" FROM Tapahtuma LEFT JOIN tapOsallistuja o ON Tapahtuma.id = o.tapahtumaId WHERE o.henkiloId=?";
		RowMapper<Tapahtuma> mapper = new TapahtumaRowMapper();
		Object[] parametrit = new Object[] { h.getId() };
		List<Tapahtuma> tapahtumat = jdbcTemplate.query(sql, parametrit, mapper);
		
		return tapahtumat;
	}

	public void luoWebUserTili(Henkilo h) {
		
		//tarkista että onko jo olemassa samainen webuser-tili
		String sql1 = "SELECT username FROM webuser2 WHERE username=?";
		Object[] parametrit = new Object[] { h.getSahkoposti() };
		List<String> webuserit = jdbcTemplate.queryForList(sql1, parametrit, String.class); 

		if (webuserit.size() > 0) {
			//webuser löytyi, ei tehdä mitään.
		} else {
			//jos ei löydy kyseistä webuseria voidaan jatkaa
			
			//talleta webuseri tietokantaan
			final String sql2 = "INSERT INTO webuser2 (username, password_encrypted, enabled, firstname, lastname) VALUES (?,?,?,?,?)";
			final String username, password, firstname, lastname;
			final boolean enabled;
			
			username = h.getSahkoposti();
			password = h.getSalasana();
			firstname = h.getEtunimi();
			lastname = h.getSukunimi();
			enabled = h.isAktivoitu();
			
			// jdbc pist�� generoidun id:n t�nne talteen
			KeyHolder idHolder = new GeneratedKeyHolder();
	
			// suoritetaan p�ivitys itse m��ritellyll� PreparedStatementCreatorilla
			// ja KeyHolderilla
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql2,
							new String[] { "id" });
					ps.setString(1, username);
					ps.setString(2, password);
					ps.setBoolean(3, enabled);
					ps.setString(4, firstname);
					ps.setString(5, lastname);
					return ps;
				}
			}, idHolder);
	
			// haetaan saatu id.
			int id = idHolder.getKey().intValue();
			
			//lisää rooli(user = 1) webuserille tietokantaan
			String sql3 = "INSERT INTO webuser2_authority (webuser2_id, authority_id) VALUES (?,1)";
			Object[] parametrit2 = new Object[] { id };
			jdbcTemplate.update(sql3, parametrit2);
		}
	}

}
