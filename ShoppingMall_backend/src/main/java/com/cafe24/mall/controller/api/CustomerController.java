package com.cafe24.mall.controller.api;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.CustomerService;
import com.cafe24.mall.vo.MemberVo;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 회원계정 관련 컨트롤러 클래스
 * 
 * @author 김석현
 *
 */
@RestController("CustomerAPIController")
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private MessageSource messageSource;
	
	/**
	 * 로그인하는 메소드
	 * 
	 * @param membervo 로그인 정보를 담은 membervo (id, password)
	 * @return 응답
	 */
	@ApiOperation(value = "로그인")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "membervo", value = "로그인 정보를 담은 membervo", required = true, dataType = "MemberVo", defaultValue = "") })
	@PostMapping("/auth")
	public ResponseEntity<JSONResult> checkAuth(@RequestBody MemberVo membervo) {
		// 로그인할 때, 장바구니를 담았다면 브라우저 쿠키에 담긴  basketCode으로
		// tblCustomerBasketCode 의 member_no 를 업데이트?
		
		MemberVo authUser = customerService.getAuthUser(membervo);

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<MemberVo>> validatorResults = validator.validateProperty(membervo, "id");

		if (!validatorResults.isEmpty()) {
			for (ConstraintViolation<MemberVo> validatorResult : validatorResults) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(JSONResult.fail(validatorResult.getMessage()));
			}
		}

		return authUser != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("로그인 성공", authUser))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("로그인 실패"));
	}

	/**
	 * 아이디 중복체크하는 메소드
	 * 
	 * @param id 중복체크를 할 아이디
	 * @return 응답
	 */
	@ApiOperation(value = "아이디 중복 체크")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "중복체크를 할 아이디", required = true, dataType = "String", defaultValue = "") })
	@GetMapping("/checkId")
	public ResponseEntity<JSONResult> CheckId(String id) {
		int count = customerService.getIdCount(id);
		return count == 0 ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("사용가능한 아이디입니다.", count))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("중복된 아이디입니다.",count));
	}

	/**
	 * 회원가입하는 메소드
	 * 
	 * @param memberVo 회원가입 VO
	 * @return 응답
	 */
	@ApiOperation(value = "회원가입 (계정 정보 생성)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberVo", value = "회원가입하는 정보 MemberVo", required = true, dataType = "MemberVo", defaultValue = "") })
	@PostMapping("/account")
	public ResponseEntity<JSONResult> join(@RequestBody @Valid MemberVo memberVo, BindingResult bindresult) {

		// 아이디 중복 체크
		if (customerService.getIdCount(memberVo.getId()) != 0) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("이미 존재하는 아이디입니다."));
		}

		// 유효성 검사 실패시
		if (bindresult.hasErrors()) {
			List<FieldError> list = bindresult.getFieldErrors();
			String errMsg = "";
			for (FieldError err : list) {
				errMsg += err.getField() + "/";
			}
			errMsg += "오류발생";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errMsg));
		}

		int result = customerService.memberJoin(memberVo);
		return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("회원가입 완료", result));
	}

	/**
	 * 회원정보수정하는 메소드
	 * 
	 * @param memberVo 회원정보수정 vo
	 * @return 응답
	 */
	@ApiOperation(value = "회원정보수정 (계정 정보 수정)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberVo", value = "회원정보 수정한 MemberVo", required = true, dataType = "MemberVo", defaultValue = "") })
	@PutMapping("/account")
	public ResponseEntity<JSONResult> modifyAccount(@RequestBody MemberVo memberVo) {

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		Set<ConstraintViolation<MemberVo>> validatorResults = validator.validateProperty(memberVo, "name");

		if (validatorResults.isEmpty() == false) {
			for (ConstraintViolation<MemberVo> validatorResult : validatorResults) {
				String message = validatorResult.getMessage();
				//String message = messageSource.getMessage("Email.userVo.email", null, LocaleContextHolder.getLocale());
				JSONResult result = JSONResult.fail(message);

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}

		}
		
		validatorResults = validator.validateProperty(memberVo, "email");

		if (validatorResults.isEmpty() == false) {
			for (ConstraintViolation<MemberVo> validatorResult : validatorResults) {
				String message = validatorResult.getMessage();
				//String message = messageSource.getMessage("Email.userVo.email", null, LocaleContextHolder.getLocale());
				JSONResult result = JSONResult.fail(message);

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}

		}

		int result = customerService.modifyAccount(memberVo);
		return result == 1 ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("회원정보수정 완료", result)) 
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("회원정보수정 실패"));
	}
	
	/**
	 * 회원비밀번호 수정하는 메소드
	 * 
	 * @param memberVo 회원번호,회원 기존 비밀번호,회원 새로운 비밀번호, 회원 새로운 비밀번호 확인 포함 MemberVo
	 * @return 응답
	 */
	@ApiOperation(value = "회원정보수정 (계정 비밀번호 정보 수정)")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "memberVo", value = "회원번호,회원 기존 비밀번호,회원 새로운 비밀번호, 회원 새로운 비밀번호 확인 포함 MemberVo", required = true, dataType = "MemberVo", defaultValue = "")})
	@PutMapping("/account/pw")
	public ResponseEntity<JSONResult> modifyAccountPassword(
			@RequestBody MemberVo memberVo) {

		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		Set<ConstraintViolation<MemberVo>> validatorResults = validator.validateProperty(memberVo, "password");

		if (validatorResults.isEmpty() == false) {
			for (ConstraintViolation<MemberVo> validatorResult : validatorResults) {
				String message = validatorResult.getMessage();
				//String message = messageSource.getMessage("Email.userVo.email", null, LocaleContextHolder.getLocale());
				JSONResult result = JSONResult.fail(message);

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}

		}
		
		int result = customerService.modifyAccountPw(memberVo);
		return result == 1 ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("회원비번수정 완료", result)) 
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("회원비번수정 실패"));
	}
	
	

	/**
	 * 계정을 삭제하는 메소드
	 * 
	 * @param memberVo 삭제할 회원번호, 비밀번호를 담은 VO
	 * @return 응답
	 */
	@ApiOperation(value = "회원탈퇴(계정정보 삭제)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberVo", value = "비밀번호를 포함한 MemberVo", required = true, dataType = "MemberVo", defaultValue = "") })
	@DeleteMapping("/account")
	public JSONResult removeAccount(@RequestBody MemberVo memberVo) {
		int result = customerService.removeAccount(memberVo);
		return result != 0 ? JSONResult.success("회원탈퇴 성공", result) : JSONResult.fail("회원탈퇴 실패");
	}

	


	
	

}
