package services;

import java.util.ArrayList;
import java.util.List;

import data.DataManager;
import models.Trening;

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
	
	public void sacuvajNovi(Trening trening) {
		DataManager.data.getTreninzi().add(trening);
		DataManager.saveData();
	}
	
}
