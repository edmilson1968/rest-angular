package br.com.edm.repositories;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8729512032804959478L;

	public ClienteNotFoundException(String exception) {
		super(exception);
	}

}