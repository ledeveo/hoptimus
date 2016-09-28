package fi.hoptimus.olutseuraa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fi.hoptimus.olutseuraa.bean.Henkilo;
import fi.hoptimus.olutseuraa.bean.HenkiloImpl;

public class HenkiloRowMapper implements RowMapper<Henkilo>{


	public Henkilo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Henkilo t = new HenkiloImpl();
		t.setEtunimi(rs.getString("etunimi"));
		t.setSukunimi(rs.getString("sukunimi"));		
		
		return t;
	}

}
	
