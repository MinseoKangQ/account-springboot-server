package kr.go.data.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

}
