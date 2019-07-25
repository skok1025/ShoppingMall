package com.cafe24.mall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.cafe24.mall.vo.BasketVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MemberVo;
import com.google.gson.Gson;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
public class BasketControllerTest {
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
	 * 회원이 상품을 장바구니에 추가하는 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testAddBasket() throws Exception{
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/basket/member/add")
						.param("memberNo", "1")
						.param("goodsDetailNo", "1")
						.param("cnt", "1")
						.contentType(MediaType.APPLICATION_JSON)
						);
		
				
				resultActions
				.andExpect(status().isCreated())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				;	
	}
	
	/**
	 * 비회원이 상품을 장바구니에 추가하는 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testNonmemberAddBasket() throws Exception{
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/basket/nonmember/add")
						.param("basketCode", "c-101")
						.param("goodsDetailNo", "1")
						.param("cnt", "1")
						.contentType(MediaType.APPLICATION_JSON)
						);
		
		
		resultActions
		.andExpect(status().isCreated())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		;	
	}
	
	@Test
	public void testMemberBasketList() throws Exception{
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/basket/member/list").contentType(MediaType.APPLICATION_JSON)
						.param("memberNo", "1")
						);
		
				
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				;
	}
	@Test
	public void testNonMemberBasketList() throws Exception{
		String basketCode = "1";
		
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/basket/nonmember/list").contentType(MediaType.APPLICATION_JSON)
						.param("basketCode", basketCode)
						);
		
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		;
	}
	
	@Test
	public void testModifyBasketInfo() throws Exception{
		BasketVo vo = new BasketVo();
		vo.setNo(1L);
		vo.setCnt(4);
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/basket/modify").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				;
	}
	
	@Test
	public void testRemoveBasketGoods() throws Exception{
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/basket/remove/2").contentType(MediaType.APPLICATION_JSON));
		
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				;
	}
}
