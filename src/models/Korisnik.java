package models;


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
	private String obejekatKojimUpravlja;
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
	public String getObejekatKojimUpravlja() {
		return obejekatKojimUpravlja;
	}
	public void setObejekatKojimUpravlja(String obejekatKojimUpravlja) {
		this.obejekatKojimUpravlja = obejekatKojimUpravlja;
	}
	
}
