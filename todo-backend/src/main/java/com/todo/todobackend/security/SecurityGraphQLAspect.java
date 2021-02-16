package com.todo.todobackend.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class SecurityGraphQLAspect {
	
	/**
     * Matches all beans that implement {@link com.coxautodev.graphql.tools.GraphQLResolver} as
     * {@code UserMutation}, {@code UserQuery}
     * extend GraphQLResolver interface
     */
    @Pointcut("target(graphql.kickstart.tools.GraphQLResolver)")
    private void allGraphQLResolverMethods() {
        //leave empty
    }

    /**
     * Matches all beans in com.zerofiltre.samplegraphqlerrorhandling package
     */
    @Pointcut("within(com.todo.todobackend..*)")
    private void isDefinedInApplication() {
        //leave empty
    }

    /**
     * Any method annotated with @Unsecured
     */
    @Pointcut("@annotation(com.todo.todobackend.security.annotations.Unsecured)")
    private void isMethodAnnotatedAsUnsecured() {
        //leave empty
    }

}
