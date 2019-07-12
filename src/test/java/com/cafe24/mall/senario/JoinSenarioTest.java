package com.cafe24.mall.senario;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
public class JoinSenarioTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/**
	 * 아이디 중복체크 -> (성공) -> 입력회원정보 저장 -> 성공
	 * @throws Exception 예외
	 */
	@Test
	public void testJoinSuccess() throws Exception {
		MemberVo vo = new MemberVo();
		vo.setId("skok1");  // 사용자 입력
		vo.setName("김석현");
		vo.setPassword("1234");
		vo.setAddress("서울시 성동구");
		vo.setBirthDate("1993-10-25");
		vo.setGender("m");
		vo.setEmail("skok1025@naver.com");
		vo.setTel("010-6866-9202");
		vo.setRegdate("2019-07-12");
		
		// 아이디 중복체크
		p1_checkId(vo.getId());
		// 계정 생성	
		p2_joinmember(vo);
	}
	
	/**
	 * 아이디 중복체크 -> (실패) -> x
	 * @throws Exception 예외
	 */
	@Test
	public void testJoinFail() throws Exception {
		MemberVo vo = new MemberVo();
		vo.setId("skok1025");  // 사용자 입력
		vo.setPassword("1234");
		vo.setAddress("서울시 성동구");
		vo.setBirthDate("1993-10-25");
		vo.setGender("m");
		vo.setEmail("skok1025@naver.com");
		vo.setTel("010-6866-9202");
		vo.setRegdate("2019-07-12");
		
		// 아이디 중복체크
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/customer/checkId?id={id}",vo.getId())).andExpect(status().isOk());
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data", not(is(0))));	
		
		// 계정 생성	
//		resultActions =
//				mockMvc
//				.perform(post("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
//		
//		resultActions
//		.andExpect(status().isOk())
//		.andDo(print())
//		.andExpect(jsonPath("$.result", is("success")))
//		.andExpect(jsonPath("$.data.id", is(vo.getId())))
//		;
	}
	
public void p1_checkId(String id) throws Exception{
	ResultActions resultActions =
			mockMvc
			.perform(get("/api/customer/checkId?id={id}",id)).andExpect(status().isOk());
			
			resultActions
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(0)));
	}

public void p2_joinmember(MemberVo vo) throws Exception{
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
	
	
	
	
}
