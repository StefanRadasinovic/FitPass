package controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.MultipartConfigElement;

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
		
		get("trening/pregled/:id", (req, res) -> {
			res.type("application/json");
			res.status(200);
			
			String treningId = req.params("id");
			
			TreningService service = new TreningService();
			Trening t = service.getPoId(treningId);
			
			if (t == null) {
				res.status(404);
				return null;
			}
			
			
			TreningPrikazDTO dto = new TreningPrikazDTO();
			
			
			dto.setId(t.getId());
			dto.setNaziv(t.getNaziv());
			dto.setCena(t.getCena());
			dto.setOpis(t.getOpis());
			dto.setSlikaURL(t.getSlika());
			dto.setTip(t.getTip().toString());
			dto.setTrajanje(t.getTrajanje());
			dto.setDatum(t.getDatum());
			dto.setTrener(t.getTrener());
			dto.setKupac(t.getKupac());
			dto.setObjekat(t.getSportskiObjekat());
			
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(dto, TreningPrikazDTO.class);
			return json;
		});
		
		put("trening/azuriranje/:id", (req, res) -> {
			res.type("application/json");
			
			String treningId = req.params("id");
			
			req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
			String naziv = req.queryParams("naziv");
			String tip = req.queryParams("tip");
			String opis = req.queryParams("opis");
			String trajanje = req.queryParams("trajanje");
			String datum = req.queryParams("datum");
			int cena = Integer.parseInt(req.queryParams("cena"));
			String objekatId = req.queryParams("objekatId");
			String kupac = req.queryParams("kupac");
			String trener= req.queryParams("trener");
			
			String slikaURL = "";
			if (req.raw().getPart("slika") != null) {
				try (InputStream is = req.raw().getPart("slika").getInputStream()) {
			    	slikaURL = "/slike/" + String.valueOf(System.currentTimeMillis()) + ".jpg";
			        File targetFile = new File(System.getProperty("user.dir") + "/static" + slikaURL);
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
			}
		    
			TreningService service = new TreningService();
			Trening trening = service.getPoId(treningId);
			
			if (trening == null) {
				res.status(404);
				return "Trening nije pronadjen";
			}
			
			try {
				trening.setNaziv(naziv);
				trening.setOpis(opis);
				trening.setTip(TreningTip.valueOf(tip));
				trening.setTrajanje(Integer.parseInt(trajanje));
				trening.setDatum(datum);
				trening.setCena(cena);
				trening.setSportskiObjekat(objekatId);
				trening.setKupac(kupac);
				trening.setTrener(trener);
				
				if (!slikaURL.isEmpty()) {
					trening.setSlika(slikaURL);
				}
				
				service.azuriraj(trening);
			} catch (Exception e) {
				res.status(400);
				return "Neispravan unos";
			}
			
			res.status(200);
			return "Uspesno";

		});
		
		post("/treninzi/novi", (req, res) -> {
			res.type("application/json");
			
			req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
			String naziv = req.queryParams("naziv");
			String tip = req.queryParams("tip");
			String opis = req.queryParams("opis");
			String trajanje = req.queryParams("trajanje");
			String datum = req.queryParams("datum");
			int cena = Integer.parseInt(req.queryParams("cena"));
			String kupac = req.queryParams("kupac");
			String trener = req.queryParams("trener");
			String sportskiObjekat = req.queryParams("objekatId");
			
			if (naziv.isEmpty() || tip.isEmpty() || opis.isEmpty() ||
					trajanje.isEmpty() || trener.isEmpty() || sportskiObjekat.isEmpty() && datum.isEmpty()) {
				res.status(400);
				return "Neispravan unos";
			}
			
			String slikaURL = "";
		    try (InputStream is = req.raw().getPart("slika").getInputStream()) {
		    	slikaURL = "/slike/" + String.valueOf(System.currentTimeMillis()) + ".jpg";
		        File targetFile = new File(System.getProperty("user.dir") + "/static" + slikaURL);
		        OutputStream outStream = new FileOutputStream(targetFile);

		        byte[] buffer = new byte[8 * 1024];
		        int bytesRead;
		        while ((bytesRead = is.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		        is.close();
		        outStream.close();
		    } catch (Exception e) {
				res.status(500);
				return "Greska";
			}
			
		    try {
				Trening noviTrening = new Trening();
				noviTrening.setId(String.valueOf(UUID.randomUUID().toString()));
				noviTrening.setNaziv(naziv);
				noviTrening.setOpis(opis);
				noviTrening.setTip(TreningTip.valueOf(tip));
				noviTrening.setTrajanje(Integer.parseInt(trajanje));
				noviTrening.setSlika(slikaURL);
				noviTrening.setDatum(datum);
				noviTrening.setCena(cena);
				noviTrening.setTrener(trener);
				noviTrening.setSportskiObjekat(sportskiObjekat);
				noviTrening.setKupac(kupac);
				
				TreningService s = new TreningService();
				s.sacuvajNovi(noviTrening);
				
				res.status(200);
				return "Uspesno";
		    } catch (Exception e) {
		    	res.status(400);
				return "Neispravni podaci";
			}
		

		});
	}
	
}
