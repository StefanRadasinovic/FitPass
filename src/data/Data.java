package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import models.KupljenaClanarina;
import models.Clanarina;
import models.IstorijaTreninga;
import models.Korisnik;
import models.SportskiObjekat;
import models.Trening;

public class Data {
	
	private List<Korisnik> korisnici = new ArrayList<Korisnik>();
	private List<SportskiObjekat> sportskiObjekti = new ArrayList<SportskiObjekat>();
	private List<Trening> treninzi = new ArrayList<Trening>();
	private List<IstorijaTreninga> istorija = new ArrayList<IstorijaTreninga>();
	private List<Clanarina> clanarine = new ArrayList<Clanarina>();
	private List<KupljenaClanarina> kupljeneClanarine = new ArrayList<KupljenaClanarina>();

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

	public List<IstorijaTreninga> getIstorija() {
		return istorija;
	}

	public void setIstorija(List<IstorijaTreninga> istorija) {
		this.istorija = istorija;
	}

	public List<Clanarina> getClanarine() {
		return clanarine;
	}

	public void setClanarine(List<Clanarina> clanarine) {
		this.clanarine = clanarine;
	}

	public List<KupljenaClanarina> getKupljeneClanarine() {
		return kupljeneClanarine;
	}

	public void setKupljeneClanarine(List<KupljenaClanarina> kupljeneClanarine) {
		this.kupljeneClanarine = kupljeneClanarine;
	}
	
	
}
