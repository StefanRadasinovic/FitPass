package services;

import java.util.ArrayList;
import java.util.List;

import data.DataManager;
import models.IstorijaTreninga;
import models.Trening;
import models.TreningTip;

public class TreningService {
	
	public List<Trening> sviTreninzi() {
		return DataManager.data.getTreninzi();
	}
	
	public List<Trening> getTrenziZaKorisnika(String korisnikId) {
		List<Trening> treninzi = new ArrayList<Trening>();
		for (Trening tr : sviTreninzi()) {
			if (tr.getTrener().equals(korisnikId)) {
				treninzi.add(tr);
			}
		}
		
		return treninzi;
	}
	
	public List<Trening> getTrenziZaObjekat(String objekatId) {
		List<Trening> treninzi = new ArrayList<Trening>();
		for (Trening tr : sviTreninzi()) {
			if (tr.getSportskiObjekat().equals(objekatId)) {
				treninzi.add(tr);
			}
		}
		
		return treninzi;
	}
	
	public Trening getPoId(String treningId) {
		for (Trening tr : sviTreninzi()) {
			if (tr.getId().equals(treningId)) {
				return tr;
			}
		}
		
		return null;
	}
	
	public void azuriraj(Trening trening) {
		for (Trening tr : sviTreninzi()) {
			if (tr.getId().equals(trening.getId())) {
				tr.setCena(trening.getCena());
				tr.setTrajanje(trening.getTrajanje());
				tr.setDatum(trening.getDatum());
				tr.setKupac(trening.getKupac());
				tr.setNaziv(trening.getNaziv());
				tr.setOpis(trening.getOpis());
				tr.setOtkazan(trening.isOtkazan());
				tr.setSlika(trening.getSlika());
				tr.setSportskiObjekat(tr.getSportskiObjekat());
				tr.setTip(trening.getTip());
				tr.setTrener(tr.getTrener());
			}
		}
		DataManager.saveData();
	}
	
	public void sacuvajNovi(Trening trening) {
		DataManager.data.getTreninzi().add(trening);
		DataManager.saveData();
	}
	
	public IstorijaTreninga getIstorijaPoKorisnikITrening(String korisnikId, String treningId) {
		
		for (IstorijaTreninga i : DataManager.data.getIstorija()) {
			if (i.getKupac().equals(korisnikId) && i.getTrening().equals(treningId)) {
				return i;
			}
		}
		
		return null;
	}
	
	public void dodajNoviZapisUIstoriju(IstorijaTreninga it) {
		DataManager.data.getIstorija().add(it);
		DataManager.saveData();
		
	}
	
	public List<IstorijaTreninga> getIstorijaPoKupacId(String kupacId) {
		List<IstorijaTreninga> pomocna = new ArrayList<IstorijaTreninga>();
		for (IstorijaTreninga it : DataManager.data.getIstorija()) {
			if (it.getKupac().equals(kupacId)) {
				pomocna.add(it);
			}
		}
		
		return pomocna;
	}
	
	public List<IstorijaTreninga> getIstorijaPoObjekatId(String objekatId) {
		List<IstorijaTreninga> pomocna = new ArrayList<IstorijaTreninga>();
		for (IstorijaTreninga it : DataManager.data.getIstorija()) {
			if (it.getSportskiObjekat().equals(objekatId)) {
				pomocna.add(it);
			}
		}
		
		return pomocna;
	}
	
}
