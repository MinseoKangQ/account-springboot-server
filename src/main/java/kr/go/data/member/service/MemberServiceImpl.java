package kr.go.data.member.service;

import kr.go.data.member.dto.ExampleDto;
import kr.go.data.member.repository.MemberRepository;
import kr.go.data.util.exception.CustomValidationException;
import kr.go.data.util.exception.EntityDuplicatedException;
import kr.go.data.util.response.CustomApiResponse;
import kr.go.data.util.valid.CustomValid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<CustomApiResponse<?>> checkEmailExists(String email) {

        // 이메일 형식 검증
        if (!CustomValid.isEmailValid(email)) {
            throw new CustomValidationException("이메일 형식을 맞춰주세요.");
        }

        // 이메일 중복 검증
        boolean emailExists = memberRepository.findByEmail(email).isPresent();
        if (emailExists) {
            throw new EntityDuplicatedException("이미 사용중인 이메일입니다.");
        }

        // 응답
        CustomApiResponse<Object> resultBody = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "사용 가능한 이메일입니다.");
        return ResponseEntity.ok(resultBody);
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> checkPhoneExists(String phone) {

        // 전화번호 형식 검증
        if (!CustomValid.isPhoneValid(phone)) {
            throw new CustomValidationException("전화번호 형식을 맞춰주세요.");
        }

        // 전화번호 중복 검증
        boolean phoneExists = memberRepository.findByPhone(phone).isPresent();
        if (phoneExists) {
            throw new EntityDuplicatedException("이미 사용중인 전화번호입니다.");
        }

        // 응답
        CustomApiResponse<Object> resultBody = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "사용 가능한 전화번호입니다.");
        return ResponseEntity.ok(resultBody);
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> checkUserIdExists(String userId) {

        // 사용자 아이디 형식 검증
        if (!CustomValid.isUserIdValid(userId)) {
            throw new CustomValidationException("아이디는 영문자와 숫자만 포함할 수 있습니다.");
        }

        // 사용자 아이디 중복 검증
        boolean userIdExists = memberRepository.findByUserId(userId).isPresent();
        if (userIdExists) {
            throw new EntityDuplicatedException("이미 사용중인 아이디입니다.");
        }

        // 응답
        CustomApiResponse<Object> resultBody = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "사용 가능한 아이디입니다.");
        return ResponseEntity.ok(resultBody);
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> registerMember(ExampleDto dto) {
        return null;
    }
}
