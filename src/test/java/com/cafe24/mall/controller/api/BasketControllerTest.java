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
	
	
	@Test
	public void testAddBasket() throws Exception{
		
		String basketCode = "a12";
		Long goodsDetailNo = 1L;
		
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/basket/add/1").contentType(MediaType.APPLICATION_JSON)
						.param("basketCode", basketCode)
						);
		
				
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				;	
	}
	
	@Test
	public void testListBasket() throws Exception{
		String basketCode = "a12";
		
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/basket/list").contentType(MediaType.APPLICATION_JSON)
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
