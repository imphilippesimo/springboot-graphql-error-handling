package com.zerofiltre.samplegraphqlerrorhandling.security;

import java.lang.annotation.*;

/**
 * This annotation will trigger Admin security check for
 * the GraphQL resolver method where it is marked.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AdminSecured {
}
