package kr.go.data.member.service;

import kr.go.data.member.dto.ExampleDto;
import kr.go.data.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<CustomApiResponse<?>> checkEmailExists(String email);
    ResponseEntity<CustomApiResponse<?>>checkPhoneExists(String phone);
    ResponseEntity<CustomApiResponse<?>>checkUserIdExists(String userId);
    ResponseEntity<CustomApiResponse<?>>registerMember(ExampleDto dto);
}
