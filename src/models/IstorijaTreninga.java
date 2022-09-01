package models;

public class IstorijaTreninga {
	private String id;
	private String datumIVremePrijave;
	private String kupac;
	private String trening;
	private String sportskiObjekat;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDatumIVremePrijave() {
		return datumIVremePrijave;
	}
	public void setDatumIVremePrijave(String datumIVremePrijave) {
		this.datumIVremePrijave = datumIVremePrijave;
	}
	public String getKupac() {
		return kupac;
	}
	public void setKupac(String kupac) {
		this.kupac = kupac;
	}
	public String getTrening() {
		return trening;
	}
	public void setTrening(String trening) {
		this.trening = trening;
	}
	public String getSportskiObjekat() {
		return sportskiObjekat;
	}
	public void setSportskiObjekat(String sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}
	
}
