package com.cafe24.mall.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cafe24.mall.config.AppConfig;
import com.cafe24.mall.config.TestWebConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;
	
	@Test
	public void testCustomerServiceDI() {
		assertNotNull(customerService);
	}
}
