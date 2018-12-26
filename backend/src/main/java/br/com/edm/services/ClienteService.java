package br.com.edm.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.edm.entities.Cliente;
import br.com.edm.repositories.ClienteRepository;

@Service
@Validated
public class ClienteService {

	@Autowired
 	private ClienteRepository clienteRepository;
	
	public ClienteService() {}
	
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	public Optional<Cliente> getCliente(String nome) {
		return clienteRepository.findById(nome);
	}
	
	public Cliente addCliente(@Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente updateCliente(@Valid Cliente novoCliente, String nome) {
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
	
	public void deleteCliente(String nome) throws Exception {
		try {
			clienteRepository.deleteById(nome);
		} catch (Exception e) {
			throw new Exception("Erro ao apagar o cliente " + nome);
		}
	}

}
