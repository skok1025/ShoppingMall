package com.cafe24.mall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.cafe24.mall.vo.SmallCategoryVo;
import com.google.gson.Gson;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@Transactional
public class AdminControllerTest {
	@After
	@Rollback(true)
	public void cleanup() {}
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private AdminDao adminDao;
	//private static Long n = 7L;  // 테스트마다 6씩 증가시켜야함
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.
				webAppContextSetup(webApplicationContext).
				build();
	}
	
	
	/**
	 * 관리자가 1차 카테고리 (상의)를 등록하는 테스트메소드 (성공케이스) 
	 * @throws Exception 예외
	 */
	@Test
	public void testAddBigCategory_Success() throws Exception{
		
		BigCategoryVo vo = new BigCategoryVo();
		vo.setName("지금넣은거");
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isCreated())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	
	/**
	 * 관리자가 1차 카테고리 (아름다운상의꼼데가르송)를 등록하는 테스트메소드 (실패케이스 - 카테고리명 10자 이상) 
	 * @throws Exception 예외
	 */
	@Test
	public void testAddBigCategory_Fail() throws Exception{
		BigCategoryVo vo = new BigCategoryVo();
		vo.setName("아름다운상의꼼데가르송");
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	
	/**
	 * 관리자가 1차 카테고리 (상의 -> 윗옷) 으로 수정하는 테스트 메소드 (성공케이스)
	 * @throws Exception
	 */
	@Test
	public void testModifyBigCategory_Success() throws Exception{
		testAddBigCategory("상의"); // 선행작업 : 1차 카테고리 등록 (상의)
		
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
	 * 관리자가 1차 카테고리 (상의 -> 아름다운상의꼼데가르송) 으로 수정하는 테스트 메소드 
	 * (실패케이스1 - 카테고리명 10자 이상)
	 * @throws Exception 예외
	 */
	@Test
	public void testModifyBigCategory_Fail1() throws Exception{
		testAddBigCategory("상의"); // 선행작업 : 1차 카테고리 등록 (상의)
		
		BigCategoryVo vo = new BigCategoryVo();
		vo.setNo(adminDao.getCurrentInsertBigCategoryNo());
		vo.setName("아름다운상의꼼데가르송");
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	
	/**
	 * 관리자가 없는 1차 카테고리 번호를 (x -> 윗옷) 으로 수정하는 테스트 메소드
	 *  (실패케이스2)
	 * @throws Exception 예외
	 */
	@Test
	public void testModifyBigCategory_Fail2() throws Exception{
		testAddBigCategory("상의"); // 선행작업 : 1차 카테고리 등록 (상의)
		
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
	
	
	/**
	 * 관리자가 1차 카테고리를 삭제하는 테스트 메소드
	 * @throws Exception 예외
	 */
	@Test
	public void testRemoveBigCategory() throws Exception{
		//testAddBigCategory("상의"); // 선행작업 : 1차 카테고리 등록 (상의)
		
		testAddSmallCategory("상의", Arrays.asList("카라티","민소매티셔츠","기본반팔"));
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
	
	
	/**
	 * 관리자가 2차 카테고리 (반팔)를 등록하는 테스트메소드 (성공케이스) 
	 * @throws Exception 예외
	 */
	@Test
	public void testAddSmallCategory_Success() throws Exception{
		testAddBigCategory("상의");
		
		SmallCategoryVo vo = new SmallCategoryVo();
		vo.setName("반팔");
		vo.setBigcategoryNo(adminDao.getCurrentInsertBigCategoryNo());
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/admin/smallcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isCreated())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	/**
	 * 관리자가 2차 카테고리 (아름다운반팔꼼데가르송)를 등록하는 테스트메소드 (실패케이스- 카테고리명 10자 이상) 
	 * @throws Exception 예외
	 */
	@Test
	public void testAddSmallCategory_Fail() throws Exception{
		testAddBigCategory("상의");
		
		SmallCategoryVo vo = new SmallCategoryVo();
		vo.setName("아름다운반팔꼼데가르송");
		vo.setBigcategoryNo(adminDao.getCurrentInsertBigCategoryNo());
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/admin/smallcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	

	/**
	 * 관리자가 2차 카테고리 (반팔 -> 칠부팔) 으로 수정하는 테스트 메소드 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void testModifySmallCategory_Success() throws Exception{
		testAddSmallCategory("상의",Arrays.asList("반팔")); // 선행작업 : 2차 카테고리 등록 (반팔)
		
		SmallCategoryVo vo = new SmallCategoryVo();
		vo.setNo(adminDao.getCurrentInsertSmallCategoryNo());
		vo.setName("칠부팔");
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/admin/smallcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	
	/**
	 * 관리자가 2차 카테고리 (반팔 -> 아름다운꼼데가르송칠부팔) 으로 수정하는 테스트 메소드 (실패케이스 - 카테고리명 10자 이상 )
	 * @throws Exception 예외
	 */
	@Test
	public void testModifySmallCategory_Fail() throws Exception{
		testAddSmallCategory("상의",Arrays.asList("반팔")); // 선행작업 : 2차 카테고리 등록 (반팔)
		
		SmallCategoryVo vo = new SmallCategoryVo();
		vo.setNo(adminDao.getCurrentInsertSmallCategoryNo());
		vo.setName("아름다운꼼데가르송칠부팔");
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/admin/smallcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	/**
	 * 관리자가 2차 카테고리를  삭제하는 테스트 메소드 
	 * @throws Exception 예외
	 */
	@Test
	public void testRemoveSmallCategory() throws Exception{
		testAddSmallCategory("상의",Arrays.asList("반팔","카라티")); // 선행작업 : 2차 카테고리 등록 
		
		SmallCategoryVo vo = new SmallCategoryVo();
		vo.setNo(adminDao.getCurrentInsertSmallCategoryNo());
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/admin/smallcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(1)))
		;	
	}

	/**
	 * 관리자 카테고리 리스트를 반환하는 테스트 메소드
	 * @throws Exception
	 */
	@Test
	public void testGetCategorylist() throws Exception{
		testAddSmallCategory("하의", Arrays.asList("바지","치마"));
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/admin/category/list").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		;	
	}
	
	
	
	@Test
	public void testGoodsList() throws Exception{
		mockMvc
		.perform(get("/api/admin/goodslist/1")).andExpect(status().isOk());
	}
	

	/**
	 * 관리자가 상품을 등록하는 테스트 하는 메소드
	 * @throws Exception 예외
	 */
	@Test
	public void testGoodsAdd_Success() throws Exception{
		testAddSmallCategory_Success();
		
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
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/admin/goods").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(goodsvo)));
		
				resultActions
				.andExpect(status().isCreated())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				;	
	}
	
	/**
	 * 관리자가 상품을 등록하는 테스트 하는 메소드 (실패 케이스 - 상품명 20자 이상)
	 * @throws Exception 예외
	 */
	@Test
	public void testGoodsAdd_Fail() throws Exception{
		testAddSmallCategory_Success();
		
		GoodsVo goodsvo = new GoodsVo();
		
		goodsvo.setName("테스트 상품3테스트 상품3테스트 상품3테스트 상품3");
		goodsvo.setSeillingPrice(15000);
		goodsvo.setDetail("많은 설명");
		goodsvo.setDisplayStatus(GoodsVo.status.y);
		goodsvo.setSeillingStatus(GoodsVo.status.y);
		goodsvo.setManufacturer("제조업자명");
		goodsvo.setSupplier("공급업자명");
		goodsvo.setManufacturingDate("2019-07-19");
		goodsvo.setOrigin("원산지명");
		goodsvo.setSmallcategoryNo(1L);
		
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
		
		
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/admin/goods").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(goodsvo)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		;	
	}
	
	
	/**
	 * 관리자가 상품을 수정하는 테스트 (성공 케이스1- 전체 항목을 수정한 경우)
	 * @throws Exception 예외
	 */
	@Test
	public void testModifyGoodsInfo_Success1() throws Exception {
		testGoodsAdd_Success();
		
		GoodsVo goodsvo = new GoodsVo();
		
		goodsvo.setNo((long)adminDao.getCurrentInsertGoodsNo());
		goodsvo.setName("테스트 상품3");
		goodsvo.setSeillingPrice(15000);
		goodsvo.setDetail("교체된 상품 설명");
		goodsvo.setDisplayStatus(GoodsVo.status.y);
		goodsvo.setSeillingStatus(GoodsVo.status.y);
		goodsvo.setManufacturer("제조업자명");
		goodsvo.setSupplier("공급업자명");
		goodsvo.setManufacturingDate("2019-07-19");
		goodsvo.setOrigin("원산지명");
		goodsvo.setSmallcategoryNo(adminDao.getCurrentInsertSmallCategoryNo());
		
		goodsvo.setGoodsImagesList(Arrays.asList(
				new GoodsImagesVo((long)adminDao.getCurrentInsertGoodsNo(),"체인지메인이미지",GoodsImagesVo.status.y),
				new GoodsImagesVo((long)adminDao.getCurrentInsertGoodsNo(),"체인지테스트이미지1",GoodsImagesVo.status.n),
				new GoodsImagesVo((long)adminDao.getCurrentInsertGoodsNo(),"체인지테스트이미지2",GoodsImagesVo.status.n),
				new GoodsImagesVo((long)adminDao.getCurrentInsertGoodsNo(),"체인지테스트이미지3",GoodsImagesVo.status.n)
		));
		
		// Detail 경우는 추가된 항목만 리스트롤 넘어온다
		goodsvo.setGoodsDetailList(Arrays.asList(
				new GoodsDetailVo((long)adminDao.getCurrentInsertGoodsNo(),"black/105",5,5)			
		));
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/admin/goods").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(goodsvo)));
		
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				;	
	}
	/**
	 * 관리자가 상품을 수정하는 테스트 (성공 케이스2- 이미지는 수정하지 않은 경우)
	 * @throws Exception 예외
	 */
	@Test
	public void testModifyGoodsInfo_Success2() throws Exception {
		testGoodsAdd_Success();
		
		GoodsVo goodsvo = new GoodsVo();
		
		goodsvo.setNo((long)adminDao.getCurrentInsertGoodsNo());
		goodsvo.setName("테스트 상품3");
		goodsvo.setSeillingPrice(15000);
		goodsvo.setDetail("교체된 상품 설명");
		goodsvo.setDisplayStatus(GoodsVo.status.y);
		goodsvo.setSeillingStatus(GoodsVo.status.y);
		goodsvo.setManufacturer("제조업자명");
		goodsvo.setSupplier("공급업자명");
		goodsvo.setManufacturingDate("2019-07-19");
		goodsvo.setOrigin("원산지명");
		goodsvo.setSmallcategoryNo(adminDao.getCurrentInsertSmallCategoryNo());
		
		// Detail 경우는 추가된 항목만 리스트롤 넘어온다
		goodsvo.setGoodsDetailList(Arrays.asList(
				new GoodsDetailVo((long)adminDao.getCurrentInsertGoodsNo(),"black/105",5,5)			
				));
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/admin/goods").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(goodsvo)));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		;	
	}
	/**
	 * 관리자가 상품을 수정하는 테스트 (실패 케이스- 상품명이 20자 이상인 경우)
	 * @throws Exception 예외
	 */
	@Test
	public void testModifyGoodsInfo_Fail() throws Exception {
		testGoodsAdd_Success();
		
		GoodsVo goodsvo = new GoodsVo();
		
		goodsvo.setNo((long)adminDao.getCurrentInsertGoodsNo());
		goodsvo.setName("테스트 상품이지만 정말 긴 이름을 가지고 있습니다.");
		goodsvo.setSeillingPrice(15000);
		goodsvo.setDetail("교체된 상품 설명");
		goodsvo.setDisplayStatus(GoodsVo.status.y);
		goodsvo.setSeillingStatus(GoodsVo.status.y);
		goodsvo.setManufacturer("제조업자명");
		goodsvo.setSupplier("공급업자명");
		goodsvo.setManufacturingDate("2019-07-19");
		goodsvo.setOrigin("원산지명");
		goodsvo.setSmallcategoryNo(1L);
		
		// Detail 경우는 추가된 항목만 리스트롤 넘어온다
		goodsvo.setGoodsDetailList(Arrays.asList(
				new GoodsDetailVo((long)adminDao.getCurrentInsertGoodsNo(),"black/105",5,5)			
				));
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/admin/goods").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(goodsvo)));
		
		resultActions
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		;	
	}
	
	/**
	 * 관리자가 상품을 삭제하는 테스트 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void testRemoveGoodsInfo() throws Exception {
		testGoodsAdd_Success();
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/admin/goods").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(adminDao.getCurrentInsertGoodsNo())));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		;	
	}
	
	/**
	 * 관리자가 상품 상세조회하는 테스트 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void testGoodsInfo() throws Exception {
		testGoodsAdd_Success();
		
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/admin/goods/"+adminDao.getCurrentInsertGoodsNo()).contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		;	
	}
	
	/**
	 * 관리자가 상품 리스트조회하는 테스트 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void testgoodsList() throws Exception {
		//testGoodsAdd_Success();
		
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/admin/goodslist/1").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		;	
	}
	
	
	/**
	 * 관리자가 회원정보를 삭제하는 테스트 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void testRemoveMemberInfo() throws Exception {
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/admin/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(4)));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		;	
	}
	
	/**
	 * 관리자 회원정보 조회 테스트 메소드 (성공케이스1 - 검색필터 적용 x)
	 * @throws Exception
	 */
	@Test
	public void testGetMemberlist_success1() throws Exception{
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/admin/member")
						.param("id", "")
						.param("orderdateStart", "")
						.param("orderdateEnd", "")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		;	
	}
	
	/**
	 * 관리자 회원정보 조회 테스트 메소드 (성공케이스2 - 검색필터 적용 id)
	 * @throws Exception
	 */
	@Test
	public void testGetMemberlist_success2() throws Exception{
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/admin/member")
						.param("id", "skok1025")
						.param("orderdateStart", "")
						.param("orderdateEnd", "")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		;	
	}
	/**
	 * 관리자 회원정보 조회 테스트 메소드 (성공케이스3 - 검색필터 적용 주문날짜)
	 * @throws Exception
	 */
	@Test
	public void testGetMemberlist_success3() throws Exception{
		ResultActions resultActions =
				mockMvc
				.perform(get("/api/admin/member")
						.param("id", "")
						.param("orderdateStart", "2019-07-25")
						.param("orderdateEnd", "2019-07-26")
						.contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		;	
	}
	
	
	
	
	//// 
public void testAddBigCategory(String name) throws Exception{
		
		BigCategoryVo vo = new BigCategoryVo();
		vo.setName(name);
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isCreated())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}

public void testAddSmallCategory(String bigCategoryName, List<String> smallCategoryNames) throws Exception{
	testAddBigCategory(bigCategoryName);
	
	for(String smallCategoryName:smallCategoryNames) {
		SmallCategoryVo vo = new SmallCategoryVo();
		vo.setName(smallCategoryName);
		vo.setBigcategoryNo(adminDao.getCurrentInsertBigCategoryNo());
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/admin/smallcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isCreated())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
}
	
}