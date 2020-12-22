package com.bms.error.exception;

import com.bms.error.domain.ErrorBo;
import lombok.Getter;


@Getter
public final class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final ErrorBo errorBo;

	public ServiceException(String message) {
		super(message);
		this.errorBo = ErrorBo.builder().message(message).code("SERVER_ERROR").build();
	}

	public ServiceException(ErrorBo errorBo) {
		super(errorBo.getMessage());
		this.errorBo = errorBo;
	}
}