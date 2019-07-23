package com.cafe24.mall.senario;

import static org.hamcrest.Matchers.is;
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
public class LoginSenarioTest {
	
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
	 * 테스트케이스1(성공): 존재하는 아이디 (skok1025)에 일치하는 비밀번호(1234) 를 입력한 경우 
	 * @throws Exception 예외
	 */
	@Test
	public void testLoginSuccess() throws Exception {
		MemberVo vo = new MemberVo();
		vo.setId("skok1025");  // 사용자 입력
		vo.setPassword("1234");
		
		p1_auth(vo,"success");		
	}
	
	/**
	 * 테스트케이스2(실패):존재하지 않는 아이디 (noid) 를 입력하였을 경우 
	 * @throws Exception 예외
	 */
	@Test
	public void testLoginFailId() throws Exception{
		MemberVo vo = new MemberVo();
		vo.setId("noid");  // 사용자 입력
		vo.setPassword("1234");
		
		p1_auth(vo,"fail");	
	}
	
	/**
	 * 테스트케이스3(실패):존재하는 아이디 (skok1025) 에 일치하지 않은 비밀번호 (12345) 입력한 경우 -> 실패
	 * @throws Exception
	 */
	@Test
	public void testLoginFailPw() throws Exception{
		MemberVo vo = new MemberVo();
		vo.setId("skok1025");  // 사용자 입력
		vo.setPassword("12345");
		
		p1_auth(vo,"fail");	
	}
	
	
	
////////////////////////////////각 테스트 케이스에 사용될 로그인 Process
	/**
	 * 로그인 진행프로세스 : 인증
	 * @param vo 인증이 필요한 MemberVo
	 * @throws Exception 예외
	 */
	public void p1_auth(MemberVo vo,String isSuccess) throws Exception{
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/customer/auth").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
				
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is(isSuccess)))
				//.andExpect(jsonPath("$.data.id", is(vo.getId())))
				;		
	}

}
