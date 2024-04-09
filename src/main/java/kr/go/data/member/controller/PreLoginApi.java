package kr.go.data.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.go.data.member.dto.LoginDto;
import kr.go.data.member.dto.SignUpDto;
import kr.go.data.util.annotation.NoAuth;
import kr.go.data.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "로그인 이전에 사용하는 API", description = "header에 token값 필요하지 않음")
public interface PreLoginApi {

    @NoAuth
    @Operation(summary = "[회원가입] 이메일 중복 확인")
    @GetMapping("exists/email")
    ResponseEntity<CustomApiResponse<?>> checkEmailExists(@RequestParam String email);

    @NoAuth
    @Operation(summary = "[회원가입] 전화번호 중복 확인")
    @GetMapping("exists/phone")
    ResponseEntity<CustomApiResponse<?>> checkPhoneExists(@RequestParam String phone);

    @NoAuth
    @Operation(summary = "[회원가입] 아이디 중복 확인")
    @GetMapping("exists/userId")
    ResponseEntity<CustomApiResponse<?>> checkUserIdExists(@RequestParam String userId);

    @NoAuth
    @Operation(summary = "[회원가입] 회원가입")
    @PostMapping("sign-up")
    ResponseEntity<CustomApiResponse<?>> signUp(@RequestBody SignUpDto.Req dto);

    @NoAuth
    @Operation(summary = "[로그인] 로그인", description = "response header에 token 값 존재")
    @PostMapping("login")
    ResponseEntity<CustomApiResponse<?>> login(@RequestBody LoginDto.Req dto);
}
