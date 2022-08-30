package controller;

import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.get;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.DtoLogin;
import dto.DtoRegistracija;
import dto.korisnik.KorisnikPregledDTO;
import models.Korisnik;
import models.KorisnikUloga;
import models.SportskiObjekat;
import models.StatusObjekta;
import models.TipKorisnika;
import models.TipObjekta;
import services.KorisnikService;
import services.SportskiObjektiService;
import spark.Session;

public class KorisnikController {
	
	
	public static void listen() {
		
		get("/korisnici/pretraga", (req, res) -> {
			res.type("application/json");
			res.status(200);
			
			String pretragaTekst = req.queryParams("pretragaTekst");
			String pretragaPo = req.queryParams("pretragaPo");
			String tip = req.queryParams("tip");
			String uloga = req.queryParams("uloga");
			boolean rastuceSortiranje = Boolean.parseBoolean(req.queryParams("rastuceSortiranje"));
			String sortiranjePo = req.queryParams("sortiranjePo");
						
			KorisnikService service = new KorisnikService();
			List<Korisnik> korisnici = service.svi();

			if (!pretragaTekst.isEmpty() && !pretragaPo.isEmpty()) {
				List<Korisnik> pomocna = new ArrayList<Korisnik>();
				if (pretragaPo.equals("ime")) {
					for (Korisnik k : korisnici) {
						if (k.getIme().contains(pretragaTekst)) {
							pomocna.add(k);
						}
					}
					
					korisnici = pomocna;
				} else if (pretragaPo.equals("prezime")) {
					for (Korisnik k : korisnici) {
						if (k.getPrezime().contains(pretragaTekst)) {
							pomocna.add(k);
						}
					}
					
					korisnici = pomocna;
				} else if (pretragaPo.equals("korisnicko-ime")) {
					for (Korisnik k : korisnici) {
						if (k.getKorisnickoIme().contains(pretragaTekst)) {
							pomocna.add(k);
						}
					}
					
					korisnici = pomocna;
				}
			}
			
			if (!tip.isEmpty()) {
				List<Korisnik> pomocna = new ArrayList<Korisnik>();
				for (Korisnik k : korisnici) {
					if (k.getTip().equals(TipKorisnika.valueOf(tip))) {
						pomocna.add(k);
					}
				}
				
				korisnici = pomocna;
			}
			
			if (!uloga.isEmpty()) {
				List<Korisnik> pomocna = new ArrayList<Korisnik>();
				for (Korisnik k : korisnici) {
					if (k.getUloga().equals(KorisnikUloga.valueOf(uloga))) {
						pomocna.add(k);
					}
				}
				
				korisnici = pomocna;
			}
			
			if (!sortiranjePo.isEmpty()) {
				if (sortiranjePo.equals("ime")) {
					if (rastuceSortiranje) {
						korisnici.sort(Korisnik.ImeComparator);
					} else {
						korisnici.sort(Korisnik.ObrnutiImeComparator);
					}
				} else if (sortiranjePo.equals("prezime")) {
					if (rastuceSortiranje) {
						korisnici.sort(Korisnik.PrezimeComparator);
					} else {
						korisnici.sort(Korisnik.ObrnutiPrezimeComparator);
					}
				} else if (sortiranjePo.equals("korisnicko-ime")) {
					if (rastuceSortiranje) {
						korisnici.sort(Korisnik.KorisnickoImeComparator);
					} else {
						korisnici.sort(Korisnik.ObrnutiKorisnickoImeComparator);
					}
				} else if (sortiranjePo.equals("broj-bodova")) {
					if (rastuceSortiranje) {
						korisnici.sort(Korisnik.BrojBodovaComparator);
					} else {
						korisnici.sort(Korisnik.ObrnutiBrojBodovaComparator);
					}
				}
			}
			
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(korisnici, List.class);
			return json;
		});
		
		get("/menadzeriBezObjekta", (req, res) -> {
			KorisnikService service = new KorisnikService();
			List<Korisnik> menadzeri = service.sviMenadzeriBezObjektaKojimUpravljaju();
			res.status(200);
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(menadzeri, List.class);
			return json;
		});
		
		get("/korisnici/svi", (req, res) -> {
			KorisnikService service = new KorisnikService();
			List<Korisnik> menadzeri = service.svi();
			res.status(200);
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(menadzeri, List.class);
			return json;
		});
		
		get("/kupci", (req, res) -> {
			KorisnikService service = new KorisnikService();
			List<Korisnik> menadzeri = service.sviKupci();
			res.status(200);
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(menadzeri, List.class);
			return json;
		});
		
		get("/treneri", (req, res) -> {
			KorisnikService service = new KorisnikService();
			List<Korisnik> treneri = service.sviTreneri();
			res.status(200);
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(treneri, List.class);
			return json;
		});
		
		get("/korisnik/pregled", (req, res) -> {
			Session ss = req.session(true);
			Korisnik ulogovanikorisnik = ss.attribute("user");
			
			KorisnikPregledDTO dto = new KorisnikPregledDTO();
			dto.setId(ulogovanikorisnik.getId());
			dto.setIme(ulogovanikorisnik.getIme());
			dto.setPrezime(ulogovanikorisnik.getPrezime());
			dto.setDatumRodjenja(ulogovanikorisnik.getDatumRodjenja());
			dto.setKorisnickoIme(ulogovanikorisnik.getKorisnickoIme());
			dto.setPol(ulogovanikorisnik.getPol());
			dto.setTip(ulogovanikorisnik.getTip().toString());
			dto.setUloga(ulogovanikorisnik.getUloga().toString());
			
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(dto, KorisnikPregledDTO.class);
			return json;
		});
		
		put("/korisnik/pregled", (req, res) -> {
			Session ss = req.session(true);
			Korisnik ulogovanikorisnik = ss.attribute("user");
			
			try {
				Gson g = new GsonBuilder().setPrettyPrinting().create();
				KorisnikPregledDTO dto = g.fromJson(req.body(), KorisnikPregledDTO.class);
				if (dto.getIme().isEmpty() || dto.getPrezime().isEmpty() || dto.getDatumRodjenja().isEmpty()) {
					res.status(400);
					return "Neispravan unos";
				}
				
				ulogovanikorisnik.setIme(dto.getIme());
				ulogovanikorisnik.setPrezime(dto.getPrezime());
				ulogovanikorisnik.setPol(dto.getPol());
				ulogovanikorisnik.setDatumRodjenja(dto.getDatumRodjenja());
				
				KorisnikService service = new KorisnikService();
				service.azuriraj(ulogovanikorisnik);
				res.status(200);
				return "Uspesno";

			} catch (Exception e) {
				res.status(400);
				return e.getMessage();
			}
		});
			
		post("/registracija", (req, res) -> {
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			KorisnikService service = new KorisnikService();
			Session ss = req.session(true);
			Korisnik ulogovaniKorisnik = ss.attribute("user");
			if (ulogovaniKorisnik != null) {
				res.status(400);
				return "Registracija nije moguca kad ste ulogovani";
			}

			try {
				DtoRegistracija k = g.fromJson(req.body(), DtoRegistracija.class);
				
				if (k.getIme().isEmpty() || k.getPrezime().isEmpty() || k.getKorisnickoIme().isEmpty() ||
				    k.getLozinka().isEmpty() || k.getDatumRodjenja().isEmpty()) {
					res.status(400);
					return "Popunite sva polja";
				}
				
				service.registracijaKorisnika(k);
				res.status(200);
				return "Uspesno";

			} catch (Exception e) {
				res.status(400);
				return e.getMessage();
			}
			
		});
		
		post("/login", (req, res) -> {
			KorisnikService service = new KorisnikService();
			try {
				Gson g = new GsonBuilder().setPrettyPrinting().create();
				DtoLogin k = g.fromJson(req.body(), DtoLogin.class);

				if (k.getKorisnickoIme() == null || k.getLozinka() == null) {
					res.status(400);
					return res;
				}
				
				Session ss = req.session(true);
				Korisnik korisnik = service.login(k.getKorisnickoIme(), k.getLozinka());
				ss.attribute("user", korisnik);
				res.status(200);
				return "Uspesan login";
			} catch (Exception e) {
				res.status(400);
				return "Neispravan unos podataka";
			}

		});
		
		post("/logout", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			Korisnik ulogovanikorisnik = ss.attribute("user");
			if (ulogovanikorisnik != null) {
				ss.invalidate();
			}
			
			res.status(200);
			return "Uspesno";
		});
		
	}

}
