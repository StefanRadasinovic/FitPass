package services;

import java.time.LocalDateTime;
import java.util.List;

import data.DataManager;
import models.Korisnik;
import models.KupljenaClanarina;
import models.StatusClanarina;
import models.TipKorisnika;

public class ClanarinaKupovinaService {
	
	public List<KupljenaClanarina> sveKupljeneClanarine() {
		return DataManager.data.getKupljeneClanarine();
	}
	
	public void sacuvaj(KupljenaClanarina kc) {
		DataManager.data.getKupljeneClanarine().add(kc);
		DataManager.saveData();
	}
	
	public void staraClanarinaDeaktivacija(String korisnikId) {
		int brojBodova = 0;
		for (KupljenaClanarina kc : sveKupljeneClanarine()) {
			if (kc.getStatus().equals(StatusClanarina.AKTIVNA) || kc.getTerminaOstalo() == 0 || LocalDateTime.parse(kc.getDatumVazenja()).isBefore(LocalDateTime.now())) {
				kc.setStatus(StatusClanarina.NEAKTIVNA);
				
				brojBodova = izracunajBrojPoena(kc);
			}
		}
		
		KorisnikService kService = new KorisnikService();
		Korisnik k = kService.getPoId(korisnikId);
		k.setSakupljeniBodovi(k.getSakupljeniBodovi() + brojBodova);
		if (k.getSakupljeniBodovi() < 100) {
			k.setTip(TipKorisnika.BRONZANI);
		} else if (k.getSakupljeniBodovi() < 200) {
			k.setTip(TipKorisnika.SREBRNI);
		} else {
			k.setTip(TipKorisnika.ZLATNI);
		}
		kService.azuriraj(k);
		
		DataManager.saveData();
		
	}
	
	private int izracunajBrojPoena(KupljenaClanarina kc) {
		if (kc.getTerminaOstalo() != 0 && kc.getTerminaOstalo() > kc.getTerminaUkupno() / 3) {
			return (int) Math.floor(kc.getCena() / 1000 * (kc.getTerminaUkupno() - kc.getTerminaOstalo()));
		} 
		
		return (int) (Math.floor(kc.getCena() / 1000*133*4) * (-1));
		
	}
	
	public KupljenaClanarina aktivnaPoKupacId(String kupacId) {
		for (KupljenaClanarina kc : sveKupljeneClanarine()) {
			if (kc.getKupac().equals(kupacId) && kc.getStatus().equals(StatusClanarina.AKTIVNA)) {
				return kc;
			}
		}
		
		return null;
	}
	
	public void azurirajTerminaPreostalo(KupljenaClanarina kClanarina) {
		for (KupljenaClanarina kc : sveKupljeneClanarine()) {
			if (kc.getId().equals(kClanarina.getId())) {
				kc.setTerminaOstalo(kc.getTerminaOstalo() - 1);
			}
		}
		
		DataManager.saveData();
	}
}
