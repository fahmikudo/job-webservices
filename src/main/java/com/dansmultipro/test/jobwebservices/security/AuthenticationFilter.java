package com.dansmultipro.test.jobwebservices.security;

import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //CORS
        response.addHeader("Access-Control-Allow-Origin", "*");
        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Headers", AUTHORIZATION_HEADER);
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "1");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        }

        final String token = request.getHeader(AUTHORIZATION_HEADER);
        if (token != null) {
            if (jwtUtil.validateToken(token) == Boolean.TRUE) {
                try {
                    Claims claims = jwtUtil.getAllClaimsFromToken(token);
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            claims.getSubject(), null, authorities
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (Exception e) {
                    log.error("ERROR ", e);
                }
            } else {
                log.error("Invalid token.");
            }
        }
        if (!request.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(request, response);
        }
    }
}
