package com.cafe24.mall.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.mall.dto.JSONResult;

@ControllerAdvice
public class GlobalExceptionHandler {

	//무결성 제약조건 예외
	   @ExceptionHandler(DataIntegrityViolationException.class)
	   public ResponseEntity<JSONResult> dataIntegrityViolationException() {
	      System.out.println("------------------------무결성 제약조건 위반------------------------");
	      JSONResult result = JSONResult.fail("무결성 제약조건 위반");
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
	   }
}

