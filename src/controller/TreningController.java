package controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.MultipartConfigElement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.trening.IstorijaDTO;
import dto.trening.PrijavaNaTreningDTO;
import dto.trening.ProsirenaIstorijaTrening;
import dto.trening.TreningPrikazDTO;
import models.IstorijaTreninga;
import models.Korisnik;
import models.SportskiObjekat;
import models.TipObjekta;
import models.Trening;
import models.TreningTip;
import services.KorisnikService;
import services.SportskiObjektiService;
import services.TreningService;
import spark.Session;

public class TreningController {
	
	public static void listen() {
		
		get("treninzi/objekat/:id", (req, res) -> {
			res.type("application/json");
			res.status(200);
			
			String objekatId = req.params("id");
			
			TreningService service = new TreningService();
			List<Trening> treninzi = service.getTrenziZaObjekat(objekatId);
			
			List<TreningPrikazDTO> prikazLista = new ArrayList<TreningPrikazDTO>();
			for (Trening t : treninzi) {
				TreningPrikazDTO dto = new TreningPrikazDTO();
				dto.setId(t.getId());
				dto.setNaziv(t.getNaziv());
				dto.setCena(t.getCena());
				dto.setOpis(t.getOpis());
				dto.setSlikaURL(t.getSlika());
				dto.setTip(t.getTip().toString());
				dto.setTrajanje(t.getTrajanje());
				dto.setDatum(t.getDatum());
				dto.setStatus(t.isOtkazan() ? "Otkazan" : "Aktivan");
				
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
		
		get("trening/istorija", (req, res) -> {
			Session ss = req.session(true);
			Korisnik ulogovaniKorisnik = ss.attribute("user");
			if (ulogovaniKorisnik == null) {
				res.status(403);
				return "Niste ulogovani";
			}
			
			TreningService service = new TreningService();
			List<IstorijaTreninga> istorija = service.getIstorijaPoKupacId(ulogovaniKorisnik.getId());
			List<IstorijaTreninga> filtrirana = new ArrayList<IstorijaTreninga>();
			for (IstorijaTreninga it : istorija) {
				if (LocalDateTime.parse(it.getDatumIVremePrijave()).compareTo(LocalDateTime.now().minusMonths(1)) >= 0) {
					filtrirana.add(it);
				}
			}
			
			List<IstorijaDTO> dtos = new ArrayList<IstorijaDTO>();
			for (IstorijaTreninga ti : filtrirana) {
				IstorijaDTO dto = new IstorijaDTO();
				dto.setId(ti.getId());
				dto.setDatum(ti.getDatumIVremePrijave());
				TreningService tService = new TreningService();
				Trening trening = tService.getPoId(ti.getTrening());
				dto.setTrening(trening.getNaziv());
				SportskiObjektiService sService = new SportskiObjektiService();
				SportskiObjekat so = sService.getObjekatPoId(trening.getSportskiObjekat());
				dto.setSportskiObjekat(so.getNaziv());
				
				dtos.add(dto);
			}
			
			res.type("application/json");
			res.status(200);
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(dtos, List.class);
			return json;
		});
		
		get("trening/istorija-pretraga", (req, res) -> {
			Session ss = req.session(true);
			Korisnik ulogovaniKorisnik = ss.attribute("user");
			if (ulogovaniKorisnik == null) {
				res.status(403);
				return "Niste ulogovani";
			}
			
			String pretragaTekst = req.queryParams("pretragaTekst");
			String cenaOd = req.queryParams("cenaOd");
			String cenaDo = req.queryParams("cenaDo");
			String datumOd = req.queryParams("datumOd");
			String datumDo = req.queryParams("datumDo");
			boolean bezDoplate = Boolean.parseBoolean(req.queryParams("bezDoplate"));
			String tip = req.queryParams("tipTreninga");
			boolean rastuceSortiranje = Boolean.parseBoolean(req.queryParams("rastuceSortiranje"));
			String sortiranjePo = req.queryParams("sortiranjePo");
			
			TreningService service = new TreningService();
			List<IstorijaTreninga> istorija = service.getIstorijaPoKupacId(ulogovaniKorisnik.getId());
			List<IstorijaTreninga> filtrirana = new ArrayList<IstorijaTreninga>();
			for (IstorijaTreninga it : istorija) {
				if (LocalDateTime.parse(it.getDatumIVremePrijave()).compareTo(LocalDateTime.now().minusMonths(1)) >= 0) {
					filtrirana.add(it);
				}
			}
			
			List<ProsirenaIstorijaTrening> prosireni = new ArrayList<ProsirenaIstorijaTrening>();
			for (IstorijaTreninga ti : filtrirana) {
				ProsirenaIstorijaTrening prosireniObjekat = new ProsirenaIstorijaTrening();
				prosireniObjekat.setId(ti.getId());
				prosireniObjekat.setDatum(ti.getDatumIVremePrijave());
				TreningService tService = new TreningService();
				Trening trening = tService.getPoId(ti.getTrening());
				prosireniObjekat.setTrening(trening);
				SportskiObjektiService sService = new SportskiObjektiService();
				SportskiObjekat so = sService.getObjekatPoId(trening.getSportskiObjekat());
				prosireniObjekat.setSportskiObjekat(so);
				
				prosireni.add(prosireniObjekat);
			}
			
			List<ProsirenaIstorijaTrening> treninziIstorija = prosireni;
			
			if (!pretragaTekst.isEmpty()) {
				List<ProsirenaIstorijaTrening> pomocna = new ArrayList<ProsirenaIstorijaTrening>();
				for (ProsirenaIstorijaTrening it : treninziIstorija) {
					if (it.getSportskiObjekat().getNaziv().contains(pretragaTekst.toLowerCase())) {
						pomocna.add(it);
					}
				}
				
				treninziIstorija = pomocna;
			}
			
			if (!cenaOd.isEmpty()) {
				List<ProsirenaIstorijaTrening> pomocna = new ArrayList<ProsirenaIstorijaTrening>();
				try {
					int cena = Integer.parseInt(cenaOd);
					for (ProsirenaIstorijaTrening it : treninziIstorija) {
						if (it.getTrening().getCena() >= cena) {
							pomocna.add(it);
						}
					}
					
					treninziIstorija = pomocna;
				} catch (Exception e) {
				}
				
			}
			
			if (!cenaDo.isEmpty()) {
				List<ProsirenaIstorijaTrening> pomocna = new ArrayList<ProsirenaIstorijaTrening>();
				try {
					int cena = Integer.parseInt(cenaDo);
					for (ProsirenaIstorijaTrening it : treninziIstorija) {
						if (it.getTrening().getCena() <= cena) {
							pomocna.add(it);
						}
					}
					
					treninziIstorija = pomocna;
				} catch (Exception e) {
				}
				
			}
			
			if (bezDoplate) {
				List<ProsirenaIstorijaTrening> pomocna = new ArrayList<ProsirenaIstorijaTrening>();
				for (ProsirenaIstorijaTrening it : treninziIstorija) {
					if (it.getTrening().getCena() == 0) {
						pomocna.add(it);
					}
				}
				
				treninziIstorija = pomocna;
			}
			
			if (!datumOd.isEmpty()) {
				try {
					LocalDateTime datumOdDate = LocalDateTime.parse(datumOd);
					List<ProsirenaIstorijaTrening> pomocna = new ArrayList<ProsirenaIstorijaTrening>();
					for (ProsirenaIstorijaTrening it : treninziIstorija) {
						if (LocalDateTime.parse(it.getTrening().getDatum()).compareTo(datumOdDate) >= 0) {
							pomocna.add(it);
						}
					}
					
					treninziIstorija = pomocna;
				} catch (Exception e) {
				}
			}
			
			if (!datumDo.isEmpty()) {
				try {
					LocalDateTime datumDoDate = LocalDateTime.parse(datumDo);
					List<ProsirenaIstorijaTrening> pomocna = new ArrayList<ProsirenaIstorijaTrening>();
					for (ProsirenaIstorijaTrening it : treninziIstorija) {
						if (LocalDateTime.parse(it.getTrening().getDatum()).compareTo(datumDoDate) <= 0) {
							pomocna.add(it);
						}
					}
					
					treninziIstorija = pomocna;
				} catch (Exception e) {
				}
			}
			
			if (!tip.isEmpty()) {
				try {
					TipObjekta tipObjekta = TipObjekta.valueOf(tip); 
					List<ProsirenaIstorijaTrening> pomocna = new ArrayList<ProsirenaIstorijaTrening>();
					for (ProsirenaIstorijaTrening it : treninziIstorija) {
						if (it.getSportskiObjekat().getTipObjekta().equals(tipObjekta)) {
							pomocna.add(it);
						}
					}
					
					treninziIstorija = pomocna;
				} catch (Exception e) {
				}
			}
			
			
			if (!sortiranjePo.isEmpty()) {
				if (sortiranjePo.equals("nazivSportskogObjekta")) {
					if (rastuceSortiranje) {
						treninziIstorija.sort(ProsirenaIstorijaTrening.NazivSportskogObjekaComparator);
					} else {
						treninziIstorija.sort(ProsirenaIstorijaTrening.NazivSportskogObjekaComparator);
					}
				} else if (sortiranjePo.equals("tipTreninga")) {
					if (rastuceSortiranje) {
						treninziIstorija.sort(SportskiObjekat.LokacijaComparator);
					} else {
						treninziIstorija.sort(SportskiObjekat.ObrnutiLokacijaComparator);
					}
				}
			}
			
			
			res.type("application/json");
			res.status(200);
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(treninziIstorija, List.class);
			return json;
		});
		
		post("/trening/prijava", (req, res) -> {
			Session ss = req.session(true);
			Korisnik ulogovaniKorisnik = ss.attribute("user");
			
			TreningService service = new TreningService();
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			PrijavaNaTreningDTO dto = g.fromJson(req.body(), PrijavaNaTreningDTO.class);
			IstorijaTreninga ti = service.getIstorijaPoKorisnikITrening(ulogovaniKorisnik.getId(), dto.getTrening());
			if (ti != null) {
				res.status(400);
				return "Prijava postoji";
			}
			
			//clanarina
			
			try {
				IstorijaTreninga it = new IstorijaTreninga();
				it.setId(UUID.randomUUID().toString());
				it.setDatumIVremePrijave(LocalDateTime.now().toString());
				it.setKupac(ulogovaniKorisnik.getId());
				it.setTrening(dto.getTrening());
				
				service.dodajNoviZapisUIstoriju(it);
				res.status(200);
				return "Uspesno";
			} catch (Exception e) {
				res.status(400);
				return "Neispravni podaci";
			}
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
