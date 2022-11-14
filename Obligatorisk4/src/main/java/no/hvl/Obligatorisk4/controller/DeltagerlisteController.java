package no.hvl.Obligatorisk4.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import no.hvl.Obligatorisk4.deltakerliste.DeltakerRepo;
import no.hvl.Obligatorisk4.util.LoginUtil;

@Controller
@RequestMapping(value = "deltagerliste")
public class DeltagerlisteController {
	@Autowired
	private DeltakerRepo deltakerRepo;
	
	

	@Value("${app.url.paamelding}")
	private String PAAMELDING_URL;
	@Value("${app.url.login}")
	private String LOGIN_URL;
	@Value("${app.url.paameldt}")
	private String PAAMELDT_URL;
	@Value("${app.url.deltagerliste}")
	private String DELTAGERLISTE_URL;

	@GetMapping
	public String visDeltagerliste(Model model, HttpSession session, RedirectAttributes ra) {
		if (!LoginUtil.erBrukerInnlogget(session)) {
			ra.addFlashAttribute("errorMsg", "Du må logge inn for å se deltagerlisten.");
			return "redirect:" + LOGIN_URL;
		}
		// Finner alle deltakere ved hjelp av deltakerrepoet.
		model.addAttribute("deltakere", deltakerRepo.findAllByOrderByFornavnAsc());
		return DELTAGERLISTE_URL;
	}

	@PostMapping
	public String loggUt() {
		return "redirect:" + "loggetut";
	}
}
