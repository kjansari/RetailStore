package com.example.demo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.ErrorDetail;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		return new ResponseEntity<Object>(new ErrorDetail(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleExceptionFromApp(Exception ex) {
		log.error(ex.getMessage());
		return new ResponseEntity<Object>(new ErrorDetail(ex.getMessage()), new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
