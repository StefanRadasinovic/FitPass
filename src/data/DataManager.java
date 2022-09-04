package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Clanarina;
import models.IstorijaTreninga;
import models.Korisnik;
import models.KupljenaClanarina;
import models.SportskiObjekat;
import models.Trening;


public class DataManager {
	
	private static Gson g = new GsonBuilder().setPrettyPrinting().create();
	
	private static String path = new File(new File("").getAbsolutePath() + "/static/data/data.json").getAbsolutePath();
	private static File file = new File(path);
	
	public static Data data = new Data();

	public static void saveData() {

		try {

			FileWriter writer = new FileWriter(file);
			writer.write(g.toJson(data));
			writer.close();
		} catch (IOException e) {
			System.out.println("Neuspesno cuvanje podataka");
		}
	}


	public static void readData() {

		try {

			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String st;
			String json = "";
			while ((st = br.readLine()) != null) json += st;
			data = g.fromJson(json, Data.class);
		} catch (FileNotFoundException e) {
			data.setKorisnici(new ArrayList<Korisnik>());
			data.setSportskiObjekti(new ArrayList<SportskiObjekat>());
			data.setTreninzi(new ArrayList<Trening>());
			data.setIstorija(new ArrayList<IstorijaTreninga>());
			data.setClanarine(new ArrayList<Clanarina>());
			data.setKupljeneClanarine(new ArrayList<KupljenaClanarina>());
			System.out.println("Fajl nije pronadjen");
		} catch (IOException e) {
			data.setKorisnici(new ArrayList<Korisnik>());
			data.setSportskiObjekti(new ArrayList<SportskiObjekat>());
			data.setTreninzi(new ArrayList<Trening>());
			data.setIstorija(new ArrayList<IstorijaTreninga>());
			data.setClanarine(new ArrayList<Clanarina>());
			data.setKupljeneClanarine(new ArrayList<KupljenaClanarina>());
			System.out.println("Neuspesno ucitavanje podataka");
		} catch (Exception e) {
			e.printStackTrace();
			data.setKorisnici(new ArrayList<Korisnik>());
			data.setSportskiObjekti(new ArrayList<SportskiObjekat>());
			data.setTreninzi(new ArrayList<Trening>());
			data.setIstorija(new ArrayList<IstorijaTreninga>());
			data.setClanarine(new ArrayList<Clanarina>());
			data.setKupljeneClanarine(new ArrayList<KupljenaClanarina>());
			System.out.println("Greska pri ucitavanju");
		}

	}
}
