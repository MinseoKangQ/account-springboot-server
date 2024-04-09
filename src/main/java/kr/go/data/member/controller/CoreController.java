package kr.go.data.member.controller;

import kr.go.data.member.dto.ChangePasswordDto;
import kr.go.data.member.dto.CheckPasswordDto;
import kr.go.data.member.dto.SignUpDto;
import kr.go.data.member.dto.LoginDto;
import kr.go.data.member.service.MemberService;
import kr.go.data.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class CoreController implements CoreApi{

    private final MemberService memberService;

    // 비밀번호 변경 페이지 접속
    @GetMapping("default-information")
    public ResponseEntity<CustomApiResponse<?>> defaultInformation(@RequestParam("userId") String userId){
        ResponseEntity<CustomApiResponse<?>> result = memberService.defaultInformation(userId);
        return result;
    }

    // 기존 비밀번호와 같은지 확인
    @PostMapping("check-password")
    public ResponseEntity<CustomApiResponse<?>> checkPassword(@RequestBody CheckPasswordDto.Req dto) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.checkPassword(dto);
        return result;
    }

    // 비밀번호 변경
    @PutMapping("password")
    public ResponseEntity<CustomApiResponse<?>> changePassword(@RequestBody ChangePasswordDto.Req dto) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.changePassword(dto);
        return result;
    }

    // 회원 탈퇴
    @DeleteMapping("{userId}")
    public ResponseEntity<CustomApiResponse<?>> withdraw(@PathVariable("userId") String userId) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.withdraw(userId);
        return result;
    }

}
