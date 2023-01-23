package com.blog.userApp.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;

        String authHeader=httpServletRequest.getHeader("authorization");
        if (authHeader==null || !authHeader.startsWith("Bearer")){
            System.out.println(authHeader);
            System.out.println(httpServletRequest);
            throw new ServletException("Token is Missing");
        }
        else {
            System.out.println("else");
            String tok=authHeader.substring(7);
            Claims claims= Jwts.parser().setSigningKey("private_key").parseClaimsJws(tok).getBody();
            System.out.println(claims);
            System.out.println(claims.get("userEmail"));
            httpServletRequest.setAttribute("userEmail",claims.get("userEmail"));
            httpServletRequest.setAttribute("userName",claims.get("userName"));
            filterChain.doFilter(httpServletRequest,httpServletResponse);

        }

    }
}
