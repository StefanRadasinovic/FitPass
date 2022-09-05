package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Clanarina;
import models.Korisnik;
import models.KupljenaClanarina;
import models.StatusClanarina;
import services.ClanarinaKupovinaService;
import services.ClanarinaService;
import services.KorisnikService;
import spark.Session;

public class ClanarinaController {
	public static void listen() {
		get("/clanarina/pregled/:id", (req, res) -> {
			res.type("application/json");
			res.status(200);
			
			String clanarinaId = req.params("id");
			
			ClanarinaService service = new ClanarinaService();
			Clanarina clanarina = service.getPoId(clanarinaId);
			
			res.type("application/json");
			res.status(200);
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(clanarina, Clanarina.class);
			return json;
		});
		
		get("/clanarine", (req, res) -> {
			res.type("application/json");
			res.status(200);
			
			ClanarinaService service = new ClanarinaService();
			res.type("application/json");
			res.status(200);
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(service.sveClanarine(), List.class);
			return json;
		});
		
		post("/clanarina/kupovina/:id", (req, res) -> {
			res.type("application/json");
			res.status(200);
			
			String clanarinaId = req.params("id");
			
			Session ss = req.session(true);
			Korisnik ulogovaniKorisnik = ss.attribute("user");
			if (ulogovaniKorisnik == null) {
				res.status(403);
				return "Niste prijavljeni";
			}
			
			ClanarinaService service = new ClanarinaService();
			Clanarina clanarina = service.getPoId(clanarinaId);
			
			if (clanarina == null) {
				res.status(400);
				return "Clanarina nije pronadjena";
			}
			
			KupljenaClanarina kc = new KupljenaClanarina();
			kc.setId(UUID.randomUUID().toString());
			kc.setTip(clanarina.getTip());
			kc.setCena(clanarina.getCena());
			kc.setTerminaUkupno(clanarina.getBrojTermina());
			kc.setTerminaOstalo(clanarina.getKolikoDanaVazi());
			kc.setDatumPlacanja(LocalDateTime.now().toString());
			kc.setDatumVazenja(LocalDateTime.now().plusDays(clanarina.getKolikoDanaVazi()).toString());
			kc.setKupac(ulogovaniKorisnik.getId());
			kc.setStatus(StatusClanarina.AKTIVNA);
			
			ClanarinaKupovinaService ckService = new ClanarinaKupovinaService(); 
			ckService.staraClanarinaDeaktivacija(ulogovaniKorisnik.getId());
			ckService.sacuvaj(kc);
			
			res.type("application/json");
			res.status(200);
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String json = g.toJson(clanarina, Clanarina.class);
			return json;
		});
	}
}
