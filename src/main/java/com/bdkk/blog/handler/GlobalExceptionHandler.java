package com.bdkk.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.bdkk.blog.dto.ResponseDto;

@ControllerAdvice // 어디로든 익셉션이 발생하면 이쪽으로 오게하면 이쪽으로 오게된다.
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value() ,e.getMessage());
	}
	
}
