package org.wora.majesticcup.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailService userDetailService;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil,UserDetailService userDetailService){
        this.jwtTokenUtil=jwtTokenUtil;
        this.userDetailService=userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String token = extractToken(request);
        if (token != null) {
            try {
                if (jwtTokenUtil.validateToken(token)) {
                    String username = jwtTokenUtil.getUsernameFromToken(token);
                    Authentication authentication = jwtTokenUtil.getAuthentication(token, userDetailService);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.info("Successfully authenticated user: {}");
                }
            } catch (Exception e) {
                logger.error("Failed to authenticate token: {}");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
