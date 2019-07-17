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

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.mall.config.AppConfig;
import com.cafe24.mall.config.TestWebConfig;
import com.cafe24.mall.repository.CustomerDao;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class CustomerControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.
				webAppContextSetup(webApplicationContext).
				build();
	}
	
	@After
	@Rollback(true)
	public void cleanup() {}
	
	
	/**
	 * 회원가입을 테스트 하는 메소드 (실패케이스 - 이름 2자 이상 , 10자 이하)
	 * @throws Exception 예외
	 */
	@Test
	public void a_testJoinFail_InputName() throws Exception{
		MemberVo vo = new MemberVo();
		vo.setName("김");
		vo.setAddress("서울시 성동구");
		vo.setBirthDate("1993-10-25");
		vo.setGender("m");
		vo.setId("skok10");
		vo.setPassword("1234");
		vo.setEmail("skok1025@naver.com");
		vo.setTel("01068669202");
		vo.setRegdate("2019-07-11");
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		
		resultActions
		.andExpect(status().isBadRequest())
		.andDo(print())
		;	
	}
	
	/**
	 * 회원가입을 테스트 하는 메소드 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void a_testJoinSuccess() throws Exception{
		MemberVo vo = new MemberVo();
		vo.setName("김석현");
		
		vo.setAddress("서울시 성동구");
		vo.setBirthDate("1993-10-25");
		vo.setGender("m");
		vo.setId("skok1025");
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
				.andExpect(jsonPath("$.data", is(1)))
				;	
	}
	
	/**
	 * 회원가입을 테스트 하는 메소드 (실패케이스 - 중복된 아이디)
	 * @throws Exception 예외
	 */
	@Test
	public void b_testJoinFail_Id() throws Exception{
		a_testJoinSuccess(); // 선행작업: 회원가입
		MemberVo vo = new MemberVo();
		vo.setName("김석현");
		vo.setAddress("서울시 성동구");
		vo.setBirthDate("1993-10-25");
		vo.setGender("m");
		vo.setId("skok1025");
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
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.message", is("이미 존재하는 아이디입니다.")))
				;	
	}
	

	/**
	 * 로그인 인증처리 테스트 (실패케이스1- 비밀번호 오류)
	 * @throws Exception 예외
	 */
	@Test
	public void c_testCheckAuthFailPw() throws Exception{
		a_testJoinSuccess(); // 선행작업: 회원가입
		
		MemberVo vo = new MemberVo();
		
		vo.setId("skok1025");  // 사용자 입력
		vo.setPassword("123");
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/customer/auth").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		;		
	}
	
	/**
	 * 로그인 인증처리 테스트 (실패케이스2- 존재하지 않는 아이디)
	 * @throws Exception 예외
	 */
	@Test
	public void c_testCheckAuthFailId() throws Exception{
		a_testJoinSuccess(); // 선행작업: 회원가입
		
		MemberVo vo = new MemberVo();
		vo.setId("skok102");  // 사용자 입력
		vo.setPassword("123");
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/customer/auth").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		;		
	}
	
	/**
	 * 로그인 인증처리 테스트 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void d_testCheckAuthSuccess() throws Exception{
		a_testJoinSuccess(); // 선행작업: 회원가입
		
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
	public void e_testCheckExistingId() throws Exception {
		a_testJoinSuccess(); // 선행작업: 회원가입
		
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
	public void f_testCheckNotExistingId() throws Exception {
		a_testJoinSuccess(); // 선행작업: 회원가입 skok1025
		
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
	 * 회원정보 수정을 테스트 하는 메소드 (실패케이스1- 이름이 2자 이하)
	 * @throws Exception 예외
	 */
	@Test
	public void g_testModifyAccount_Fail_Name() throws Exception{
		a_testJoinSuccess(); // 선행작업: 회원가입
		
		MemberVo vo = new MemberVo();
		// 수정할 회원번호
		vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
		vo.setNo(1L);
		// 수정내용
		vo.setName("김");
		vo.setAddress("서울시 성동구 성수동 2가");
		vo.setBirthDate("1993-10-25");
		vo.setGender("f");
		vo.setPassword("1234");
		vo.setEmail("skok1025@naver.com");
		vo.setTel("01068669202");
	
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
				
				resultActions
				.andExpect(status().isBadRequest())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("fail")))
				;	
	}
	/**
	 * 회원정보 수정을 테스트 하는 메소드 (실패케이스2- 이메일 형식 오류)
	 * @throws Exception 예외
	 */
	@Test
	public void h_testModifyAccount_Fail_Email() throws Exception{
		a_testJoinSuccess(); // 선행작업: 회원가입
		
		MemberVo vo = new MemberVo();
		// 수정할 회원번호
		vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
		vo.setNo(1L);
		// 수정내용
		vo.setName("김석현");
		vo.setAddress("서울시 성동구 성수동 2가");
		vo.setBirthDate("1993-10-25");
		vo.setGender("f");
		vo.setPassword("1234");
		vo.setEmail("skok102naver.com");
		vo.setTel("01068669202");
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		
		resultActions
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		;	
	}
	/**
	 * 회원정보 수정을 테스트 하는 메소드 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void i_testModifyAccount_Success() throws Exception{
		a_testJoinSuccess(); // 선행작업: 회원가입
		
		MemberVo vo = new MemberVo();
		
		// 수정할 회원번호
		vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
		//vo.setNo(1L); // 회원가입한 회원번호를 가져올 필요성 !!!
		// 수정내용
		vo.setName("김석현");
		vo.setAddress("서울시 성동구 성수동 2가");
		vo.setBirthDate("1993-10-25");
		vo.setGender("f");
		vo.setPassword("1234");
		vo.setEmail("skok1025@naver.com");
		vo.setTel("01068669202");
	
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
	 * 회원정보 수정(pw)을 테스트 하는 메소드 (실패케이스 - 기존 비밀번호가 아닌경우)
	 * @throws Exception 예외
	 */
	@Test
	public void j_testModifyAccountPw_Fail()  throws Exception{
		a_testJoinSuccess(); // 선행작업: 회원가입
		
		MemberVo vo = new MemberVo();
		// 수정할 회원번호
		vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
		vo.setNo(1L);
		
		vo.setPassword("1233424"); // 비번 : 1234
		vo.setNewPw("12345");
		vo.setConfirmPw("12345");
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/customer/account/pw").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
				
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.message", is("회원비번수정 실패")))
				.andExpect(jsonPath("$.data", not(is(1))))
				;	
		
	}
	
	/**
	 * 회원정보 수정(pw)을 테스트 하는 메소드 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void k_testModifyAccountPw_Success()  throws Exception{
		a_testJoinSuccess(); // 선행작업: 회원가입
		
		MemberVo vo = new MemberVo();
		
		
		// 수정할 회원번호
		vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
		//vo.setNo(1L);
		
		vo.setPassword("1234");
		vo.setNewPw("12345");
		vo.setConfirmPw("12345");
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/customer/account/pw").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
				
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.message", is("회원비번수정 완료")))
				.andExpect(jsonPath("$.data", is(1)))
				;	
		
	}
	
	/**
	 * 계정삭제 실패 테스트 (실패케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void l_testRemoveAccountFail() throws Exception{
		a_testJoinSuccess(); // 선행작업: 회원가입
		
		MemberVo vo = new MemberVo();
		vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
		//vo.setNo(1L);
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
	
	/**
	 * 계정삭제 성공 테스트 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	
	public void m_testRemoveAccountSuccess() throws Exception{
		a_testJoinSuccess(); // 선행작업: 회원가입
		
		MemberVo vo = new MemberVo();
		vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
		//vo.setNo(1L);
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
	
	
	
	
	
	
	

}
