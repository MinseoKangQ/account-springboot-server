package kr.go.data.member.service;

import kr.go.data.member.dto.CreateMemberDto;
import kr.go.data.domain.MemberEntity;
import kr.go.data.member.repository.MemberRepository;
import kr.go.data.util.exception.CustomValidationException;
import kr.go.data.util.exception.EntityDuplicatedException;
import kr.go.data.util.response.CustomApiResponse;
import kr.go.data.util.valid.CustomValid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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
        return ResponseEntity.status(HttpStatus.OK).body(resultBody);
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
        return ResponseEntity.status(HttpStatus.OK).body(resultBody);
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
        return ResponseEntity.status(HttpStatus.OK).body(resultBody);
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> createMember(CreateMemberDto.Req dto) {

        // 한 번 더 검증
        checkEmailExists(dto.getEmail());
        checkPhoneExists(dto.getPhone());
        checkUserIdExists(dto.getUserId());

        // 비밀번호 암호화
        String encodedPw = passwordEncoder.encode(dto.getPassword());

        // 멤버 생성
        MemberEntity member = CreateMemberDto.Req.builder()
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .userId(dto.getUserId())
                .password(encodedPw)
                .build().toEntity();

        // 저장
        memberRepository.save(member);

        // 응답
        CustomApiResponse<Object> resultBody = CustomApiResponse.createSuccess(HttpStatus.CREATED.value(), null, "회원가입에 성공하였습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(resultBody);

    }
}
