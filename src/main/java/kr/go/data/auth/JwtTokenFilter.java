package kr.go.data.auth;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import kr.go.data.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = extractToken(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            setSecurityContext(token);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            unauthorizedResponse(servletResponse);
        }
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private void setSecurityContext(String token) {
        Claims claims = jwtTokenProvider.getClaimsFromToken(token);
        String rolesStr = claims.get("roles", String.class);
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + rolesStr);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(), null, Collections.singletonList(authority));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }


    private void unauthorizedResponse(ServletResponse servletResponse) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // CustomApiResponse 객체 생성
        CustomApiResponse<?> apiResponse = CustomApiResponse.createFailWithoutData(HttpServletResponse.SC_UNAUTHORIZED, "토큰값을 확인해주세요");

        // 응답 본문에 쓸 JSON 문자열 생성
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);

        // HttpServletResponse 설정
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
