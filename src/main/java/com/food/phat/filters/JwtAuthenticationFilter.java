package com.food.phat.filters;

import com.food.phat.entity.Token;
import com.food.phat.service.AuthService;
import com.food.phat.service.Impl.UserServiceImpl;
import com.food.phat.config.JwtService;
import com.food.phat.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final TokenService tokenService;
    private final UserServiceImpl userServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        token = jwtService.extractToken(token);
        if(jwtService.validateToken(token)) {
            String username = jwtService.extractUsername(token);
            Token redisToken = tokenService.get(jwtService.extractUuid(token));

            UserDetails userDetails = userServiceImpl.loadUserByUsername(username);

            if(userDetails != null
                    && redisToken != null
                    && redisToken.getUserKey().equals(userServiceImpl.getUserByUsername(username).getUserId())) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                authToken.getDetails();
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
