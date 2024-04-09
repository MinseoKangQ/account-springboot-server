package kr.go.data.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.go.data.member.dto.ChangePasswordDto;
import kr.go.data.member.dto.CheckPasswordDto;
import kr.go.data.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "로그인 이후에 사용하는 API", description = "header에 유효한 token값이 필수")
public interface CoreApi {


    @Operation(summary = "[회원정보 변경 페이지] 필요한 정보 전달")
    @GetMapping("default-information")
    ResponseEntity<CustomApiResponse<?>> defaultInformation(@RequestParam("userId") String userId);

    @Operation(summary = "[회원정보 변경 페이지] 이전 비밀번호와 동일한지 확인")
    @PostMapping("check-password")
    ResponseEntity<CustomApiResponse<?>> checkPassword(@RequestBody CheckPasswordDto.Req dto);

    @Operation(summary = "[회원정보 변경 페이지] 비밀번호 변경")
    @PutMapping("password")
    ResponseEntity<CustomApiResponse<?>> changePassword(@RequestBody ChangePasswordDto.Req dto);

    @Operation(summary = "[회원탈퇴] 회원탈퇴")
    @DeleteMapping("{userId}")
    ResponseEntity<CustomApiResponse<?>> withdraw(@PathVariable("userId") String userId);
}
