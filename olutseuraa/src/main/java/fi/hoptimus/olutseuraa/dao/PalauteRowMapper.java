package fi.hoptimus.olutseuraa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fi.hoptimus.olutseuraa.bean.Palaute;
import fi.hoptimus.olutseuraa.bean.PalauteImpl;


public class PalauteRowMapper implements RowMapper<Palaute> {

	public Palaute mapRow(ResultSet rs, int rowNum) throws SQLException {
		Palaute p = new PalauteImpl();
		
		p.setId(rs.getInt("id"));
		p.setNimi(rs.getString("nimi"));
		p.setPalaute(rs.getString("palaute"));
		p.setAikaleima(rs.getTimestamp("aikaleima"));
		p.setSposti(rs.getString("sposti"));
		
		return p;
	}
}
