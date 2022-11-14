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

import no.hvl.Obligatorisk4.deltakerliste.DeltakerRepo;
import no.hvl.Obligatorisk4.deltakerliste.DeltakerService;
import no.hvl.Obligatorisk4.util.InputValidator;
import no.hvl.Obligatorisk4.util.LoginUtil;
import no.hvl.Obligatorisk4.util.PassordUtil;

@Controller
@RequestMapping(value = "login")
public class LoginController {
	@Autowired
	public DeltakerRepo deltakerrepo;

	@Autowired
	public InputValidator inputvalid;

	@Autowired
	DeltakerService deltakers;

	@Value("${app.url.login}")
	private String LOGIN_URL;
	@Value("${app.url.deltagerliste}")
	private String DELTAGERLISTE_URL;

	@GetMapping
	public String visLogin() {
		return LOGIN_URL;
	}

	@PostMapping
	public String loggInn(RedirectAttributes ra, HttpServletRequest rq, @RequestParam(name = "mobil") String mobil,
			@RequestParam(name = "passord") String passord) {
		String salt = inputvalid.finnSalt(mobil);
		String hashetPassord = inputvalid.finnHashetpassord(mobil);

		if (PassordUtil.validerMedSalt(passord, salt, hashetPassord)) {
			LoginUtil.loggInnBruker(rq, mobil, passord);
			ra.addFlashAttribute("deltaker", mobil);
			return "redirect:" + DELTAGERLISTE_URL;
		} else {
			ra.addFlashAttribute("errorMsg", "Ugyldig brukernavn og/eller passord");
			return "redirect:" + LOGIN_URL;
		}

	}
}
