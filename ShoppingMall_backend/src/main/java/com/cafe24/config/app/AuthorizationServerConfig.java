package com.cafe24.config.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@EnableResourceServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private DataSource dataSource;
	
	//accessToken 저장 >> DB
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//			super.configure(endpoints);
			//스키마 >> 주입 시, 자동 생성
			endpoints.tokenStore(new JdbcTokenStore(dataSource))
				.authenticationManager(authenticationManager);
		}
		
		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			super.configure(security);
		}
		
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//			super.configure(clients);
			clients.jdbc(dataSource);
		}
}
