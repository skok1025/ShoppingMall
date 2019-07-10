package com.cafe24.mall.controller.api.test;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.mall.config.AppConfig;
import com.cafe24.mall.config.TestWebConfig;
import com.cafe24.mall.service.MainService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
public class MainControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private MainService mainService;
	
	@Before
	public void setup() { // 초깃값을 잡아주는 메소드
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

	}

	@Test
	public void testDIMainService() {
		assertNotNull(mainService);
	}
	
	@Test
	public void testJoin() throws Exception{
		this.mockMvc
		.perform(get("/join"))
		.andDo(print())
		.andExpect(status().is(200));
	}
	
	
}
