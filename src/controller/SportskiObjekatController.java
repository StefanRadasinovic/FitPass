package controller;

import static spark.Spark.get;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
				
	}
}
