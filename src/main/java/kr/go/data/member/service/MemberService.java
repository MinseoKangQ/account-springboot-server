package kr.go.data.member.service;

import kr.go.data.member.dto.ChangePasswordDto;
import kr.go.data.member.dto.CheckPasswordDto;
import kr.go.data.member.dto.CreateMemberDto;
import kr.go.data.member.dto.LoginDto;
import kr.go.data.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<CustomApiResponse<?>> checkEmailExists(String email);
    ResponseEntity<CustomApiResponse<?>> checkPhoneExists(String phone);
    ResponseEntity<CustomApiResponse<?>> checkUserIdExists(String userId);
    ResponseEntity<CustomApiResponse<?>> createMember(CreateMemberDto.Req dto);
    ResponseEntity<CustomApiResponse<?>> login(LoginDto.Req dto);
    ResponseEntity<CustomApiResponse<?>> defaultInformation(String userId);
    ResponseEntity<CustomApiResponse<?>> checkPassword(CheckPasswordDto.Req dto);
    ResponseEntity<CustomApiResponse<?>> changePassword(ChangePasswordDto.Req dto);

}
