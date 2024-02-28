package kr.go.data.member.service;

import kr.go.data.member.dto.ExampleDto;
import kr.go.data.member.repository.MemberRepository;
import kr.go.data.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<CustomApiResponse<?>> checkEmailExists(String email) {
        return null;
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> checkPhoneExists(String phone) {
        return null;
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> checkUserIdExists(String userId) {
        return null;
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> registerMember(ExampleDto dto) {
        return null;
    }
}
