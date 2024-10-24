package com.example.Project.security;

import ch.qos.logback.core.Context;
import com.example.Project.entities.User;
import com.example.Project.services.impl.JWTService;
import com.example.Project.services.impl.UserServiceImpl;
import io.micrometer.observation.Observation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;
    @Autowired
    ApplicationContext context;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                    //Bearer+token
        String authHeader =request.getHeader("Authorization");
        String token=null;
        String userName=null;

        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            userName =jwtService.extractUserName(token);
        }

        if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= context.getBean(UserDetailsService.class).loadUserByUsername(userName);
            if (jwtService.validateToken(token,userDetails)){
               UsernamePasswordAuthenticationToken authenticationToken=
                       new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);

    }
}
