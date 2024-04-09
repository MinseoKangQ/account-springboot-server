package kr.go.data.member.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.go.data.member.dto.ChangePasswordDto;
import kr.go.data.member.dto.CheckPasswordDto;
import kr.go.data.member.dto.LoginDto;
import kr.go.data.member.dto.SignUpDto;
import kr.go.data.member.service.MemberService;
import kr.go.data.util.annotation.NoAuth;
import kr.go.data.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class PreLoginController implements PreLoginApi {

    private final MemberService memberService;

    // 이메일 중복 확인
    @GetMapping("exists/email")
    public ResponseEntity<CustomApiResponse<?>> checkEmailExists(@RequestParam String email) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.checkEmailExists(email);
        return result;
    }

    // 전화번호 확인
    @GetMapping("exists/phone")
    public ResponseEntity<CustomApiResponse<?>> checkPhoneExists(@RequestParam String phone) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.checkPhoneExists(phone);
        return result;
    }

    // 아이디 중복 확인
    @GetMapping("exists/userId")
    public ResponseEntity<CustomApiResponse<?>> checkUserIdExists(@RequestParam String userId) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.checkUserIdExists(userId);
        return result;
    }

    // 회원가입
    @PostMapping("sign-up")
    public ResponseEntity<CustomApiResponse<?>> signUp(@RequestBody SignUpDto.Req dto) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.createMember(dto);
        return result;
    }

    // 로그인
    @PostMapping("login")
    public ResponseEntity<CustomApiResponse<?>> login(@RequestBody LoginDto.Req dto) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.login(dto);
        return result;
    }

}
