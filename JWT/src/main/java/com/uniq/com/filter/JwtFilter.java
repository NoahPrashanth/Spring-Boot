package com.uniq.com.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.uniq.com.config.service.JwtUtil;
import com.uniq.com.entity.Employee;
import com.uniq.com.repo.EmpRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmpRepository repo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
        	
            String jwt = authHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);
            String role = jwtUtil.extractRole(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            	Employee emp = repo.findByEmail(username).get();
                UserDetails user = User.builder()
                        .username(username)
                        .password(emp.getPwd())
                        .roles(role.replace("ROLE_", ""))
                        .build();

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        chain.doFilter(request, response);
    }
}
