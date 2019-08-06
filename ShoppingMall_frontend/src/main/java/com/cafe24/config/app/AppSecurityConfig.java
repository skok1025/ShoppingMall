package com.cafe24.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cafe24.mall.security.CustomPasswordEncoder;
import com.cafe24.mall.security.CustomUrlAuthenticationSuccessHandler;



/*
Security Filter Chain
	 1. ChannelProcessingFilter
	 2. SecurityContextPersistenceFilter		( auto-config default, Required )
	 3. ConcurrentSessionFilter
	 4. LogoutFilter							( auto-config default, Required )
	 5. UsernamePasswordAuthenticationFilter	( auto-config default, Required )
	 6. DefaultLoginPageGeneratingFilter		( auto-config default )
	 7. CasAuthenticationFilter
	 8. BasicAuthenticationFilter				( auto-config default )
	 9. RequestCacheAwareFilter					( auto-config default )
	10. SecurityContextHolderAwareRequestFilter	( auto-config default )
	11. JaasApiIntegrationFilter
	12. RememberMeAuthenticationFilter			( , Required Custom)
	13. AnonymousAuthenticationFilter			( auto-config default )
	14. SessionManagementFilter					( auto-config default )
	15. ExceptionTranslationFilter				( auto-config default, Required )
	16. FilterSecurityInterceptor				( auto-config default, Required )	
*/	
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	// 스프링 시큐리티 필터 연결
	// WebSecurity 객체 
	// springSecurityFilterChain 라는 Bean 이름의 
	// DelegatingFilterProxy Bean 객체를 생성한다.
	// DelegatingFilterProxy Bean 는 많은 
	// Spring Security Filter Chain 위임한다.
	//
	@Override
	public void configure(WebSecurity web) throws Exception {
		//super.configure(web);
		// ACL 예외 URL 설정
		// 예외가 접근 url을 설정한다. 특정 url 허용
		
		//web.ignoring().antMatchers("/assets/**");
		//web.ignoring().antMatchers("/favicon.ico");
		web.ignoring().regexMatchers("\\A/(u|assets)/.*\\Z");
		web.ignoring().regexMatchers("\\A/favicon.ico/\\Z");
		
		
	}

	/**
deny all

/user/update
/user/logout -> ROLE_USER , ROLE_ADMIN => Authenticated
/board/write -> ROLE_USER , ROLE_ADMIN => Authenticated
/board/delete -> ROLE_USER , ROLE_ADMIN => Authenticated
/board/modify -> ROLE_USER , ROLE_ADMIN => Authenticated

/admin/** -> ROLE_ADMIN (Authorized)
all permitted

	 **/
	// Interceptor URL의 요청을 안전하게 보호(보안)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		// 막는 작업 모든 URL 막는다.
		http.authorizeRequests()
			// 인증이 되어있을 때 (Authenticated ?)
			.antMatchers("/user/update","/user/logout").authenticated()
			.antMatchers("/board/write","/board/delete","/board/modify").authenticated()
			// ADMIN Authorizaty (ADMIN 권한, ROLE_ADMIN)
			//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			//.antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
			// All Permit
			//.antMatchers("/**").permitAll()
			.anyRequest().permitAll();
			
			//
			// 2. CSRF 설정
			// Temp
			http.csrf().disable();
		
		
			//
			// 2. 로그인 설정
			//
			http
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/auth")
			.failureUrl("/login?result=fail")
			// Ajax 또는 WEB 요청에 따라서 반응을 다르게 - authenticationSuccessHandler
			.successHandler(authenticationSuccessHandler())
			//.defaultSuccessUrl("/",true)
			.usernameParameter("id")
			.passwordParameter("password")
			
			//
			// 3. 로그아웃 설정
			//
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/")
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true)
			
			
			// 
			// 4. Access Denial Handler
			//
			.and()
			.exceptionHandling()
			.accessDeniedPage("/WEB-INF/views/error/403.jsp")
		
			//
			// 5. Remember Me (자동로그인)
			//
			.and()
			.rememberMe()
			.key("mysite3")
			.rememberMeParameter("remember-me");
		
		
			
}
	
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomUrlAuthenticationSuccessHandler();
	}

	
	
	// ACL
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 비번 encoding?
		auth
			.userDetailsService(userDetailsService)
			.and()
			.authenticationProvider(autenicationProvider());
	}
	
	@Bean
	public AuthenticationProvider autenicationProvider() {
		DaoAuthenticationProvider authprovider = 
				new DaoAuthenticationProvider();
		
		authprovider.setUserDetailsService(userDetailsService);
		//authprovider.setPasswordEncoder(passwordEncoder());
		authprovider.setPasswordEncoder(customPasswordEncoder());

		return authprovider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PasswordEncoder customPasswordEncoder() {
		return new CustomPasswordEncoder();
	}


}
