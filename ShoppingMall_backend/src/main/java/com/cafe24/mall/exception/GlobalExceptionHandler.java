package com.cafe24.mall.exception;

import static org.mockito.Matchers.startsWith;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.mall.dto.JSONResult;

/**
 * GlobalExceptionHandler 백엔드 에서 에러가 발생하면 요청하는 쪽으로 에러 상태값을 보내준다
 * @author 김석현
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	//무결성 제약조건 예외
	@ExceptionHandler(Exception.class)
	   public ResponseEntity<JSONResult> Exception(Exception e) {
	      System.out.println("------------------------내부 에러------------------------");
	      System.out.println(e);
	      JSONResult result = JSONResult.fail("내부 에러");
	      ResponseEntity<JSONResult> status = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
	      System.out.println(status);
	      return status;   
	   }
	@ExceptionHandler(NullPointerException.class)
	   public ResponseEntity<JSONResult> NullPointException() {
	      System.out.println("------------------------Null point exception------------------------");
	      JSONResult result = JSONResult.fail("null point exception");
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
	   }
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<JSONResult> ArithmeticException() {
		System.out.println("------------------------Arithmetic exception------------------------");
		JSONResult result = JSONResult.fail("arithmetic exception");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<JSONResult> dataIntegrityViolationException() {
		System.out.println("------------------------무결성 제약조건 위반------------------------");
		JSONResult result = JSONResult.fail("무결성 제약조건 위반");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
	}
}

