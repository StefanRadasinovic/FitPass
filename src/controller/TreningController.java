package controller;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.trening.TreningPrikazDTO;
import models.Korisnik;
import models.SportskiObjekat;
import models.Trening;
import models.TreningTip;
import services.KorisnikService;
import services.SportskiObjektiService;
import services.TreningService;
import spark.Session;

public class TreningController {
	
	public static void listen() {
		
		get("treninzi/trener/grupni", (req, res) -> {
			res.type("application/json");
			res.status(200);
			
			Session session = req.session(true);
			Korisnik ulogovaniKorisnik = session.attribute("user");
			
			TreningService service = new TreningService();
			List<Trening> treninzi = service.getTrenziZaKorisnika(ulogovaniKorisnik.getId());
			List<Trening> grupniTreninzi = new ArrayList<Trening>();
			for (Trening t : treninzi) {
				if (t.getTip().equals(TreningTip.GRUPNI)) {
					grupniTreninzi.add(t);
				}
			}
			
			List<TreningPrikazDTO> prikazLista = new ArrayList<TreningPrikazDTO>();
			for (Trening t : grupniTreninzi) {
				TreningPrikazDTO dto = new TreningPrikazDTO();
				dto.setId(t.getId());
				dto.setNaziv(t.getNaziv());
				dto.setCena(t.getCena());
				dto.setOpis(t.getOpis());
				dto.setSlikaURL(t.getSlika());
				dto.setTip(t.getTip().toString());
				dto.setTrajanje(t.getTrajanje());
				dto.setDatum(t.getDatum());
				
				KorisnikService ks = new KorisnikService();
				Korisnik trener = ks.getPoId(t.getTrener());
				if (trener != null ) {
					dto.setTrener(trener.getIme() + " " + trener.getPrezime());
				}
				
				Korisnik kupac = ks.getPoId(t.getKupac());
				if (kupac != null) {
					dto.setKupac(kupac.getIme() + " " + kupac.getPrezime());
				}
				
				SportskiObjektiService sos = new SportskiObjektiService();
				SportskiObjekat so = sos.getObjekatPoId(t.getSportskiObjekat());
				if (so != null) {
					dto.setObjekat(so.getNaziv());
				}
				
				prikazLista.add(dto);
			}
			
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(prikazLista, List.class);
			return json;
		});
		
		get("treninzi/trener/individualni", (req, res) -> {
			res.type("application/json");
			res.status(200);
			
			Session session = req.session(true);
			Korisnik ulogovaniKorisnik = session.attribute("user");
			
			TreningService service = new TreningService();
			List<Trening> treninzi = service.getTrenziZaKorisnika(ulogovaniKorisnik.getId());
			List<Trening> grupniTreninzi = new ArrayList<Trening>();
			for (Trening t : treninzi) {
				if (t.getTip().equals(TreningTip.INDIVIDUALNI)) {
					grupniTreninzi.add(t);
				}
			}
			
			List<TreningPrikazDTO> prikazLista = new ArrayList<TreningPrikazDTO>();
			for (Trening t : grupniTreninzi) {
				TreningPrikazDTO dto = new TreningPrikazDTO();
				dto.setId(t.getId());
				dto.setNaziv(t.getNaziv());
				dto.setCena(t.getCena());
				dto.setOpis(t.getOpis());
				dto.setSlikaURL(t.getSlika());
				dto.setTip(t.getTip().toString());
				dto.setTrajanje(t.getTrajanje());
				dto.setDatum(t.getDatum());
				
				KorisnikService ks = new KorisnikService();
				Korisnik trener = ks.getPoId(t.getTrener());
				if (trener != null ) {
					dto.setTrener(trener.getIme() + " " + trener.getPrezime());
				}
				
				Korisnik kupac = ks.getPoId(t.getKupac());
				if (kupac != null) {
					dto.setKupac(kupac.getIme() + " " + kupac.getPrezime());
				}
				
				SportskiObjektiService sos = new SportskiObjektiService();
				SportskiObjekat so = sos.getObjekatPoId(t.getSportskiObjekat());
				if (so != null) {
					dto.setObjekat(so.getNaziv());
				}
				
				prikazLista.add(dto);
			}
			
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(prikazLista, List.class);
			return json;
		});
	}
	
}
