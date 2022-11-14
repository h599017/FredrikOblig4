package no.hvl.Obligatorisk4.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtil {

	public static void loggUtBruker(HttpSession session) {
		session.invalidate();
	}

	public static void loggInnBruker(HttpServletRequest request, String mobil, String passord) {
		// Logger ut bruker før den logger inn igjen slik at vi ikke får problemer med
		// autentisering
		HttpSession http = request.getSession();
		http.setAttribute("passord", passord);
		http.setAttribute("mobil", mobil);
		// Setter tiden man maks kan være inaktiv i sekunder.
		http.setMaxInactiveInterval(120);

	}

	public static boolean erBrukerInnlogget(HttpSession session) {
		return session != null && session.getAttribute("passord") != null;
	}
}
