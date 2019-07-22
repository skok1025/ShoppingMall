package com.cafe24.mall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

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
import com.cafe24.mall.repository.OrderDao;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.CancelApplyVo;
import com.cafe24.mall.vo.OrderGoodsVo;
import com.cafe24.mall.vo.OrderGoodsVo.status;
import com.cafe24.mall.vo.OrderVo;
import com.cafe24.mall.vo.OrderVo.orderStatus;
import com.cafe24.mall.vo.OrderVo.paymentStatus;
import com.google.gson.Gson;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
public class OrderControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private OrderDao orderdao;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.
				webAppContextSetup(webApplicationContext).
				build();
	}
	
	/**
	 * 고객이 주문을 등록하는 테스트메소드 (성공케이스) 
	 * @throws Exception 예외
	 */
	@Test
	public void testAddOrder_Success() throws Exception{
		
		OrderVo vo = new OrderVo();
		
		vo.setCode("20190722-00004");
		vo.setMemberNo(1L);
		vo.setOrderName("김석현");
		vo.setOrderTel("01068669202");
		vo.setPassword("1234");
		vo.setPaymentStatus(paymentStatus.y);
		vo.setOrderStatus(orderStatus.배송대기);
		vo.setPaymentWay("카드");
		vo.setReceiverName("김은완");
		vo.setReceiverTel1("01068669202");
		vo.setReceiverTel2("01068669202");
		vo.setReceiverPostcode("152-123");
		vo.setReceiverAddress("서울시 성동구 성수이로 137 110동 1502호");
		vo.setMessage("배송중 메세지");
		
		vo.setOrderGoodsList(Arrays.asList(
				new OrderGoodsVo(1L, 1, status.배송대기, 15000),
				new OrderGoodsVo(2L, 3, status.배송대기, 45000)
				));
		
		
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/order/").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isCreated())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	/**
	 * 주문코드(20190722-00002)에 따른 주문상품리스트를 얻어오는 테스트메소드 (성공케이스) 
	 * @throws Exception 예외
	 */
	@Test
	public void testviewOrderGoodsList() throws Exception{
		
		
		String orderCode = "20190722-00002";
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/order/list").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(orderCode)));
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}

	/**
	 * 주문코드(20190722-00002)에 따른 주문자 정보 및 배송지 정보를 얻어오는 테스트메소드 (성공케이스) 
	 * @throws Exception 예외
	 */
	@Test
	public void testviewOrderInfo() throws Exception{
		
		
		String orderCode = "20190722-00002";
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/order/info").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(orderCode)));
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
	
	
	/**
	 * 주문취소신청 하는 테스트메소드 (성공케이스) 
	 * @throws Exception 예외
	 */
	@Test
	public void testAddCancelApply_Success() throws Exception{
		testAddOrder_Success();
		
		CancelApplyVo vo = new CancelApplyVo();
		
		vo.setOrderNo(orderdao.getCurrentInsertOrderNo());
		vo.setGoodsDetailNo(1L);
		vo.setCancelCnt(1);
		vo.setCancelReason("제가 원하는 색상이 아닌거 같아요");
		vo.setStatus(CancelApplyVo.status.n);
		
		ResultActions resultActions =
				mockMvc
				.perform(post("/api/order/cancel").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andExpect(status().isCreated())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		//.andExpect(jsonPath("$.data", is(1)))
		;	
	}
}
