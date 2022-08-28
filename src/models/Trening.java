package models;

public class Trening {
	
	private String id;
	private String naziv;
	private TreningTip tip;
	private int trajanje;
	private String opis;
	private String slika;
	private String datum;
	private int cena;
	private boolean otkazan;
	private String trener;
	private String sportskiObjekat;
	private String kupac;
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
	public TreningTip getTip() {
		return tip;
	}
	public void setTip(TreningTip tip) {
		this.tip = tip;
	}
	public int getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getSlika() {
		return slika;
	}
	public void setSlika(String slika) {
		this.slika = slika;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public boolean isOtkazan() {
		return otkazan;
	}
	public void setOtkazan(boolean otkazan) {
		this.otkazan = otkazan;
	}
	public String getTrener() {
		return trener;
	}
	public void setTrener(String trener) {
		this.trener = trener;
	}
	public String getSportskiObjekat() {
		return sportskiObjekat;
	}
	public void setSportskiObjekat(String sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}
	public String getKupac() {
		return kupac;
	}
	public void setKupac(String kupac) {
		this.kupac = kupac;
	}
	
	
}
