package services;

import java.util.List;

import data.DataManager;
import models.SportskiObjekat;

public class SportskiObjektiService {
	
	public List<SportskiObjekat> getSportskiObjekti() {
		return DataManager.data.getSportskiObjekti();
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

}
