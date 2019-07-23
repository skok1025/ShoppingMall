package com.cafe24.mall.senario;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MemberVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
public class AdminGoodsSenarioTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	/**
	 * 테스트 케이스1(성공) : 상품리스트 조회 -> 상품등록 -> 상품수정 -> 상품 삭제
	 * 
	 * @throws Exception 예외
	 */
	@Test
	public void testAdminGoods() throws Exception {
		p1_GoodsList();
		
		GoodsVo goodsvo = new GoodsVo();
		
		goodsvo.setNo(1L);
		goodsvo.setName("테스트 상품");
		goodsvo.setGoodsImagesList(Arrays.asList(
				new GoodsImagesVo(1L,"메인이미지",GoodsImagesVo.status.y),
				new GoodsImagesVo(1L,"테스트이미지1",GoodsImagesVo.status.n),
				new GoodsImagesVo(1L,"테스트이미지2",GoodsImagesVo.status.n),
				new GoodsImagesVo(1L,"테스트이미지3",GoodsImagesVo.status.n)
		));
		
		goodsvo.setGoodsDetailList(Arrays.asList(
				new GoodsDetailVo(1L,"black/90",5,5),
				new GoodsDetailVo(1L,"black/95",5,5),
				new GoodsDetailVo(1L,"black/100",5,4)				
		));
		
		p2_testGoodsAdd(goodsvo);
		p3_testModifyGoodsInfo(goodsvo);
		p4_testRemoveGoodsInfo(goodsvo.getNo());
		
	}
	
	
	
////////////////////////////////각 테스트 케이스에 사용될 관리자 상품관리 Process
	
	public void p1_GoodsList() throws Exception{
		mockMvc
		.perform(get("/api/admin/goodslist")).andExpect(status().isOk());
	}
	
	/**
	 * 관리자가 상품을 등록하는 테스트 하는 메소드
	 * @throws Exception 예외
	 */
	
	public void p2_testGoodsAdd(GoodsVo goodsvo) throws Exception{
		
		
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
	public void p3_testModifyGoodsInfo(GoodsVo goodsvo) throws Exception {
		
		goodsvo.setDetail("수정한 상품디테일입니다.");
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
	 * 관리자가 상품을 삭제하는 테스트
	 * @throws Exception 예외
	 */
	
	public void p4_testRemoveGoodsInfo(Long no) throws Exception {
		ResultActions resultActions =
				mockMvc
				.perform(delete("/api/admin/goods").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(no)));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		;	
	}
	
	
	

}
