package com.cafe24.mall.controller.api;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.mall.config.AppConfig;
import com.cafe24.mall.config.TestWebConfig;
import com.cafe24.mall.vo.MemberVo;
import com.google.gson.Gson;


/**
 * 
 * @author BIT
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
public class CustomerControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.
				webAppContextSetup(webApplicationContext).
				build();
	}
	
	/**
	 * 로그인 인증처리 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testCheckAuth() throws Exception{
		MemberVo vo = new MemberVo();
		vo.setId("skok1025");  // 사용자 입력
		vo.setPassword("1234");
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/customer/auth").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
				
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.id", is(vo.getId())))
				;		
	}
	
	
	/**
	 * 존재하는 아이디('skok1025')의 아이디(사용불가)를 체크하는 테스트 
	 * @throws Exception 예외
	 */
	@Test
	public void testCheckExistingId() throws Exception {
		ResultActions resultActions =
		mockMvc
		.perform(get("/api/customer/checkId?id={id}","skok1025")).andExpect(status().isOk());
		
		resultActions
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data", not(is(0))));
	}
	
	/**
	 * 존재하지 않는 아이디('test')의 아이디(사용가능)를 체크하는 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testCheckNotExistingId() throws Exception {
		ResultActions resultActions =
		mockMvc
		.perform(get("/api/customer/checkId?id={id}","test")).andExpect(status().isOk());
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(0)));
	}
	
	/**
	 * 회원가입을 테스트 하는 메소드
	 * @throws Exception 예외
	 */
	@Test
	public void testJoin() throws Exception{
		MemberVo vo = new MemberVo();
		vo.setNo(1L);
		vo.setName("김석현");
		vo.setAddress("서울시 성동구");
		vo.setBirthDate("1993-10-25");
		vo.setGender("m");
		vo.setId("skok10251");
		vo.setPassword("1234");
		vo.setEmail("skok1025@naver.com");
		vo.setTel("01068669202");
		vo.setRegdate("2019-07-11");
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
				
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.id", is(vo.getId())))
				;	
	}
	
	/**
	 * 회원정보 수정을 테스트 하는 메소드
	 * @throws Exception 예외
	 */
	@Test
	public void testModifyAccount() throws Exception{
		MemberVo vo = new MemberVo();
		vo.setNo(1L);
		vo.setName("김석현");
		vo.setAddress("서울시 성동구 성수동");
		vo.setBirthDate("1993-10-25");
		vo.setGender("m");
		vo.setId("skok10251");
		vo.setPassword("1234");
		vo.setEmail("skok1025@naver.com");
		vo.setTel("01068669202");
		vo.setRegdate("2019-07-11");
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
				
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.message", is("회원정보수정 완료")))
				.andExpect(jsonPath("$.data", is(1)))
				;	
	}
	
	/**
	 * 계정삭제 성공 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testRemoveAccountSuccess() throws Exception{
		MemberVo vo = new MemberVo();
		vo.setNo(1L);
		vo.setPassword("1234"); // 사용자입력 
								// 실제 비밀번호 : 1234
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
				
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(1)))
				;		
	}
	
	/**
	 * 계정삭제 실패 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testRemoveAccountFail() throws Exception{
		MemberVo vo = new MemberVo();
		vo.setNo(1L);
		vo.setPassword("124"); // 사용자입력 
		// 실제 비밀번호 : 1234
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data", not(is(1))))
		;		
	}
	
	
	

}
