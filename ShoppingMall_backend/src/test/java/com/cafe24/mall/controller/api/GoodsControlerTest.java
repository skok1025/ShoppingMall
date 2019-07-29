package com.cafe24.mall.controller.api;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.cafe24.mall.vo.MemberVo;
import com.google.gson.Gson;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
//@Transactional
public class GoodsControlerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.
				webAppContextSetup(webApplicationContext).
				build();
	}
	
	@After
	//@Rollback(true)
	public void cleanup() {}
	
	/**
	 * 키워드('반팔')를 검색 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testSearch() throws Exception {
		//AdminControllerTest admin = new AdminControllerTest();
		//admin.testGoodsAdd_Success();
		
		ResultActions resultActions =
		mockMvc
		.perform(get("/api/goods/search?kw=반팔"));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is("키워드:1")))
		;
	}
	/**
	 * 2차 카테고리 번호(1L)를 탐색 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testSearchByCategory() throws Exception {
		//AdminControllerTest admin = new AdminControllerTest();
		//admin.testGoodsAdd_Success();
		
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/category").param("smallcategoryNo", "1"));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is("키워드:1")))
		;
	}
	/**
	 * 상품 메인질열조회 탐색 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testGetMainDisplayList() throws Exception {
		//AdminControllerTest admin = new AdminControllerTest();
		//admin.testGoodsAdd_Success();
		
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/maindisplay").param("maindisplayNo", "2"));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is("키워드:1")))
		;
	}
	
	/**
	 * 상품번호 2번의 메인이미지를 얻어오는 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testgetMainImage() throws Exception {
		//AdminControllerTest admin = new AdminControllerTest();
		//admin.testGoodsAdd_Success();
		
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/mainimage/2"));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is("키워드:1")))
		;
	}
	/**
	 * 상품번호 2번의 서브이미지리스트를 얻어오는 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testgetSubimagelist() throws Exception {
		//AdminControllerTest admin = new AdminControllerTest();
		//admin.testGoodsAdd_Success();
		
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/subimagelist/2"));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is("키워드:1")))
		;
	}
	
	
	/**
	 * 상품번호 2번의 상품상세 리스트를 얻어오는 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testgetGoodsDetailList() throws Exception {
		//AdminControllerTest admin = new AdminControllerTest();
		//admin.testGoodsAdd_Success();
		
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/goodsdetail/2"));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is("키워드:1")))
		;
	}
	
	
	
	
	/**
	 * 상품상세조회 (상품상세번호 : 1) 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testView() throws Exception {
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/view/{goodDetailNo}",1L)).andExpect(status().isOk());
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is("상품상세번호:1")))
		;
	}
	
	
	
	
	

}
