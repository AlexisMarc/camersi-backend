package com.camersi.camersi.Config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.camersi.camersi.Security.UserDetailsServiceImp;
import com.camersi.camersi.Security.jwt.JwtProvider;
import com.camersi.camersi.Security.jwt.JwtTokenFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JwtTokenFilter.class);

    public JwtAuthenticationTokenFilter() {
        super("/graphql/**");
    }

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImpl;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String header = httpServletRequest.getHeader("Authorisation");


        if (header == null || !header.startsWith("Token ")) {
            throw new RuntimeException("JWT Token is missing");
        }

        String authenticationToken = header.substring(6);
        String userName = jwtProvider.getUserNameFromToken(authenticationToken);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
        return getAuthenticationManager().authenticate(auth);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        logger.error(header);
        if (header != null && header.startsWith("Bearer"))
            return header.replace("Bearer ", "");
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        try {
            String token = getToken(request);
            if (token != null && jwtProvider.validateToken(token)) {
                String userName = jwtProvider.getUserNameFromToken(token);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        } catch (Exception e) {
            logger.error("ERROR: " + e.getMessage());
        }
        chain.doFilter(request, response);
    }
}
