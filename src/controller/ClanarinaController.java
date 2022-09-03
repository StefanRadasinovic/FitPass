package controller;

import static spark.Spark.get;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import services.ClanarinaService;

public class ClanarinaController {
	public static void listen() {
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
	}
}
