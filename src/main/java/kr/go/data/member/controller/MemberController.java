package kr.go.data.member.controller;

import kr.go.data.member.dto.ExampleDto;
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
        return null;
    }

    // 전화번호 확인
    @GetMapping("members/exists/phone")
    public ResponseEntity<CustomApiResponse<?>> checkPhoneExists(@RequestParam String phone) {
        return null;
    }

    // 아이디 중복 확인
    public ResponseEntity<CustomApiResponse<?>> checkUserIdExists(@RequestParam String userId) {
        return null;
    }

    // 회원가입
    @PostMapping("members")
    public ResponseEntity<CustomApiResponse<?>> registerMember(@RequestBody ExampleDto dto) {
        return null;
    }

}
