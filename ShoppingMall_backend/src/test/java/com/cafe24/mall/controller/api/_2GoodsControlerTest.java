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

import java.util.Arrays;
import java.util.List;

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
import com.cafe24.mall.repository.AdminDao;
import com.cafe24.mall.service.AdminService;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MemberVo;
import com.cafe24.mall.vo.SmallCategoryVo;
import com.google.gson.Gson;


/**
 * 상품관련 컨트롤러 테스트 클래스
 * @author 김석현
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
@Transactional
public class _2GoodsControlerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AdminService adminService;
	
	@Before
	@Rollback(true)
	public void setUp() {
		mockMvc = MockMvcBuilders.
				webAppContextSetup(webApplicationContext).
				build();
	}
	
	@After
	@Rollback(true)
	public void cleanup() {}
	
	
	/**
	 * 전체 상품 리스트를 조회 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testList() throws Exception {
		addCategory("남성의류", Arrays.asList("카라티","후드티","반팔"));
		addGoods();
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/list/0"));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is("키워드:1")))
		;
	}
	/**
	 * 키워드('반팔')를 검색 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testSearch() throws Exception {
		addCategory("남성의류", Arrays.asList("카라티","후드티","반팔"));
		addGoods();
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
		addCategory("남성의류", Arrays.asList("카라티","후드티","반팔"));
		addGoods();
		
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/category").param("smallcategoryNo", adminDao.getCurrentInsertSmallCategoryNo()+""));
		
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
		addCategory("남성의류", Arrays.asList("카라티","후드티","반팔"));
		addGoods();
		addMainDisplay();
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/maindisplay").param("maindisplayNo", adminDao.getCurrentInsertMaindisplayVo().getMaindisplayNo()+""));
		
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
		addCategory("남성의류", Arrays.asList("카라티","후드티","반팔"));
		addGoods();
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/mainimage/"+adminDao.getCurrentInsertGoodsNo()));
		
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
		addCategory("남성의류", Arrays.asList("카라티","후드티","반팔"));
		addGoods();
		
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/subimagelist/"+adminDao.getCurrentInsertGoodsNo()));
		
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
		addCategory("남성의류", Arrays.asList("카라티","후드티","반팔"));
		addGoods();
		
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/goodsdetail/"+adminDao.getCurrentInsertGoodsNo()));
		
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
		addCategory("남성의류", Arrays.asList("카라티","후드티","반팔"));
		addGoods();
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/goods/view/{goodDetailNo}",adminDao.getCurrentInsertGoodsNo())).andExpect(status().isOk());
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is("상품상세번호:1")))
		;
	}
	
	
	
	public void addCategory(String bigCategoryName, List<String> smallCategoryNames) {
		BigCategoryVo bvo = new BigCategoryVo();
		bvo.setName(bigCategoryName);
		
		adminService.addBigCatergory(bvo);
		
		for(String smallCategoryName:smallCategoryNames) {
			SmallCategoryVo vo = new SmallCategoryVo();
			vo.setName(smallCategoryName);
			vo.setBigcategoryNo(adminDao.getCurrentInsertBigCategoryNo());
			
			adminService.addSmallCatergory(vo);
		}
	}
	

	public void addGoods() {
		// 선행작업 상품등록
				GoodsVo goodsvo = new GoodsVo();
				
				goodsvo.setName("테스트 상품3");
				goodsvo.setSeillingPrice(15000);
				goodsvo.setDetail("많은 설명");
				goodsvo.setDisplayStatus(GoodsVo.status.y);
				goodsvo.setSeillingStatus(GoodsVo.status.y);
				goodsvo.setManufacturer("제조업자명");
				goodsvo.setSupplier("공급업자명");
				goodsvo.setManufacturingDate("2019-07-19");
				goodsvo.setOrigin("원산지명");
				goodsvo.setSmallcategoryNo(adminDao.getCurrentInsertSmallCategoryNo());
				
				goodsvo.setGoodsImagesList(Arrays.asList(
						new GoodsImagesVo("메인이미지",GoodsImagesVo.status.y),
						new GoodsImagesVo("테스트이미지1",GoodsImagesVo.status.n),
						new GoodsImagesVo("테스트이미지2",GoodsImagesVo.status.n),
						new GoodsImagesVo("테스트이미지3",GoodsImagesVo.status.n)
				));
				
				goodsvo.setGoodsDetailList(Arrays.asList(
						new GoodsDetailVo("black/90",5,5),
						new GoodsDetailVo("black/95",5,5),
						new GoodsDetailVo("black/100",5,4)				
				));
				
				adminService.addGoods(goodsvo);
				
	}
	
	public void addMainDisplay() {
		adminService.addMaindisplayCategory("신상품");
		adminService.addMaindisplay((long)adminDao.getCurrentInsertGoodsNo(), Long.parseLong(adminDao.getCurrentInsertMainDisplayCategoryNo()));
	}
	
	
	

}
