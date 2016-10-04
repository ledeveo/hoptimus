package fi.hoptimus.olutseuraa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fi.hoptimus.olutseuraa.bean.Henkilo;
import fi.hoptimus.olutseuraa.bean.HenkiloImpl;

public class HenkiloRowMapper implements RowMapper<Henkilo>{


	public Henkilo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Henkilo h = new HenkiloImpl();
		h.setEtunimi(rs.getString("h.etunimi"));
		h.setSukunimi(rs.getString("h.sukunimi"));
		h.setSahkoposti(rs.getString("h.sahkoposti"));		
		h.setId(rs.getInt("henkiloId"));
		return h;
	}

}
	
