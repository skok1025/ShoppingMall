package com.cafe24.mall.controller.api;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
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
 * 고객 컨트롤러 클래스
 * @author 김석현
 *
 */
@RestController("CustomerAPIController")
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	

	/**
	 * 로그인하는 메소드
	 * @param membervo 로그인 정보를 담은 membervo (id, password)
	 * @return 응답
	 */
	@ApiOperation(value = "로그인")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "membervo", value = "로그인 정보를 담은 membervo", required = true, dataType = "MemberVo", defaultValue = "") 
	})
	@PostMapping("/auth")
	public ResponseEntity<JSONResult> checkAuth(@RequestBody MemberVo membervo) {
		MemberVo authUser = customerService.getAuthUser(membervo); 
		
		Validator validator =Validation.buildDefaultValidatorFactory()
.getValidator();
		Set<ConstraintViolation<MemberVo>> validatorResults = validator.validateProperty(membervo, "id");
		
		if(!validatorResults.isEmpty()) {
			for(ConstraintViolation<MemberVo> validatorResult : validatorResults) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(validatorResult.getMessage()));
			}
		}
		
		return authUser != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("로그인 성공",authUser)) : ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("로그인 실패"));
	}

	
	/**
	 * 아이디 중복체크하는 메소드
	 * @param id 중복체크를 할 아이디
	 * @return 응답
	 */
	@ApiOperation(value = "아이디 중복 체크")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "중복체크를 할 아이디", required = true, dataType = "String", defaultValue = "") 
	})
	@GetMapping("/checkId")
	public JSONResult CheckId(String id) {
		int count = customerService.getIdCount(id);
		return count==0 ? JSONResult.success("사용가능한 아이디입니다.",count):JSONResult.fail("중복된 아이디입니다."); 
	}
	
	/**
	 * 회원가입하는 메소드
	 * @param memberVo 회원가입 VO
	 * @return 응답
	 */
	@ApiOperation(value = "회원가입 (계정 정보 생성)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberVo", value = "회원가입하는 정보 MemberVo", required = true, dataType = "MemberVo", defaultValue = "") 
	})
	@PostMapping("/account")
	public JSONResult join(
			@RequestBody @Valid MemberVo memberVo,
			BindingResult result) {
		
		// 아이디 중복 체크
		if(customerService.getIdCount(memberVo.getId())==1) {
			return JSONResult.fail("이미 존재하는 아이디입니다.");
		}
		
		// 유효성 검사 실패시
		if(result.hasErrors()) {
			List<FieldError> list = result.getFieldErrors();
			String errMsg = "";
			for(FieldError err : list) {
				errMsg += err.getField()+"/";
			}
			errMsg += "오류발생";
			return JSONResult.fail(errMsg);
		}
		
		MemberVo member = customerService.memberJoin(memberVo);
		return JSONResult.success("회원가입 완료",member);
	}
	
	/**
	 * 회원정보수정하는 메소드
	 * @param memberVo 회원정보수정 vo
	 * @return 응답
	 */
	@ApiOperation(value = "회원정보수정 (계정 정보 수정)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberVo", value = "회원정보 수정한 MemberVo", required = true, dataType = "MemberVo", defaultValue = "") 
	})
	@PutMapping("/account")
	public JSONResult modifyAccount(@RequestBody MemberVo memberVo) {
		int result = customerService.modifyAccount(memberVo);
		return result == 1 ? JSONResult.success("회원정보수정 완료",result) : JSONResult.fail("회원정보수정 실패");
	}
	
	
	/**
	 * 계정을 삭제하는 메소드
	 * @param memberVo 삭제할 회원번호, 비밀번호를 담은 VO
	 * @return 응답
	 */
	@ApiOperation(value = "회원탈퇴(계정정보 삭제)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberVo", value = "비밀번호를 포함한 MemberVo", required = true, dataType = "MemberVo", defaultValue = "") 
	})
	@DeleteMapping("/account")
	public JSONResult removeAccount(@RequestBody MemberVo memberVo) {
		int result = customerService.removeAccount(memberVo);
		return result != 0 ? JSONResult.success("회원탈퇴 성공",result): JSONResult.fail("회원탈퇴 실패");
	}
	
	
}
