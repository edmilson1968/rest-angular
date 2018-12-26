package br.com.edm.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.edm.entities.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente,String> {

}
