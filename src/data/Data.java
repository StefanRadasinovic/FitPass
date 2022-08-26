package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import models.Korisnik;
import models.SportskiObjekat;

public class Data {
	
	private List<Korisnik> korisnici = new ArrayList<Korisnik>();
	private List<SportskiObjekat> sportskiObjekti = new ArrayList<SportskiObjekat>();

	public Collection<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(List<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}

	public List<SportskiObjekat> getSportskiObjekti() {
		return sportskiObjekti;
	}

	public void setSportskiObjekti(List<SportskiObjekat> sportskiObjekti) {
		this.sportskiObjekti = sportskiObjekti;
	}		
}
