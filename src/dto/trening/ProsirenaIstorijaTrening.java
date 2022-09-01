package dto.trening;

import java.util.Comparator;

import models.SportskiObjekat;
import models.Trening;

public class ProsirenaIstorijaTrening {
	private String id;
	private String datum;
	private Trening trening;
	private SportskiObjekat sportskiObjekat;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public Trening getTrening() {
		return trening;
	}
	public void setTrening(Trening trening) {
		this.trening = trening;
	}
	public SportskiObjekat getSportskiObjekat() {
		return sportskiObjekat;
	}
	public void setSportskiObjekat(SportskiObjekat sportskiObjekat) {
		this.sportskiObjekat = sportskiObjekat;
	}
	
	public static Comparator<ProsirenaIstorijaTrening> NazivSportskogObjekaComparator = new Comparator<ProsirenaIstorijaTrening>() {
		public int compare(ProsirenaIstorijaTrening a, ProsirenaIstorijaTrening b) {
			return a.getSportskiObjekat().getNaziv().compareTo(b.getSportskiObjekat().getNaziv());
		}
	};
	
	public static Comparator<ProsirenaIstorijaTrening> ObrnutiNazivSportskogObjekaComparator = new Comparator<ProsirenaIstorijaTrening>() {
		public int compare(ProsirenaIstorijaTrening a, ProsirenaIstorijaTrening b) {
			return b.getSportskiObjekat().getNaziv().compareTo(a.getSportskiObjekat().getNaziv());
		}
	};
	
	public static Comparator<ProsirenaIstorijaTrening> TipTreningaComparator = new Comparator<ProsirenaIstorijaTrening>() {
		public int compare(ProsirenaIstorijaTrening a, ProsirenaIstorijaTrening b) {
			return a.getTrening().getTip().compareTo(b.getTrening().getTip());
		}
	};
	
	public static Comparator<ProsirenaIstorijaTrening> ObrnutiTipTreningaComparator = new Comparator<ProsirenaIstorijaTrening>() {
		public int compare(ProsirenaIstorijaTrening a, ProsirenaIstorijaTrening b) {
			return b.getTrening().getTip().compareTo(a.getTrening().getTip());
		}
	};
	
}
