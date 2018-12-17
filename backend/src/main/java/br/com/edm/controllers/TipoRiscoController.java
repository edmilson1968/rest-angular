package br.com.edm.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.edm.entities.TipoRisco;
import br.com.edm.repositories.TipoRiscoRepository;

@RestController
@CrossOrigin(allowedHeaders="*")
@RequestMapping("/api")
public class TipoRiscoController {

	@Autowired
	private TipoRiscoRepository riscoRepository;
	
	public TipoRiscoController() {}
	
	@GetMapping("/riscos")
	public Iterable<TipoRisco> getTiposRisco() {
		return riscoRepository.findAll(Sort.by("tipo").ascending());
	}

	@GetMapping("/riscos/{id}")
	public Optional<TipoRisco> getOneTipoRisco(@PathVariable String id) {
		return riscoRepository.findById(id);
	}

	@PostMapping("/riscos")
	public TipoRisco addTiposRisco(TipoRisco risco) {
		return riscoRepository.save(risco);
	}


}
