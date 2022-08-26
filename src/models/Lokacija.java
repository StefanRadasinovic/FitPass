package models;

public class Lokacija {
	
	private String adresa;
	
	public Lokacija( ) {}
	
	public Lokacija(String adresa) {
		super();
		this.adresa = adresa;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
}
