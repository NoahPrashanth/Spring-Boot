package com.july.boot.CRUD.exehandling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHndle {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	//@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, String>> handle(MethodArgumentNotValidException ex) {
		BindingResult rs = ex.getBindingResult();
		List<FieldError> error = rs.getFieldErrors();
		Map<String, String> map = new HashMap<>();
		error.forEach(e -> map.put(e.getField(), e.getDefaultMessage()));
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
	}
	
	

}
