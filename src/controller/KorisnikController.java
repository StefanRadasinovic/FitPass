package controller;

import static spark.Spark.post;

import java.util.List;

import static spark.Spark.get;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.DtoLogin;
import dto.DtoRegistracija;
import models.Korisnik;
import services.KorisnikService;
import spark.Session;

public class KorisnikController {
	
	
	public static void listen() {
		
		get("/menadzeriBezObjekta", (req, res) -> {
			KorisnikService service = new KorisnikService();
			List<Korisnik> menadzeri = service.sviMenadzeriBezObjektaKojimUpravljaju();
			res.status(200);
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(menadzeri, List.class);
			return json;
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
