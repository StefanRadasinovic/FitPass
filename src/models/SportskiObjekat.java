package models;


public class SportskiObjekat {
	
	private String id;
	private String naziv;
	private TipObjekta tipObjekta;
	private String sadrzaj;
	private StatusObjekta status;
	private Lokacija lokacija;
	private float prosecnaOcena;
	private String radnoVreme;
	private String logo;
	
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
	
}
