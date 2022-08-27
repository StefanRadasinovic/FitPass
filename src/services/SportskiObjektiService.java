package services;

import java.util.List;

import data.DataManager;
import models.SportskiObjekat;

public class SportskiObjektiService {
	
	public List<SportskiObjekat> getSportskiObjekti() {
		return DataManager.data.getSportskiObjekti();
	}
	
	public SportskiObjekat getObjekatPoId(String id) {
		System.out.println(id);
		for (SportskiObjekat s : DataManager.data.getSportskiObjekti()) {
			System.out.println(s.getId());
			if (s.getId().equals(id)) {
				return s;
			}
		}
		
		return null;
	}

}
