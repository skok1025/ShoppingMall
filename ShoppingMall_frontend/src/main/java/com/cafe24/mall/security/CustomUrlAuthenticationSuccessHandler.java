package com.cafe24.mall.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import org.springframework.http.server.ServletServerHttpResponse;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import org.springframework.security.web.savedrequest.RequestCache;

import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.cafe24.mall.dto.JSONResult2;
import com.cafe24.mall.provider.BasketProvider;


@Component
public class CustomUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	protected final Log logger = LogFactory.getLog(this.getClass());

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,

			Authentication authentication) throws ServletException, IOException {

		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest != null) {

			requestCache.removeRequest(request, response);

			clearAuthenticationAttributes(request);

		}

		String accept = request.getHeader("accept");

		SecurityUser securityUser = null;

		if (SecurityContextHolder.getContext().getAuthentication() != null) {

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal != null && principal instanceof UserDetails) {

				securityUser = (SecurityUser) principal;

			}

		}

		// 응답요청이 JSON 아닐때, / 로 보낸다.
		if( accept == null || accept.matches( ".*application/json.*" ) == false ) {
			
			System.out.println("security userName: "+securityUser.getId());
            request.getSession(true).setAttribute("loginNow", true);
            if(!securityUser.getId().equals("admin")) {
            	// 성공하면 Cookie 에 있던 temp_id(basketCode) 를 멤버의 basketCode 로 변경해야 한다.
            	String basketCode = "";
            	Long memberNo = securityUser.getNo();
            	for(Cookie cookie:request.getCookies()) {
            		if(cookie.getName().equals("temp_id")) {
            			basketCode = cookie.getValue();
            		}
            	}
            	System.out.println("로그인 멤버번호: "+memberNo+" / 비회원 장바구니 코드: "+basketCode);
            	securityUser.setBasketCode(basketCode);

            	// 로그인 시, 기존 비회원 장바구니 코드를 회원 장바구니 코드로 업데이트
            	getRedirectStrategy().sendRedirect( request, response, "/basket/update" );
            } else {
            	getRedirectStrategy().sendRedirect( request, response, "/admin/" );
            }
            
            return;
         }
         
         MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
         MediaType jsonMimeType = MediaType.APPLICATION_JSON;
        
         JSONResult2 jsonResult = JSONResult2.success(securityUser);
         if( jsonConverter.canWrite( jsonResult.getClass(), jsonMimeType ) ) {
             jsonConverter.write( jsonResult, jsonMimeType, new ServletServerHttpResponse( response ) );
         }

	}

	public void setRequestCache(RequestCache requestCache) {

		this.requestCache = requestCache;

	}

}