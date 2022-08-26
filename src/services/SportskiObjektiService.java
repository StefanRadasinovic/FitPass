package services;

import java.util.List;

import data.DataManager;
import models.SportskiObjekat;

public class SportskiObjektiService {
	
	public List<SportskiObjekat> getSportskiObjekti() {
		return DataManager.data.getSportskiObjekti();
	}

}
