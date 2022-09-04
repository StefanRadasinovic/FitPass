package services;

import java.util.ArrayList;
import java.util.List;

import data.Data;
import data.DataManager;
import models.SportskiObjekat;

public class SportskiObjektiService {
	
	public List<SportskiObjekat> getSportskiObjekti() {
		List<SportskiObjekat> list = new ArrayList<SportskiObjekat>();
		for (SportskiObjekat so : DataManager.data.getSportskiObjekti()) {
			if (!so.isObrisan()) {
				list.add(so);
			}
		}
		return list;
	}
	
	public SportskiObjekat getObjekatPoId(String id) {
		
		for (SportskiObjekat s : DataManager.data.getSportskiObjekti()) {
			if (s.getId().equals(id)) {
				return s;
			}
		}
		
		return null;
	}
	
	public void sacuvajNovi(SportskiObjekat s) {
		DataManager.data.getSportskiObjekti().add(s);
		DataManager.saveData();
	}
	
	public void obrisi(SportskiObjekat sOb) {
		for (SportskiObjekat s : DataManager.data.getSportskiObjekti()) {
			if (s.getId().equals(sOb.getId())) {
				s.setObrisan(true);
			}
		}
		
		DataManager.saveData();
	}

}
