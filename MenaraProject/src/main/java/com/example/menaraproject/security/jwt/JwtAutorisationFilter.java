

package com.example.menaraproject.security.jwt;

import com.example.menaraproject.security.common.SecurityParams;
import com.example.menaraproject.security.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class JwtAutorisationFilter extends OncePerRequestFilter {
    @Qualifier("rhService")
    @Autowired
    private UserService userService;
    private JwtUtil jwtUtil;




    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // rest of your code


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin",
                "*");
        response.addHeader("Access-Control-Allow-Headers",
                "Origin, Accept, X-Requested-With, Content-Type, " +
                        "Access-Control-Request-Method, " +
                        "Access-Control-Request-Headers,Authorization");
        response.addHeader("Access-Control-Allow-Methods",
                "DELETE, POST, GET, PUT, PATCH, OPTIONS");
        response.addHeader("Access-Control-Expose-Headers",
                "Access-Control-Allow-Origin, " +
                        "Access-Control-Allow-Credentials, " +
                        "Authorization");

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        } else {
            final String jwtToken = request.getHeader(SecurityParams.JWT_HEADER_NAME);
            final SecurityContext securityContext = SecurityContextHolder.getContext();
            System.out.println("Token=" + jwtToken);
            if (jwtToken != null && securityContext.getAuthentication() == null) {
                String jwt = jwtToken.substring("Bearer ".length());
                System.out.println("JWT=" + jwt);
                String username = jwtUtil.getUserNameFromToken(jwt);
                if (username != null) {
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    if (jwtUtil.isTokenValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
            filterChain.doFilter(request, response);
        }
    }



}

