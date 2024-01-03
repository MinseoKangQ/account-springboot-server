package kr.go.data.member.service;

import kr.go.data.member.dto.SignupDto;

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
    Boolean signUp(SignupDto signupDto);
}
