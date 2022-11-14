package no.hvl.Obligatorisk4.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import no.hvl.Obligatorisk4.deltakerliste.Deltaker;
import no.hvl.Obligatorisk4.deltakerliste.DeltakerRepo;
import no.hvl.Obligatorisk4.util.InputValidator;
import no.hvl.Obligatorisk4.util.LoginUtil;
import no.hvl.Obligatorisk4.util.PassordUtil;

@Controller
@RequestMapping(value = "paamelding")
public class PaameldingController {
	@Autowired
	private DeltakerRepo deltakerRepo;
	@Autowired
	public InputValidator inputvalid;
	@Value("${app.url.paamelding}")
	private String PAAMELDING_URL;
	@Value("${app.url.login}")
	private String LOGIN_URL;
	@Value("${app.url.paameldt}")
	private String PAAMELDT_URL;

	@GetMapping
	public String paameldingPage() {
		return PAAMELDING_URL;
	}

	@PostMapping
	public String sendPaamelding(HttpServletRequest rq, RedirectAttributes ra,
			@RequestParam(name = "fornavn") String fornavn, @RequestParam(name = "etternavn") String etternavn,
			@RequestParam(name = "mobil") String mobil, @RequestParam(name = "kjonn") String kjonn,
			@RequestParam(name = "passord") String passord,
			@RequestParam(name = "confirm_password") String repetertpassord) {

		if (deltakerRepo.existsByMobil(mobil)) {
			ra.addFlashAttribute("errorMsg", "Dette telefonnummeret har allerede blitt registrert vennligst logg inn");
			return "redirect:" + LOGIN_URL;
		} else if (!passord.equals(repetertpassord)) {
			ra.addFlashAttribute("errorMsg", "Passordene var ikke like, vennligst prøv igjen.");
			return "redirect:" + PAAMELDING_URL;
		}

		else {
			// Javasjekk i tilfelle required pattern ikke fungerer.
			if (!InputValidator.gyldigNavn(fornavn)) {
				ra.addFlashAttribute("ugyldig", "Ugyldig input for fornavn");
				return "redirect:" + PAAMELDING_URL;
			}

			if (!InputValidator.gyldigNavn(etternavn)) {
				ra.addFlashAttribute("ugyldig", "Ugyldig input for etternavn");
				return "redirect:" + PAAMELDING_URL;
			}

			if (!InputValidator.gyldigMobil(mobil)) {
				ra.addFlashAttribute("ugyldig", "Ugyldig input for mobil");
				return "redirect:" + PAAMELDING_URL;
			}

			// Salter og hasher passordet.
			String salt = PassordUtil.genererTilfeldigSalt();
			passord = PassordUtil.hashMedSalt(passord, salt);

			// Oppretter en ny deltaker
			Deltaker ny = new Deltaker(fornavn, etternavn, mobil, kjonn, passord, salt);
			// Lager et flash attribute som bruker for å hente ut igjen informasjonen ved
			// bekreftelse.
			ra.addFlashAttribute("deltaker", ny);
			ny.setFornavn(fornavn);
			ny.setEtternavn(etternavn);
			ny.setMobil(mobil);
			ny.setKjonn(kjonn);
			ny.setPassord(passord);
			ny.setSalt(salt);
			// Bruker JPARepo til å lagre til databasen min.
			deltakerRepo.save(ny);
			// Sjekker om passordet finnes og logger inn bruker slik at bruker får tilgang
			// resten av session.
			if (inputvalid.sjekkLogin(mobil, passord)) {
				LoginUtil.loggInnBruker(rq, mobil, passord);
				ra.addFlashAttribute("deltaker", ny);
			}
			return "redirect:" + PAAMELDT_URL;
		}
	}
}
