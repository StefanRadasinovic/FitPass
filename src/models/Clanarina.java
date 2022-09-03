package models;

public class Clanarina {
	
	private String id;
	private String naziv;
	private TipClanarina tip;
	private int cena;
	private int brojTermina;
	private int kolikoDanaVazi;
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
	public TipClanarina getTip() {
		return tip;
	}
	public void setTip(TipClanarina tip) {
		this.tip = tip;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public int getBrojTermina() {
		return brojTermina;
	}
	public void setBrojTermina(int brojTermina) {
		this.brojTermina = brojTermina;
	}
	public int getKolikoDanaVazi() {
		return kolikoDanaVazi;
	}
	public void setKolikoDanaVazi(int kolikoDanaVazi) {
		this.kolikoDanaVazi = kolikoDanaVazi;
	}
	
	
	
}
