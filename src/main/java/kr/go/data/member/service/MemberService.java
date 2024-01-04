package kr.go.data.member.service;

import kr.go.data.member.dto.MemberDto;

public interface MemberService {

    Boolean checkIsAvailableId(String id);

    Boolean checkIsAvailableEmail(String email);

    Boolean checkIsAvailablePhoneNum(String phoneNum);

    Boolean signUp(MemberDto.SignupReq signupDto);
}
