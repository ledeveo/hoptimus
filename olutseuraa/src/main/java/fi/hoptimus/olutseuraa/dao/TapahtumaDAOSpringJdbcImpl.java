package fi.hoptimus.olutseuraa.dao;

import java.sql.Connection;
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

		final String nimi, pvm, aika, paikka, teema, isanta, kuvaus;
		final int maxOsallistujamaara;
		nimi = t.getNimi();
		pvm = t.getPvm();
		aika = t.getPaikka();
		paikka = t.getPaikka();
		teema = t.getTeema();
		isanta = t.getIsanta();
		kuvaus = t.getKuvaus();
		maxOsallistujamaara = t.getmaxOsallistujamaara();

		// jdbc pist‰‰ generoidun id:n t‰nne talteen
		KeyHolder idHolder = new GeneratedKeyHolder();

		// suoritetaan p‰ivitys itse m‰‰ritellyll‰ PreparedStatementCreatorilla
		// ja KeyHolderilla
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						new String[] { "id" });
				ps.setString(1, nimi);
				ps.setString(2, pvm);
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
		// kutsujalla pit‰isi olla viittaus samaiseen olioon
		t.setId(idHolder.getKey().intValue());

	}

	public List<Tapahtuma> haeKaikki() {
		// etunimi, sukunimi, tapahtumaId FROM Tapahtuma t INNER JOIN
		// Tapahtuman_henkilo th ON t.id = tapahtumaId
		String sql = "SELECT id, nimi, pvm, aika, paikka, teema, isanta, kuvaus, maxOsallistujamaara from Tapahtuma";
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
		String sql = "SELECT h.etunimi, h.sukunimi, h.sahkoposti, h.id as henkiloId, t.id as tapahtumaId"
				+ " FROM Henkilo h"
				+ " LEFT JOIN tapOsallistuja o ON h.id = o.henkiloId"
				+ " LEFT JOIN Tapahtuma t ON t.id = o.tapahtumaId "
				+ " WHERE o.tapahtumaId = ?";

		RowMapper<Henkilo> mapper = new HenkiloRowMapper();
		Object[] parametrit = new Object[] { tapId };
		List<Henkilo> osallistujat = jdbcTemplate
				.query(sql, parametrit, mapper);

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

		// anonyymi sis‰luokka tarvitsee vakioina v‰litett‰v‰t arvot,
		// jotta roskien keruu onnistuu t‰m‰n metodin suorituksen p‰‰ttyess‰.
		final String etunimi = h.getEtunimi();
		final String sukunimi = h.getSukunimi();
		final String sahkoposti = h.getSahkoposti();

		// jdbc pist‰‰ generoidun id:n t‰nne talteen
		KeyHolder idHolder = new GeneratedKeyHolder();

		// suoritetaan p‰ivitys itse m‰‰ritellyll‰ PreparedStatementCreatorilla
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
		// kutsujalla pit‰isi olla viittaus samaiseen olioon
		h.setId(idHolder.getKey().intValue());
	}

	public void liityTapahtumaan(Henkilo h, String tapahtumaid) {

		final int tapahtumaIdInt = Integer.parseInt(tapahtumaid);
		final int henkId = h.getId();
		final String sql = "INSERT INTO tapOsallistuja(henkiloId, tapahtumaid) VALUES(?,?)";

		// jdbc pist‰‰ generoidun id:n t‰nne talteen
		KeyHolder idHolder = new GeneratedKeyHolder();

		// suoritetaan p‰ivitys itse m‰‰ritellyll‰ PreparedStatementCreatorilla
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

		// asettaa tietokannassa generoidun id:n henkilˆlle
		h.setId(idHolder.getKey().intValue());

		System.out.println("lis‰ttiin k‰ytt‰j‰ " + h.getEtunimi()
				+ " tapahtumaan id:ll‰: " + tapahtumaid);
	}

	public Tapahtuma haeTapahtuma(int id) {
		String sql = "SELECT id, nimi, pvm, aika, paikka, teema, osallistujat, isanta, kuvaus, maxOsallistujamaara FROM Tapahtuma WHERE id=?";
		Object[] parametrit = new Object[] { id };
		RowMapper<Tapahtuma> mapper = new TapahtumaRowMapper();

		Tapahtuma t = jdbcTemplate.queryForObject(sql, parametrit, mapper);
		return t;
	}

}
