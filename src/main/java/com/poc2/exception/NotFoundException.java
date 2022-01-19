package com.poc2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private @Getter String msg;
	
	public NotFoundException(String msg) {
		super(msg);
		this.msg = msg;
		
	}

}
