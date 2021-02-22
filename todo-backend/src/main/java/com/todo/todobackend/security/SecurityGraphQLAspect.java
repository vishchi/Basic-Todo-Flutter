package com.todo.todobackend.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.todo.todobackend.entity.User;

@Aspect
@Component
@Order(1)
public class SecurityGraphQLAspect {

	@Before("(allGraphQLResolverMethods() && isDefinedInApplication() && !isMethodAnnotatedAsUnsecured())")
	public void doSecurityCheck() throws Exception {
		// TODO Check security context for userdetails, if not present then throw
		// exception
		if (SecurityContextHolder.getContext() != null
				&& SecurityContextHolder.getContext().getAuthentication() != null) {
			Authentication authToken = SecurityContextHolder.getContext().getAuthentication();
			System.out.printf("AuthToken: %s\n", authToken.getPrincipal() );
			System.out.print("Is authenticated: ");
			System.out.println(authToken.isAuthenticated());
			User user = (User) authToken.getPrincipal();
			if (user != null) {
				return;
			}
		}
		// TODO throw exception
		System.out.println("Context not set");
		throw new Exception("No Login");
	}

	/**
	 * Matches all beans that implement
	 * {@link com.coxautodev.graphql.tools.GraphQLResolver} as {@code UserMutation},
	 * {@code UserQuery} extend GraphQLResolver interface
	 */
	@Pointcut("target(graphql.kickstart.tools.GraphQLResolver)")
	private void allGraphQLResolverMethods() {
		// leave empty
	}

	/**
	 * Matches all beans in com.zerofiltre.samplegraphqlerrorhandling package
	 */
	@Pointcut("within(com.todo.todobackend..*)")
	private void isDefinedInApplication() {
		// leave empty
	}

	/**
	 * Any method annotated with @Unsecured
	 */
	@Pointcut("@annotation(com.todo.todobackend.security.annotations.Unsecured)")
	private void isMethodAnnotatedAsUnsecured() {
		// leave empty
	}

}
