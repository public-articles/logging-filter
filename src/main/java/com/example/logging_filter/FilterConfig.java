package com.example.logging_filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

import java.io.IOException;

@Slf4j
@Configuration
public class FilterConfig implements Filter {

    public static final String CORRELATION_ID = "X-Correlation-Id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        MDC.put(CORRELATION_ID, httpRequest.getHeader(CORRELATION_ID));
        log.info("Intercept coming request and set MDC context information");
        chain.doFilter(request, response);
    }

}
