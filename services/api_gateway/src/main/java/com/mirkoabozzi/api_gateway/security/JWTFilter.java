package com.mirkoabozzi.api_gateway.security;

import com.mirkoabozzi.api_gateway.entities.User;
import com.mirkoabozzi.api_gateway.exceptions.UnauthorizedException;
import com.mirkoabozzi.api_gateway.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTTools jwtTools;
    private final UserService userService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {

            String header = request.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) throw new UnauthorizedException("Token required.");

            String token = header.substring(7);
            this.jwtTools.verifyToken(token);

            String id = this.jwtTools.extractIdFromToken(token);
            User userFound = this.userService.findById(UUID.fromString(id));

            Authentication authentication = new UsernamePasswordAuthenticationToken(userFound, null, userFound.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            this.handlerExceptionResolver.resolveException(request, response, null, ex);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        String path = request.getServletPath();
        List<String> patternList = List.of(
                "/api/auth/**"
        );
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        return patternList.stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
    }
}
