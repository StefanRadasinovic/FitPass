package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import models.Korisnik;
import models.SportskiObjekat;
import models.Trening;

public class Data {
	
	private List<Korisnik> korisnici = new ArrayList<Korisnik>();
	private List<SportskiObjekat> sportskiObjekti = new ArrayList<SportskiObjekat>();
	private List<Trening> treninzi = new ArrayList<Trening>();

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

	public List<Trening> getTreninzi() {
		return treninzi;
	}

	public void setTreninzi(List<Trening> treninzi) {
		this.treninzi = treninzi;
	}		

}
