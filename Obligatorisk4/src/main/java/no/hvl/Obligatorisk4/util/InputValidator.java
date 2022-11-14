package no.hvl.Obligatorisk4.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.hvl.Obligatorisk4.deltakerliste.Deltaker;
import no.hvl.Obligatorisk4.deltakerliste.DeltakerService;

@Component
public class InputValidator {

	@Autowired
	DeltakerService dservice;

	public static final String ER_BOKSTAV = "[a-zA-æøåÆØÅ ]";
	public static final String TO_FLERE = "{2,}";
	public static final String ATTE_FLERE = "{8,}";
	public static final String MOBIL_NR = "^[0-9]{8}$";
	public static final String PASSORD_KRAV = "^{8,}$";

	public boolean sjekkLogin(String mobil, String passord) {
		for (Deltaker deltaker : dservice.finnAlleDeltakere()) {
			if (deltaker.getMobil().equals(mobil)) {
				return deltaker.getPassord().equals(passord);
			}
		}
		return false;
	}

	public String finnSalt(String mobil) {
		for (Deltaker deltaker : dservice.finnAlleDeltakere()) {
			if (deltaker.getMobil().equals(mobil)) {
				return deltaker.getSalt();
			}
		}
		return null;
	}

	public String finnHashetpassord(String mobil) {
		for (Deltaker deltaker : dservice.finnAlleDeltakere()) {
			if (deltaker.getMobil().equals(mobil)) {
				return deltaker.getPassord();
			}
		}
		return null;
	}

	public static boolean gyldigNavn(String fornavn) {
		return fornavn != null && fornavn.matches("^" + ER_BOKSTAV + TO_FLERE + "$");
	}

	public static boolean gyldigMobil(String mobil) {
		return mobil != null && mobil.matches(MOBIL_NR);
	}

	public static boolean gyldigPassord(String passord) {
		return passord != null && passord.matches(PASSORD_KRAV);
	}
}
