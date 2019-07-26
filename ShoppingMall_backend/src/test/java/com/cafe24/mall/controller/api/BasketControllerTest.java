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
	 * 회원이 상품을 장바구니에 추가하는 테스트 (성공케이스)
	 * @throws Exception 예외
	 */
	@Test
	public void testAddBasket_success() throws Exception{
		
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
	 * 회원이 상품을 장바구니에 추가하는 테스트 (실패케이스 - 없는 회원번호)
	 * @throws Exception 예외
	 */
	@Test
	public void testAddBasket_fail() throws Exception{
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/basket/member/add")
						.param("memberNo", "100")
						.param("goodsDetailNo", "1")
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

	/**
	 * 비회원이 상품을 장바구니에 추가하는 테스트 (성공 케이스 - 기존에 없는 장바구니 코드)
	 * @throws Exception 예외
	 */
	@Test
	public void testNonmemberAddBasket_success2() throws Exception{
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/basket/nonmember/add")
						.param("basketCode", "bk-101")
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
	 * 비회원이 상품을 장바구니에 추가하는 테스트 (실패 케이스 - 없는 상품번호)
	 * @throws Exception 예외
	 */
	@Test
	public void testNonmemberAddBasket_fail() throws Exception{
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/basket/nonmember/add")
						.param("basketCode", "bk-101")
						.param("goodsDetailNo", "123")
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

	/**
	 * 장바구니 수량수정 테스트 (실패케이스 - 없는 장바구니번호)
	 * @throws Exception 예외
	 */
	@Test
	public void testModifyBasketInfo_fail() throws Exception{
		BasketVo vo = new BasketVo();
		vo.setNo(100L);
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
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/basket/remove").param("basketNo", "2").contentType(MediaType.APPLICATION_JSON));
		
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
		//testAddBasket();
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/basket/member/allremove").param("memberNo", "1").contentType(MediaType.APPLICATION_JSON));
		
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
		//testAddBasket();
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/basket/member/allremove").param("memberNo", "100").contentType(MediaType.APPLICATION_JSON));
		
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
		//testAddBasket();
		
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
		//testAddBasket();
		
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/basket/nonmember/allremove").param("basketCode", "bbb-101").contentType(MediaType.APPLICATION_JSON));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		;
	}
	
	
}
