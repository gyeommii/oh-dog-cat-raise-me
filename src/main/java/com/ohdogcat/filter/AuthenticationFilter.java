package com.ohdogcat.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthenticationFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.debug("dofilter()");
		
		HttpServletRequest req = (HttpServletRequest) request; 
		HttpSession session = req.getSession();
		Object signedMember = session.getAttribute("signedMember");  
		if(signedMember == null) {
			log.debug("로그아웃 상태");
			
			String reqUrl = req.getRequestURL().toString();
			log.debug("요청주소={}",reqUrl);
			
			String url = req.getContextPath() + "/user/signin";
			
			log.debug("url={}",url);
			((HttpServletResponse) response).sendRedirect(url);
			
		} else {
			log.debug("로그인 상태 : {}", signedMember);
			chain.doFilter(request, response);
		}
	}
}
