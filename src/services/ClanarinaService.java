package services;

import java.util.List;

import data.DataManager;
import models.Clanarina;
import models.KupljenaClanarina;

public class ClanarinaService {
	
	public List<Clanarina> sveClanarine() {
		return DataManager.data.getClanarine();
	}
}
