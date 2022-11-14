package no.hvl.Obligatorisk4.deltakerliste;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeltakerService {

	@Autowired
	private DeltakerRepo deltakerrepo;

	public List<Deltaker> finnAlleDeltakere() {
		return deltakerrepo.findAll();
	}
	
//	public List<Deltaker> finnAlleDeltakere() {
//		List<Deltaker> liste = deltakerrepo.findAll();
//		List<Deltaker> sortert = liste.stream().sorted().collect(Collectors.toList());
//		
//		return sortert;
//	}

	public Deltaker finnMobil(String mobil) {
		return deltakerrepo.findByMobil(mobil);
	}
	
	public Deltaker finnFornavn(String fornavn) {
		return deltakerrepo.findByFornavn(fornavn);
	}
}
