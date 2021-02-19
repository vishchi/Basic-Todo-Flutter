package com.todo.todobackend.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.todo.todobackend.entity.User;
import com.todo.todobackend.services.UserService;
import com.todo.todobackend.utility.Jwt;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserService userService;
	@Autowired
	private Jwt jwt;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorization = request.getHeader("Authorization");
		String email = null;
		String token = null;
		if(authorization != null && authorization.startsWith("Bearer ")) {
			token = authorization.substring(7);
			email = jwt.extractUsername(token);
		}
		if(email != null) {
			User user = userService.getUser(email);
			if( user != null && jwt.validateToken(token, user.getEmail())) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, null);
				SecurityContextHolder.getContext().setAuthentication(authToken);
				System.out.println("Context set");
				System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
			}
			
		}
		filterChain.doFilter(request, response);

	}

}
