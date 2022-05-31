package com.app.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	//Same contract as for doFilter, but guaranteed to bejust invoked once per request within a single request thread
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(SecurityConstants.AUTHORIZATION_STRING);
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		//implementation that is designed for simple presentation of a username and password. 
		UsernamePasswordAuthenticationToken authentication = getAuhentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuhentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.AUTHORIZATION_STRING);
		if (token != null && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			token = token.replace(SecurityConstants.TOKEN_PREFIX, "").trim();
			String user = Jwts.parser().setSigningKey(SecurityConstants.getSecretToken()).parseClaimsJws(token).getBody()
					.getSubject();
			if (user != null) {
				//This constructor should only be used by AuthenticationManager or AuthenticationProvider implementations that are satisfied withproducing a trusted (i.e. isAuthenticated() = true)authentication token.
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
		}

		return null;
	}

}
