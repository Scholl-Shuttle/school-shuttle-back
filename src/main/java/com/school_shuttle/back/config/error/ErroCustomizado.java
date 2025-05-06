package com.school_shuttle.back.config.error;

public class ErroCustomizado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroCustomizado(String message) {
		super(message);
	}

}
