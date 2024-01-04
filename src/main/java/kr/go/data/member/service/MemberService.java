package kr.go.data.member.service;

import kr.go.data.member.dto.MemberDto;

public interface MemberService {

    /**
     * @param id
     * @return
     */
    Boolean checkIsAvailableId(String id);

    /**
     * @param email
     * @return
     */
    Boolean checkIsAvailableEmail(String email);

    /**
     * @param phoneNum
     * @return
     */
    Boolean checkIsAvailablePhoneNum(String phoneNum);

    /**
     * @return
     */
    Boolean signUp(MemberDto.SignupReq signupDto);
}
