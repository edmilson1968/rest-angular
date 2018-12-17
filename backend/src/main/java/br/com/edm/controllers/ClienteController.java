package br.com.edm.controllers;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.edm.entities.Cliente;
import br.com.edm.repositories.ClienteRepository;

@RestController
@CrossOrigin(allowedHeaders="*")
@RequestMapping(value="/api")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteController() {}
	
	@GetMapping(value="/clientes", produces = {MediaType.APPLICATION_JSON_VALUE} )
	public Page<Cliente> getClientes(@NotNull final Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	@GetMapping(value="/clientes/{nome}", produces = {MediaType.APPLICATION_JSON_VALUE} )
	public Optional<Cliente> getOneCliente(@PathVariable String nome) {
		return clienteRepository.findById(nome);
	}

	@PostMapping("/clientes")
	public Cliente addCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@PutMapping("/clientes/{nome}")
	public Cliente updateCliente(@RequestBody Cliente novoCliente, @PathVariable String nome) {
		return clienteRepository.findById(nome)
				.map(cliente -> {
					cliente.setNome(novoCliente.getNome());
					cliente.setLimite(novoCliente.getLimite());
					cliente.setRisco(novoCliente.getRisco());
					return clienteRepository.save(cliente);
				})
				.orElseGet(() -> {
					novoCliente.setNome(nome);
					return clienteRepository.save(novoCliente);
				});
	}

	@DeleteMapping("/clientes/{nome}")
	public void deleteCliente(@PathVariable String nome) {
		clienteRepository.deleteById(nome);
	}


}
