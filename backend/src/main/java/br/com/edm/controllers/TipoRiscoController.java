package br.com.edm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.edm.entities.TipoRiscoEnum;

@RestController
@CrossOrigin(allowedHeaders="*")
@RequestMapping("/api")
public class TipoRiscoController {

	private List<TipoRiscoEnum> riscos;
	
	public TipoRiscoController() {
		riscos = new ArrayList<>();
		riscos.add(TipoRiscoEnum.A);
		riscos.add(TipoRiscoEnum.B);
		riscos.add(TipoRiscoEnum.C);
	}
	
	@GetMapping("/riscos")
	public ResponseEntity<List<TipoRiscoEnum>> getTiposRisco() {
		return new ResponseEntity<List<TipoRiscoEnum>>(riscos, HttpStatus.OK) {} ;
	}
	
	@GetMapping("/riscos/{id}")
	public ResponseEntity<TipoRiscoEnum> getOneTipoRisco(@PathVariable String id) {
		TipoRiscoEnum risco = TipoRiscoEnum.getByTipo(id);
		
		if (risco == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<TipoRiscoEnum>(risco, HttpStatus.OK);
	}
}
