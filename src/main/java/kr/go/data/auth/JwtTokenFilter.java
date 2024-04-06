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
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // "Bearer " 접두어 제거
            if (jwtTokenProvider.validateToken(token)) {
                // 토큰에서 권한 정보 추출
                Claims claims = jwtTokenProvider.getClaimsFromToken(token);
                List<SimpleGrantedAuthority> authorities = null;

                if (claims.get("roles") != null) {
                    String rolesStr = claims.get("roles").toString();
                    authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rolesStr));
                } else {
                    // "roles"가 없을 경우 기본 권한 설정 가능
                    authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
                }

                // Spring Security Authentication 객체 생성 및 설정
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        claims.getSubject(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);

                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        // 토큰이 유효하지 않을 경우 또는 토큰이 없을 경우
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
