package com.kayafirat.blog.security;

import com.kayafirat.blog.entity.User;
import com.kayafirat.blog.service.UserService;
import com.kayafirat.blog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final Cookie c;
        if (httpServletRequest.getCookies() != null) {
            c = Arrays.stream(httpServletRequest.getCookies()).filter(cookie -> cookie.getName().equals("authenticate"))
                    .findFirst()
                    .orElse(new Cookie("authenticate", null));

        String authorizationHeader = null;
        if (c.getValue() != null) {
            authorizationHeader = c.getValue();
        }

        Long id = null;
        String jwt = null;
        if (authorizationHeader != null) {
            jwt = authorizationHeader;
            id = jwtUtil.extractUserID(jwt);
        }
        if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userService.loadUserByUsername(String.valueOf(id));
            User user = userService.getUser(id);
            if (jwtUtil.validateToken(jwt, user)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }
}
