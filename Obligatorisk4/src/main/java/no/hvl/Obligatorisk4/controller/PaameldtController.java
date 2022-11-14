package no.hvl.Obligatorisk4.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import no.hvl.Obligatorisk4.util.LoginUtil;

@Controller
@RequestMapping(value = "paameldt")
public class PaameldtController {
	@Value("${app.url.login}")
	private String LOGIN_URL;
	@Value("${app.url.deltagerliste}")
	private String DELTAGERLISTE_URL;
	@Value("${app.url.paameldt}")
	private String PAAMELDT_URL;

	@GetMapping
	public String visRegistrering(HttpSession session, RedirectAttributes ra) {
		if (!LoginUtil.erBrukerInnlogget(session)) {
			ra.addFlashAttribute("errorMsg", "Du må logge inn for å se deltagerlisten.");
			return "redirect:" + LOGIN_URL;
		}

		return PAAMELDT_URL;
	}

	@PostMapping
	public String tilDeltakerListe() {
		return "redirect:" + DELTAGERLISTE_URL;
	}

}
