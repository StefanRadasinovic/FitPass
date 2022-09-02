package dto.trening;

import java.util.Comparator;

import models.Korisnik;
import models.SportskiObjekat;
import models.Trening;

public class TreningProsireno {
	private Trening trening;
	private String trener;
	private SportskiObjekat sportskiObjekat;
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
	
	public String getTrener() {
		return trener;
	}
	public void setTrener(String trener) {
		this.trener = trener;
	}



	public static Comparator<TreningProsireno> NazivSportskogObjekaComparator = new Comparator<TreningProsireno>() {
		public int compare(TreningProsireno a, TreningProsireno b) {
			return a.getSportskiObjekat().getNaziv().compareTo(b.getSportskiObjekat().getNaziv());
		}
	};
	
	public static Comparator<TreningProsireno> ObrnutiNazivSportskogObjekaComparator = new Comparator<TreningProsireno>() {
		public int compare(TreningProsireno a, TreningProsireno b) {
			return b.getSportskiObjekat().getNaziv().compareTo(a.getSportskiObjekat().getNaziv());
		}
	};
	
	public static Comparator<TreningProsireno> TipTreningaComparator = new Comparator<TreningProsireno>() {
		public int compare(TreningProsireno a, TreningProsireno b) {
			return a.getTrening().getTip().compareTo(b.getTrening().getTip());
		}
	};
	
	public static Comparator<TreningProsireno> ObrnutiTipTreningaComparator = new Comparator<TreningProsireno>() {
		public int compare(TreningProsireno a, TreningProsireno b) {
			return b.getTrening().getTip().compareTo(a.getTrening().getTip());
		}
	};
	
}
