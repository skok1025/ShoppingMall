package com.cafe24.mall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
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
import com.cafe24.mall.repository.AdminDao;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MemberVo;
import com.google.gson.Gson;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class AdminControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private AdminDao adminDao;
	
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
	 * 관리자가 1차 카테고리 (상의)를 등록하는 테스트메소드 (성공케이스) 
	 * @throws Exception 예외
	 */
	@Test
	public void testAddBigCategory_Success() throws Exception{
		BigCategoryVo vo = new BigCategoryVo();
		vo.setName("상의");
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	
	/**
	 * 관리자가 1차 카테고리 (상의 -> 윗옷) 으로 수정하는 테스트 메소드 (성공케이스)
	 * @throws Exception
	 */
	@Test
	public void testModifyBigCategory_Success() throws Exception{
		testAddBigCategory_Success(); // 선행작업 : 1차 카테고리 등록 (상의)
		
		BigCategoryVo vo = new BigCategoryVo();
		vo.setNo(adminDao.getCurrentInsertBigCategoryNo());
		vo.setName("윗옷");
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	
	/**
	 * 관리자가 없는 1차 카테고리 번호를 (x -> 윗옷) 으로 수정하는 테스트 메소드 (실패케이스)
	 * @throws Exception
	 */
	@Test
	public void testModifyBigCategory_Fail() throws Exception{
		testAddBigCategory_Success(); // 선행작업 : 1차 카테고리 등록 (상의)
		
		BigCategoryVo vo = new BigCategoryVo();
		vo.setNo(1000L);// 1000번의 상품번호는 없음
		vo.setName("윗옷");
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	
	
	@Test
	public void testRemoveBigCategory() throws Exception{
		testAddBigCategory_Success(); // 선행작업 : 1차 카테고리 등록 (상의)
		
		BigCategoryVo vo = new BigCategoryVo();
		vo.setNo(adminDao.getCurrentInsertBigCategoryNo());
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	
	
	
	
	
	@Test
	public void testGoodsList() throws Exception{
		mockMvc
		.perform(get("/api/admin/goodslist")).andExpect(status().isOk());
	}
	

	/**
	 * 관리자가 상품을 등록하는 테스트 하는 메소드
	 * @throws Exception 예외
	 */
	@Test
	public void testGoodsAdd() throws Exception{
		
		GoodsVo goodsvo = new GoodsVo();
		
		goodsvo.setNo(1L);
		goodsvo.setName("테스트 상품");
		goodsvo.setGoodsImagesList(Arrays.asList(
				new GoodsImagesVo(1L,"메인이미지"),
				new GoodsImagesVo(2L,"테스트이미지1"),
				new GoodsImagesVo(3L,"테스트이미지2"),
				new GoodsImagesVo(4L,"테스트이미지3")
		));
		
		goodsvo.setGoodsDetailList(Arrays.asList(
				new GoodsDetailVo(1L,1L,"black/90",5,5),
				new GoodsDetailVo(2L,1L,"black/95",5,5),
				new GoodsDetailVo(3L,1L,"black/100",5,4)				
		));
		
		
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/admin/goods").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(goodsvo)));
		
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				;	
	}
	
	
	/**
	 * 관리자가 상품을 수정하는 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testModifyGoodsInfo() throws Exception {
		GoodsVo vo = new GoodsVo();
		vo.setNo(1L);
		vo.setDetail("수정한 상품디테일입니다.");
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/admin/goods").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				;	
	}
	
	/**
	 * 관리자가 상품을 삭제하는 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testRemoveGoodsInfo() throws Exception {
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/admin/goods").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(1L)));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		;	
	}
	
	
	
}
