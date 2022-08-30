package services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import data.DataManager;
import dto.DtoRegistracija;
import models.Korisnik;
import models.KorisnikUloga;
import models.TipKorisnika;

public class KorisnikService {
	
	public List<Korisnik> sviMenadzeriBezObjektaKojimUpravljaju() {
		List<Korisnik> pomocna = new ArrayList<Korisnik>();
		List<Korisnik> korisnici = (List<Korisnik>) DataManager.data.getKorisnici();
		for (Korisnik korisnik : korisnici) {
			if (korisnik.getUloga().equals(KorisnikUloga.MENADZER) && korisnik.getObjekatKojimUpravlja() == null) {
				pomocna.add(korisnik);
			}
		}
		
		return pomocna;
	}
	
	public Korisnik login(String korisnickoIme, String lozinka) throws Exception {
		List<Korisnik> korisnici = (List<Korisnik>) DataManager.data.getKorisnici();
		for (Korisnik korisnik : korisnici) {
			if (korisnik.getKorisnickoIme().equals(korisnickoIme)) {
				if (korisnik.getLozinka().equals(lozinka)) return korisnik;
		        else throw new Exception();
			}
		}

	    throw new Exception();
    }
	
	public void registracijaKorisnika(DtoRegistracija dto) throws Exception {
		List<Korisnik> korisnici = (List<Korisnik>) DataManager.data.getKorisnici();
	    String korisnickoIme = dto.getKorisnickoIme();
	    for (Korisnik korisnik : korisnici) {
	    	if (korisnik.getKorisnickoIme().equals(korisnickoIme)) {
	    		throw new Exception("Korisnicko ime je vec iskorisceno");
	    	}
        }

	    Korisnik korisnik = new Korisnik();
	    korisnik.setId(UUID.randomUUID().toString());
	    korisnik.setKorisnickoIme(korisnickoIme);
	    korisnik.setIme(dto.getIme());
	    korisnik.setPrezime(dto.getPrezime());
	    korisnik.setLozinka(dto.getLozinka());
	    korisnik.setDatumRodjenja(dto.getDatumRodjenja());
	    korisnik.setUloga(KorisnikUloga.valueOf(dto.getUloga()));
	    korisnik.setObejekatKojimUpravlja(null);
	    korisnik.setPol(dto.getPol());
	    korisnik.setSakupljeniBodovi(0);
	    korisnik.setTip(TipKorisnika.BRONZANI);
	    korisnik.setUloga(KorisnikUloga.valueOf(dto.getUloga()));
	    
        DataManager.data.getKorisnici().add(korisnik);
        DataManager.saveData();
    }
	
	public Korisnik getPoId(String korisnikId) throws Exception {
		List<Korisnik> korisnici = (List<Korisnik>) DataManager.data.getKorisnici();
		for (Korisnik korisnik : korisnici) {
			if (korisnik.getId().equals(korisnikId)) {
				return korisnik;
			}
		}
		
		return null;
    }

	public List<Korisnik> sviKupci() {
		List<Korisnik> pomocna = new ArrayList<Korisnik>();
		List<Korisnik> korisnici = (List<Korisnik>) DataManager.data.getKorisnici();
		for (Korisnik korisnik : korisnici) {
			if (korisnik.getUloga().equals(KorisnikUloga.KUPAC)) {
				pomocna.add(korisnik);
			}
		}
		
		return pomocna;
	}
	
	public List<Korisnik> sviTreneri() {
		List<Korisnik> pomocna = new ArrayList<Korisnik>();
		List<Korisnik> korisnici = (List<Korisnik>) DataManager.data.getKorisnici();
		for (Korisnik korisnik : korisnici) {
			if (korisnik.getUloga().equals(KorisnikUloga.TRENER)) {
				pomocna.add(korisnik);
			}
		}
		
		return pomocna;
	}
	
	public void azuriraj(Korisnik korisnik) throws Exception {
		List<Korisnik> korisnici = (List<Korisnik>) DataManager.data.getKorisnici();
	    for (Korisnik k : korisnici) {
	    	if (korisnik.getId().equals(korisnik.getId())) {
	    	    k.setIme(korisnik.getIme());
	    	    k.setPrezime(korisnik.getPrezime());
	    	    k.setDatumRodjenja(korisnik.getDatumRodjenja());
	    	    k.setPol(korisnik.getPol());
	    	}
        }	    
	    
        DataManager.saveData();
    }
	
}
