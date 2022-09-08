package controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.MultipartConfigElement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Korisnik;
import models.KorisnikUloga;
import models.Lokacija;
import models.SportskiObjekat;
import models.StatusObjekta;
import models.TipObjekta;
import services.KorisnikService;
import services.SportskiObjektiService;
import spark.Session;


public class SportskiObjekatController {
	
	public static void listen() {
		
		get("/sportski-objekti", (req, res) -> {
			res.type("application/json");
			res.status(200);
			
			SportskiObjektiService service = new SportskiObjektiService();
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(service.getSportskiObjekti(), List.class);
			return json;
		});
		
		get("/sportski-objekti/pretraga", (req, res) -> {
			res.type("application/json");
			res.status(200);
			
			String pretragaTekst = req.queryParams("pretragaTekst");
			String pretragaPo = req.queryParams("pretragaPo");
			String tipObjekta = req.queryParams("tipObjekta");
			boolean otvoreni = Boolean.parseBoolean(req.queryParams("otvoreni"));
			boolean rastuceSortiranje = Boolean.parseBoolean(req.queryParams("rastuceSortiranje"));
			String sortiranjePo = req.queryParams("sortiranjePo");
						
			SportskiObjektiService service = new SportskiObjektiService();
			List<SportskiObjekat> sportskiObjekti = service.getSportskiObjekti();

			if (!pretragaTekst.isEmpty() && !pretragaPo.isEmpty()) {
				List<SportskiObjekat> pomocna = new ArrayList<SportskiObjekat>();
				if (pretragaPo.equals("naziv")) {
					for (SportskiObjekat s : sportskiObjekti) {
						if (s.getNaziv().contains(pretragaTekst)) {
							pomocna.add(s);
						}
					}
					
					sportskiObjekti = pomocna;
				} else if (pretragaPo.equals("tip")) {
					for (SportskiObjekat s : sportskiObjekti) {
						if (s.getTipObjekta().equals(TipObjekta.valueOf(pretragaTekst))) {
							pomocna.add(s);
						}
					}
					
					sportskiObjekti = pomocna;
				} else if (pretragaPo.equals("lokacija")) {
					for (SportskiObjekat s : sportskiObjekti) {
						if (s.getLokacija().getAdresa().contains(pretragaTekst)) {
							pomocna.add(s);
						}
					}
					
					sportskiObjekti = pomocna;
				} else if (pretragaPo.equals("ocena")) {
					try {
						int ocena = Integer.parseInt(pretragaTekst);
						for (SportskiObjekat s : sportskiObjekti) {
							if (s.getProsecnaOcena() == ocena) {
								pomocna.add(s);
							}
						}
						
						sportskiObjekti = pomocna;
					} catch (Exception e) {}
				}
				
			}
			
			if (!tipObjekta.isEmpty()) {
				List<SportskiObjekat> pomocna = new ArrayList<SportskiObjekat>();
				for (SportskiObjekat s : sportskiObjekti) {
					if (s.getTipObjekta().equals(TipObjekta.valueOf(tipObjekta))) {
						pomocna.add(s);
					}
				}
				
				sportskiObjekti = pomocna;
			}
			
			if (otvoreni) {
				List<SportskiObjekat> pomocna = new ArrayList<SportskiObjekat>();
				for (SportskiObjekat s : sportskiObjekti) {
					if (s.getStatus().equals(StatusObjekta.AKTIVAN)) {
						pomocna.add(s);
					}
				}
				
				sportskiObjekti = pomocna;
			}
			
			if (!sortiranjePo.isEmpty()) {
				if (sortiranjePo.equals("naziv")) {
					if (rastuceSortiranje) {
						sportskiObjekti.sort(SportskiObjekat.NazivComparator);
					} else {
						sportskiObjekti.sort(SportskiObjekat.ObrnutiNazivComparator);
					}
				} else if (sortiranjePo.equals("lokacija")) {
					if (rastuceSortiranje) {
						sportskiObjekti.sort(SportskiObjekat.LokacijaComparator);
					} else {
						sportskiObjekti.sort(SportskiObjekat.ObrnutiLokacijaComparator);
					}
				} else if (sortiranjePo.equals("prosecna-ocena")) {
					if (rastuceSortiranje) {
						sportskiObjekti.sort(SportskiObjekat.ProsecnaOcenaComparator);
					} else {
						sportskiObjekti.sort(SportskiObjekat.ObrnutiProsecnaOcenaComparator);
					}
				}
			}
			
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(sportskiObjekti, List.class);
			return json;
		});
		
		get("/sportski-objekti/menadzer-objekat", (req, res) -> {			
			Session session = req.session(true);
			Korisnik ulogovaniKorisnik = session.attribute("user");
			if (ulogovaniKorisnik == null) {
				res.status(403);
				return "Niste ulogovani";
			} else if (!ulogovaniKorisnik.getUloga().equals(KorisnikUloga.MENADZER)) {
				res.status(403);
				return "Niste ulogovani kao menadzer";
			}
			
			res.type("application/json");
			res.status(200);
			
			String objekatId = ulogovaniKorisnik.getObjekatKojimUpravlja();
			
			SportskiObjektiService service = new SportskiObjektiService();
			SportskiObjekat sportskiObjekat = service.getObjekatPoId(objekatId);
			if (sportskiObjekat == null) {
				res.type("application/json");
				res.status(404);
				return "Objekat nije pronadjen";
			} else {
				Gson g = new GsonBuilder().setPrettyPrinting().create();
				String json = g.toJson(sportskiObjekat, SportskiObjekat.class);
				return json;
			}
		});
		
		post("/sportski-objekti/novi", (req, res) -> {
			req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
			String naziv = req.queryParams("naziv");
			String tip = req.queryParams("tip");
			String adresa = req.queryParams("adresa");
			String menadzer = req.queryParams("menadzer");
			String radnoVreme = req.queryParams("radnoVreme");
			String sadrzaj = req.queryParams("sadrzaj");
			
			if (naziv.isEmpty() || tip.isEmpty() || menadzer.isEmpty()) {
				res.status(400);
				return "Sva polja su obavezna";
			}
			
			String logo = "";
		    try (InputStream is = req.raw().getPart("logo").getInputStream()) {
		    	logo = "/slike/" + String.valueOf(System.currentTimeMillis()) + ".jpg";
		        File targetFile = new File(System.getProperty("user.dir") + "/static" + logo);
		        OutputStream outStream = new FileOutputStream(targetFile);
		        
		        byte[] buffer = new byte[8 * 1024];
		        int bytesRead;
		        while ((bytesRead = is.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		        is.close();
		        outStream.close();
		    } catch (Exception e) {
				e.printStackTrace();
				res.status(500);
				return "Internal server error";
			}
			
			try {
				SportskiObjekat sportskiObjekat = new SportskiObjekat();
				sportskiObjekat.setId(String.valueOf(UUID.randomUUID().toString()));
				sportskiObjekat.setNaziv(naziv);
				sportskiObjekat.setTipObjekta(TipObjekta.valueOf(tip));
				sportskiObjekat.setStatus(StatusObjekta.NEAKTIVAN);
				sportskiObjekat.setSadrzaj(sadrzaj);
				sportskiObjekat.setRadnoVreme(radnoVreme);
				
				Lokacija l = new Lokacija();
				l.setAdresa(adresa);
				sportskiObjekat.setLokacija(l);
				sportskiObjekat.setLogo(logo);
				
				SportskiObjektiService s = new SportskiObjektiService();
				s.sacuvajNovi(sportskiObjekat);
				
				Gson g = new GsonBuilder().setPrettyPrinting().create();
				res.status(200);
				return g.toJson("");
			} catch (Exception e) {
				res.status(400);
				return "Unete vrednosti nisu ispravne";
			}
		
		});
		
		get("/sportski-objekti/:id", (req, res) -> {
			res.type("application/json");
			res.status(200);
			
			String objekatId = req.params("id");
			
			SportskiObjektiService service = new SportskiObjektiService();
			SportskiObjekat sportskiObjekat = service.getObjekatPoId(objekatId);
			if (sportskiObjekat == null) {
				res.type("application/json");
				res.status(404);
				return "Objekat nije pronadjen";
			} else {
				Gson g = new GsonBuilder().setPrettyPrinting().create();
				String json = g.toJson(sportskiObjekat, SportskiObjekat.class);
				return json;
			}
		});
		
		delete("/sportski-objekat/brisanje/:id", (req, res) -> {
			res.type("application/json");
			res.status(200);
			
			String objekatId = req.params("id");
			
			SportskiObjektiService service = new SportskiObjektiService();
			SportskiObjekat sob = service.getObjekatPoId(objekatId);
			KorisnikService kService = new KorisnikService();
			kService.ukloniSportskiObjekat(objekatId);
			service.obrisi(sob);
			
			return "Uspesno obrisan";
		});
				
	}
}
