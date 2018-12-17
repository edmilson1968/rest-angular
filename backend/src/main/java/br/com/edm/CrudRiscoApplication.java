package br.com.edm;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.edm.entities.TipoRisco;
import br.com.edm.repositories.TipoRiscoRepository;

@SpringBootApplication
public class CrudRiscoApplication implements CommandLineRunner {

	@Autowired
	private TipoRiscoRepository riscoRepository;
//	@Autowired
//	private ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudRiscoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<TipoRisco> risco = riscoRepository.findById("A");
		if (! risco.isPresent())
			riscoRepository.save(new TipoRisco("A", new BigDecimal("0.0")));
		
		risco = riscoRepository.findById("B");
		if (! risco.isPresent())
			riscoRepository.save(new TipoRisco("B", new BigDecimal("0.1")));
		
		risco = riscoRepository.findById("C");
		if (! risco.isPresent())
			riscoRepository.save(new TipoRisco("C", new BigDecimal("0.2")));
		
//		Optional<Cliente> cliente = clienteRepository.findById("Edmilson");
//		if (! cliente.isPresent())
//			clienteRepository.save(
//					new Cliente("Edmilson"
//					, new BigDecimal("10000.00")
//					, riscoRepository.findById("B").get())
//			);
	}

	
}

