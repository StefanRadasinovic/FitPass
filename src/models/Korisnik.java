package models;

import java.util.Comparator;

public class Korisnik {
	private String id;
	private KorisnikUloga uloga;
	private TipKorisnika tip;
	private String ime;
	private String prezime;
	private String korisnickoIme;
	private String lozinka;
	private String pol;
	private String datumRodjenja;
	private int sakupljeniBodovi;
	private String objekatKojimUpravlja;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public KorisnikUloga getUloga() {
		return uloga;
	}
	public void setUloga(KorisnikUloga uloga) {
		this.uloga = uloga;
	}
	public TipKorisnika getTip() {
		return tip;
	}
	public void setTip(TipKorisnika tip) {
		this.tip = tip;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getPol() {
		return pol;
	}
	public void setPol(String pol) {
		this.pol = pol;
	}
	public String getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public int getSakupljeniBodovi() {
		return sakupljeniBodovi;
	}
	public void setSakupljeniBodovi(int sakupljeniBodovi) {
		this.sakupljeniBodovi = sakupljeniBodovi;
	}
	public String getObjekatKojimUpravlja() {
		return objekatKojimUpravlja;
	}
	public void setObejekatKojimUpravlja(String obejekatKojimUpravlja) {
		this.objekatKojimUpravlja = obejekatKojimUpravlja;
	}
	
	public static Comparator<Korisnik> ImeComparator = new Comparator<Korisnik>() {
		public int compare(Korisnik a, Korisnik b) {
			return a.getIme().compareTo(b.getIme());
		}
	};
	
	public static Comparator<Korisnik> ObrnutiImeComparator = new Comparator<Korisnik>() {
		public int compare(Korisnik a, Korisnik b) {
			return b.getIme().compareTo(a.getIme());
		}
	};
	
	public static Comparator<Korisnik> PrezimeComparator = new Comparator<Korisnik>() {
		public int compare(Korisnik a, Korisnik b) {
			return a.getPrezime().compareTo(b.getPrezime());
		}
	};
	
	public static Comparator<Korisnik> ObrnutiPrezimeComparator = new Comparator<Korisnik>() {
		public int compare(Korisnik a, Korisnik b) {
			return b.getPrezime().compareTo(a.getPrezime());
		}
	};
	
	public static Comparator<Korisnik> KorisnickoImeComparator = new Comparator<Korisnik>() {
		public int compare(Korisnik a, Korisnik b) {
			return a.getKorisnickoIme().compareTo(b.getKorisnickoIme());
		}
	};
	
	public static Comparator<Korisnik> ObrnutiKorisnickoImeComparator = new Comparator<Korisnik>() {
		public int compare(Korisnik a, Korisnik b) {
			return b.getKorisnickoIme().compareTo(a.getKorisnickoIme());
		}
	};
	
	public static Comparator<Korisnik> BrojBodovaComparator = new Comparator<Korisnik>() {
		public int compare(Korisnik a, Korisnik b) {
			return Integer.compare(a.getSakupljeniBodovi(), b.getSakupljeniBodovi());
		}
	};
	
	public static Comparator<Korisnik> ObrnutiBrojBodovaComparator = new Comparator<Korisnik>() {
		public int compare(Korisnik a, Korisnik b) {
			return Integer.compare(b.getSakupljeniBodovi(), a.getSakupljeniBodovi());
		}
	};
	
}
