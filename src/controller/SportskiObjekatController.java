package controller;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.SportskiObjekat;
import models.StatusObjekta;
import models.TipObjekta;
import services.SportskiObjektiService;


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
				
	}
}
