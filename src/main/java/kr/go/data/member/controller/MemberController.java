package kr.go.data.member.controller;

import kr.go.data.member.service.MemberServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class MemberController {

    private final MemberServiceImpl memberService;

    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    // 아이디 검증
    @GetMapping("/check-id")
    public ResponseEntity<?> checkIsAvailableId(@RequestParam("id") String id) {
        Boolean result = memberService.checkIsAvailableId(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 이메일 검증
    @GetMapping("/check-email")
    public ResponseEntity<?> checkIsAvailableEmail(@RequestParam("email") String email) {
        Boolean result = memberService.checkIsAvailableEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 전화번호 검증
    @GetMapping("/check-phone-num")
    public ResponseEntity<?> checkIsAvailablePhoneNum(@RequestParam("phoneNum") String phoneNum) {
        Boolean result = memberService.checkIsAvailablePhoneNum(phoneNum);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signUp() {
        return null;
    }

}
