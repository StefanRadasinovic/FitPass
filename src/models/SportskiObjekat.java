package models;

import java.util.Comparator;

public class SportskiObjekat {
	
	private String id;
	private String naziv;
	private TipObjekta tipObjekta;
	private String sadrzaj = "";
	private StatusObjekta status;
	private Lokacija lokacija;
	private float prosecnaOcena = 0;
	private String radnoVreme = "";
	private String logo;
	private boolean obrisan;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public TipObjekta getTipObjekta() {
		return tipObjekta;
	}
	public void setTipObjekta(TipObjekta tipObjekta) {
		this.tipObjekta = tipObjekta;
	}
	public String getSadrzaj() {
		return sadrzaj;
	}
	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
	public StatusObjekta getStatus() {
		return status;
	}
	public void setStatus(StatusObjekta status) {
		this.status = status;
	}
	public Lokacija getLokacija() {
		return lokacija;
	}
	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}
	public float getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(float prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	public String getRadnoVreme() {
		return radnoVreme;
	}
	public void setRadnoVreme(String radnoVreme) {
		this.radnoVreme = radnoVreme;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
	public boolean isObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}


	public static Comparator<SportskiObjekat> NazivComparator = new Comparator<SportskiObjekat>() {
		public int compare(SportskiObjekat a, SportskiObjekat b) {
			return a.getNaziv().compareTo(b.getNaziv());
		}
	};
	
	public static Comparator<SportskiObjekat> ObrnutiNazivComparator = new Comparator<SportskiObjekat>() {
		public int compare(SportskiObjekat a, SportskiObjekat b) {
			return b.getNaziv().compareTo(a.getNaziv());
		}
	};
	
	public static Comparator<SportskiObjekat> LokacijaComparator = new Comparator<SportskiObjekat>() {
		public int compare(SportskiObjekat a, SportskiObjekat b) {
			return a.getLokacija().getAdresa().compareTo(b.getLokacija().getAdresa());
		}
	};
	
	public static Comparator<SportskiObjekat> ObrnutiLokacijaComparator = new Comparator<SportskiObjekat>() {
		public int compare(SportskiObjekat a, SportskiObjekat b) {
			return b.getLokacija().getAdresa().compareTo(a.getLokacija().getAdresa());
		}
	};
	
	public static Comparator<SportskiObjekat> ProsecnaOcenaComparator = new Comparator<SportskiObjekat>() {
		public int compare(SportskiObjekat a, SportskiObjekat b) {
			return Float.compare(a.getProsecnaOcena(), b.getProsecnaOcena());
		}
	};
	
	public static Comparator<SportskiObjekat> ObrnutiProsecnaOcenaComparator = new Comparator<SportskiObjekat>() {
		public int compare(SportskiObjekat a, SportskiObjekat b) {
			return Float.compare(b.getProsecnaOcena(), a.getProsecnaOcena());
		}
	};
}
