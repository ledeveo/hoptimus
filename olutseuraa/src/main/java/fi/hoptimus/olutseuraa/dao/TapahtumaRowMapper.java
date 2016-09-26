package fi.hoptimus.olutseuraa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fi.hoptimus.olutseuraa.bean.Tapahtuma;
import fi.hoptimus.olutseuraa.bean.TapahtumaImpl;

public class TapahtumaRowMapper implements RowMapper<Tapahtuma> {

	public Tapahtuma mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tapahtuma t = new TapahtumaImpl();
		t.setId(rs.getInt("id"));
		t.setNimi(rs.getString("nimi"));
		t.setPvm(rs.getString("pvm"));
		t.setAika(rs.getString("aika"));
		t.setPaikka(rs.getString("paikka"));
		t.setTeema(rs.getString("teema"));
		t.setOsallistujat(rs.getInt("osallistujat"));
		t.setIsanta(rs.getString("isanta"));
		t.setKuvaus(rs.getString("kuvaus"));
		
		return t;
	}

}
