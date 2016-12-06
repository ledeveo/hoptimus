package fi.hoptimus.olutseuraa.bean;

import java.sql.Timestamp;

public interface Palaute {

		public abstract int getId();		
		public void setId(int id);
		
		public abstract String getPalautteenAntaja();
		public void setPalautteenAntaja(String nimi);
		
		public abstract String getPalaute();
		public void setPalaute(String palaute);
		
		public abstract String getSposti();
		public void setSposti(String sposti);
		
		public abstract Timestamp getAikaleima();
		public void setAikaleima(Timestamp aikaleima);	
		
		public abstract String getOtsikko();
		public void setOtsikko(String otsikko);
		
	}


