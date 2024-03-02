package kr.go.data.member.controller;

import kr.go.data.member.dto.CreateMemberDto;
import kr.go.data.member.dto.LoginDto;
import kr.go.data.member.dto.LoginDto.Req;
import kr.go.data.member.service.MemberService;
import kr.go.data.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 이메일 중복 확인
    @GetMapping("members/exists/email")
    public ResponseEntity<CustomApiResponse<?>> checkEmailExists(@RequestParam String email) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.checkEmailExists(email);
        return result;
    }

    // 전화번호 확인
    @GetMapping("members/exists/phone")
    public ResponseEntity<CustomApiResponse<?>> checkPhoneExists(@RequestParam String phone) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.checkPhoneExists(phone);
        return result;
    }

    // 아이디 중복 확인
    @GetMapping("members/exists/userId")
    public ResponseEntity<CustomApiResponse<?>> checkUserIdExists(@RequestParam String userId) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.checkUserIdExists(userId);
        return result;
    }

    // 회원가입
    @PostMapping("members")
    public ResponseEntity<CustomApiResponse<?>> createMember(@RequestBody CreateMemberDto.Req dto) {
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
