package dto.trening;

public class IstorijaDTO {
	private String id;
	private String datum;
	private String trening;
	private String sportskiObjekat;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTrening() {
		return trening;
	}
	public void setTrening(String trening) {
		this.trening = trening;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getSportskiObjekat() {
		return sportskiObjekat;
	}
	public void setSportskiObjekat(String sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}
	
}
