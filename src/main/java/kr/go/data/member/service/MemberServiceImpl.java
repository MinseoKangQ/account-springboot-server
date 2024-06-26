package kr.go.data.member.service;

import kr.go.data.util.jwt.JwtTokenProvider;
import kr.go.data.member.dto.ChangePasswordDto;
import kr.go.data.member.dto.CheckPasswordDto;
import kr.go.data.member.dto.SignUpDto;
import kr.go.data.domain.MemberEntity;
import kr.go.data.member.dto.DefaultInformationDto;
import kr.go.data.member.dto.LoginDto;
import kr.go.data.member.repository.MemberRepository;
import kr.go.data.util.exception.CustomValidationException;
import kr.go.data.util.exception.EntityDuplicatedException;
import kr.go.data.util.exception.EntityNotFoundException;
import kr.go.data.util.exception.PasswordIncorrectException;
import kr.go.data.util.exception.PasswordNotChangedException;
import kr.go.data.util.response.CustomApiResponse;
import kr.go.data.util.valid.CustomValid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final JwtTokenProvider jwtTokenProvider;
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
    public ResponseEntity<CustomApiResponse<?>> createMember(SignUpDto.Req dto) {

        // 한 번 더 검증
        checkEmailExists(dto.getEmail());
        checkPhoneExists(dto.getPhone());
        checkUserIdExists(dto.getUserId());

        // 비밀번호 암호화
        String encodedPw = passwordEncoder.encode(dto.getPassword());

        // 멤버 생성
        MemberEntity member = SignUpDto.Req.builder()
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

    @Override
    public ResponseEntity<CustomApiResponse<?>> login(LoginDto.Req dto) {

        // 아이디가 존재하는지 확인 -> 존재하지 않는다면 error
        MemberEntity member = memberRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("id가 " + dto.getUserId() + "인 회원은 존재하지 않습니다."));

        // 비밀번호가 일치하는지 확인 -> 일치하지 않는다면 error
        if(!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new PasswordIncorrectException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인에 성공했으므로, 토큰 생성
        String token = jwtTokenProvider.createToken(dto.getUserId());

        // 응답에 토큰을 포함하여 반환
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token); // 토큰을 "Authorization" 헤더에 포함
        CustomApiResponse<Object> resultBody = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "로그인에 성공하였습니다.");
        return new ResponseEntity<>(resultBody, headers, HttpStatus.OK);

    }

    // 비밀번호 변경 페이지 접속 시 나오는 사용자 기본 정보
    @Override
    public ResponseEntity<CustomApiResponse<?>> defaultInformation(String userId) {

        // 회원 찾기
        MemberEntity memberEntity = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("id가 " + userId + "인 회원은 존재하지 않습니다."));

        DefaultInformationDto.Res data = DefaultInformationDto.Res.builder()
                .member(memberEntity)
                .build();

        CustomApiResponse<?> resultBody = CustomApiResponse.createSuccess(HttpStatus.OK.value(), data, "회원 정보가 정상적으로 조회되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(resultBody);
    }

    // 입력한 비밀번호가 현재 비밀번호와 같은지 확인
    @Override
    public ResponseEntity<CustomApiResponse<?>> checkPassword(CheckPasswordDto.Req dto) {

        // 회원 찾기
        MemberEntity memberEntity = memberRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("id가 " + dto.getUserId() + "인 회원은 존재하지 않습니다."));

        // 비밀번호가 같으면
        if (passwordEncoder.matches(dto.getPw(), memberEntity.getPassword())) {
            throw new PasswordNotChangedException("이전 비밀번호와 동일합니다.");
        }

        // 비밀번호가 같지 않다면
        CustomApiResponse<?> resultBody = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "사용 가능한 비밀번호입니다.");
        return ResponseEntity.status(HttpStatus.OK).body(resultBody);

    }

    // 비밀번호 변경
    @Override
    public ResponseEntity<CustomApiResponse<?>> changePassword(ChangePasswordDto.Req dto) {

        // 회원 찾기
        MemberEntity memberEntity = memberRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("id가 " + dto.getUserId() + "인 회원은 존재하지 않습니다."));

        // pw1과 pw2가 같은지 확인
        if (!dto.getPw1().equals(dto.getPw2())) {
            throw new PasswordIncorrectException("비밀번호가 일치하지 않습니다.");
        }

        // 이전 비밀번호와 같으면
        if (passwordEncoder.matches(dto.getPw1(), memberEntity.getPassword())) {
            throw new PasswordNotChangedException("이전 비밀번호와 동일합니다.");
        }

        // 비밀번호 변경
        memberEntity.changePassword(passwordEncoder.encode(dto.getPw1()));
        memberRepository.save(memberEntity);

        // 응답
        CustomApiResponse<?> resultBody = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "비밀번호 변경이 완료되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(resultBody);

    }

    // 회원 탈퇴
    @Override
    public ResponseEntity<CustomApiResponse<?>> withdraw(String userId) {

        // 별 다른 검증 없이 유저가 존재하기만 한다면 회원탈퇴
        MemberEntity memberEntity = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("id가 " + userId + "인 회원은 존재하지 않습니다."));

        memberRepository.delete(memberEntity);

        CustomApiResponse<?> resultBody = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "회원이 정상적으로 탈퇴되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(resultBody);
    }
}
