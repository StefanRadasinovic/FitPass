package app;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
import java.io.File;

import controller.SportskiObjekatController;
import controller.TreningController;
import controller.ClanarinaController;
import controller.KorisnikController;
import data.DataManager;

public class Application {
	
	public static void main(String[] args) throws Exception {
		port(8080);
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
					
		KorisnikController.listen();
		SportskiObjekatController.listen();
		TreningController.listen();
		ClanarinaController.listen();
		
		DataManager.readData();
		
	}
}
