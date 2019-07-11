package com.cafe24.mall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.CustomerService;
import com.cafe24.mall.vo.MemberVo;

@RestController("CustomerAPIController")
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	

	@PostMapping("/auth")
	public JSONResult checkAuth(@RequestBody MemberVo membervo) {
		MemberVo authUser = customerService.getAuthUser(membervo); 
		
		return authUser != null ? JSONResult.success("로그인 성공",authUser) : JSONResult.fail("로그인 실패");
	}

	@GetMapping("/checkId")
	public JSONResult CheckId(String id) {
		int count = customerService.getIdCount(id);
		return count==0 ? JSONResult.success("사용가능한 아이디입니다.",count):JSONResult.fail("중복된 아이디입니다."); 
	}
	
	@PostMapping("/join")
	public JSONResult join(@RequestBody MemberVo memberVo) {
		MemberVo member = customerService.memberJoin(memberVo);
		return JSONResult.success("회원가입 완료",member);
	}
	/**
	 * 계정을 삭제하는 메소드
	 * @param memberVo 삭제할 회원번호, 비밀번호를 담은 VO
	 * @return 응답
	 */
	@DeleteMapping("/remove")
	public JSONResult removeAccount(@RequestBody MemberVo memberVo) {
		int result = customerService.removeAccount(memberVo);
		return result != 0 ? JSONResult.success("회원탈퇴 성공",result): JSONResult.fail("회원탈퇴 실패");
	}
	
	
}
