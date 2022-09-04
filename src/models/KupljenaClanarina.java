package models;

public class KupljenaClanarina {
	private String id;
	private double cena;
	private TipClanarina tip;
	private StatusClanarina status;
	private String datumPlacanja;
	private String datumVazenja;
	private int terminaUkupno;
	private int terminaOstalo;
	private String kupac;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public TipClanarina getTip() {
		return tip;
	}
	public void setTip(TipClanarina tip) {
		this.tip = tip;
	}
	public StatusClanarina getStatus() {
		return status;
	}
	public void setStatus(StatusClanarina status) {
		this.status = status;
	}
	public String getDatumPlacanja() {
		return datumPlacanja;
	}
	public void setDatumPlacanja(String datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}
	public String getDatumVazenja() {
		return datumVazenja;
	}
	public void setDatumVazenja(String datumVazenja) {
		this.datumVazenja = datumVazenja;
	}
	public int getTerminaUkupno() {
		return terminaUkupno;
	}
	public void setTerminaUkupno(int terminaUkupno) {
		this.terminaUkupno = terminaUkupno;
	}
	public int getTerminaOstalo() {
		return terminaOstalo;
	}
	public void setTerminaOstalo(int terminaOstalo) {
		this.terminaOstalo = terminaOstalo;
	}
	public String getKupac() {
		return kupac;
	}
	public void setKupac(String kupac) {
		this.kupac = kupac;
	}
	
	
}
