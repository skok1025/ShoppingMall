package com.cafe24.mall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
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
import com.cafe24.mall.repository.BasketDao;
import com.cafe24.mall.repository.CustomerDao;
import com.cafe24.mall.service.AdminService;
import com.cafe24.mall.service.CustomerService;
import com.cafe24.mall.vo.BasketVo;
import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MemberVo;
import com.google.gson.Gson;

/**
 * 장바구니 관련 컨트롤러 테스트 클래스
 * @author 김석현
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
@Transactional
public class _4BasketControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private BasketDao basketDao;
	
	@Autowired
	private AdminService adminService;
	
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
	 * 회원이 상품을 장바구니에 추가하는 테스트 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void testAddBasket_success() throws Exception{
		//addMember();
		//addGoods();
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/basket/member/add")
						.param("memberNo", customerDao.getCurrentInsertNo()+"")
						.param("goodsDetailNo", adminDao.getCurrentInsertGoodsDetailNo()+"")
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
	 * 회원이 상품을 장바구니에 추가하는 테스트 (실패케이스 - 없는 회원번호)
	 * @throws Exception 예외
	 */
	@Test
	public void testAddBasket_fail() throws Exception{
		addMember();
		addGoods();
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/basket/member/add")
						.param("memberNo", "100")
						.param("goodsDetailNo", adminDao.getCurrentInsertGoodsDetailNo()+"")
						.param("cnt", "1")
						.contentType(MediaType.APPLICATION_JSON)
						);
		
		
		resultActions
		.andExpect(status().is(500))
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		;	
	}
	
	/**
	 * 비회원이 상품을 장바구니에 추가하는 테스트 (성공 케이스 - 기존에 있는 장바구니 코드)
	 * @throws Exception 예외
	 */
	@Test
	public void testNonmemberAddBasket_success() throws Exception{
		addMember();
		addGoods();
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/basket/nonmember/add")
						.param("basketCode", "c-101")
						.param("goodsDetailNo", adminDao.getCurrentInsertGoodsDetailNo()+"")
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
	 * 비회원이 상품을 장바구니에 추가하는 테스트 (성공 케이스 - 기존에 없는 장바구니 코드)
	 * @throws Exception 예외
	 */
	@Test
	public void testNonmemberAddBasket_success2() throws Exception{
		addMember();
		addGoods();
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/basket/nonmember/add")
						.param("basketCode", "bk-101")
						.param("goodsDetailNo", adminDao.getCurrentInsertGoodsDetailNo()+"")
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
	 * 비회원이 상품을 장바구니에 추가하는 테스트 (실패 케이스 - 없는 상품번호)
	 * @throws Exception 예외
	 */
	@Test
	public void testNonmemberAddBasket_fail() throws Exception{
		addMember();
		addGoods();
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/basket/nonmember/add")
						.param("basketCode", "bk-101")
						.param("goodsDetailNo", "1230")
						.param("cnt", "1")
						.contentType(MediaType.APPLICATION_JSON)
						);
		
		
		resultActions
		.andExpect(status().is(500))
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		;	
	}
	
	/**
	 * 회원 장바구니 리스트 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testMemberBasketList() throws Exception{
		testAddBasket_success();
		
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
	
	/**
	 * 비회원 장바구니 리스트 테스트
	 * @throws Exception 예외
	 */
	@Test
	public void testNonMemberBasketList() throws Exception{
		testNonmemberAddBasket_success();
		
		String basketCode = "c-101";
		
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
	
	/**
	 * 장바구니 수량수정 테스트 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void testModifyBasketInfo_success() throws Exception{
		testAddBasket_success();
		
		BasketVo vo = new BasketVo();
		vo.setNo(basketDao.getCurrentInsertBasketNo());
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

	/**
	 * 장바구니 수량수정 테스트 (실패케이스 - 없는 장바구니번호)
	 * @throws Exception 예외
	 */
	@Test
	public void testModifyBasketInfo_fail() throws Exception{
		testAddBasket_success();
		
		BasketVo vo = new BasketVo();
		vo.setNo(1000L);
		vo.setCnt(4);
		
		ResultActions resultActions =
				mockMvc
				.perform(put("/api/basket/modify").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		;
	}
	
	/**
	 * 장바구니 특정상품 삭제 테스트 (성공 케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void testRemoveBasketGoods_success() throws Exception{
		testAddBasket_success();
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/basket/remove").param("basketNo", basketDao.getCurrentInsertBasketNo()+"").contentType(MediaType.APPLICATION_JSON));
		
				resultActions
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				;
	}
	
	/**
	 * 장바구니 특정상품 삭제 테스트(실패 케이스 - 없는 장바구니 번호)
	 * @throws Exception 예외
	 */
	@Test
	public void testRemoveBasketGoods_fail() throws Exception{
		testAddBasket_success();
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/basket/remove").param("basketNo", "100").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		;
	}

	/**
	 * 회원 장바구니 전체 삭제 테스트 (성공 케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void testAllremoveMemberBasketGoods_success() throws Exception{
		testAddBasket_success();
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/basket/member/allremove").param("memberNo", customerDao.getCurrentInsertNo()+"").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		;
	}
	
	/**
	 * 회원 장바구니 전체 삭제 테스트 (실패 케이스 - 없는 멤버번호)
	 * @throws Exception 예외
	 */
	@Test
	public void testAllremoveMemberBasketGoods_fail() throws Exception{
		testAddBasket_success();
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/basket/member/allremove").param("memberNo", "103420").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		;
	}
	
	
	/**
	 * 비회원 장바구니 전체 삭제 테스트 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void testAllremoveNonMemberBasketGoods_success() throws Exception{
		testNonmemberAddBasket_success();
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/basket/nonmember/allremove").param("basketCode", "c-101").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		;
	}
	
	/**
	 * 비회원 장바구니 전체 삭제 테스트 (실패케이스 - 없는 장바구니 코드)
	 * @throws Exception 예외
	 */
	@Test
	public void testAllremoveNonMemberBasketGoods_fail() throws Exception{
		testNonmemberAddBasket_success();
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/basket/nonmember/allremove").param("basketCode", "bbb-101").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		;
	}
	
	// 선행작업: 회원가입, 상품등록
	
	public void addMember() {
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
		
		customerService.memberJoin(vo);
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
}
