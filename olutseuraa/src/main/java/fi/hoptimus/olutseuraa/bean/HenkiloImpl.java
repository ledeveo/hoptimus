package fi.hoptimus.olutseuraa.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;


@Entity
@Table(name = "Henkilo")
public class HenkiloImpl implements Henkilo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@Size(min = 1, max = 255)
	private String etunimi;
	
	@Size(min = 1, max = 255)
	private String sukunimi;
	
	@Size(min = 1, max = 255)
	@Email
	private String sahkoposti;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEtunimi() {
		return etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	public String getSahkoposti() {
		return sahkoposti;
	}

	public void setSahkoposti(String sahkoposti) {
		this.sahkoposti = sahkoposti;
	}

	@Override
	public String toString() {
		return etunimi + " " + sukunimi ;
	}
	
	

}
