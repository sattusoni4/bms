package com.bms.error.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Getter
public class ErrorBo {

	private String code;
	private String message;
	private HttpStatus status;
	private List<ErrorDetailBo> details;
}