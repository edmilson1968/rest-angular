package br.com.edm.controllers;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.edm.entities.Cliente;
import br.com.edm.repositories.ClienteNotFoundException;
import br.com.edm.services.ClienteService;

@RestController
@CrossOrigin(allowedHeaders="*")
@RequestMapping(value="/api")
public class ClienteController {

	@Autowired
    private ObjectMapper objectMapper;
	@Autowired
	private ClienteService clienteService;
	
	public ClienteController() {}
	
	@GetMapping(value="/clientes", produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<?> getClientes(@NotNull final Pageable pageable) {
		Page<Cliente> clientes = clienteService.findAll(pageable);
		return new ResponseEntity<Page<Cliente>>(clientes, HttpStatus.OK);
	}

	@GetMapping(value="/clientes/{nome}", produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<?> getOneCliente(@PathVariable String nome) {
		 Optional<Cliente> cliente = clienteService.getCliente(nome);
		 if (!cliente.isPresent()) {
			 throw new ClienteNotFoundException("nome-" + nome);
		 } else {
			 return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
		 }
	}

	@PostMapping("/clientes")
	public ResponseEntity<?> addCliente(@RequestBody String cliente) {
		
		Cliente oCli = null;
		try {
			oCli = objectMapper.readValue(cliente, Cliente.class);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Cliente newCliente = clienteService.addCliente(oCli);
		if (newCliente != null) {
			return new ResponseEntity<Cliente>(newCliente, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/clientes/{nome}")
	public ResponseEntity<?> updateCliente(@RequestBody String novoCliente, @PathVariable String nome) {
		if (novoCliente == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (nome == null || nome.isEmpty())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Cliente oCli = null;
		try {
			oCli = objectMapper.readValue(novoCliente, Cliente.class);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Cliente cli = clienteService.updateCliente(oCli, nome);
		return new ResponseEntity<Cliente>(cli, HttpStatus.OK);
	}

	@DeleteMapping("/clientes/{nome}")
	public ResponseEntity<?> deleteCliente(@PathVariable String nome) {
		if (nome == null || nome.isEmpty())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		try {
			clienteService.deleteCliente(nome);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
