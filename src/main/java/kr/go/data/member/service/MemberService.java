package kr.go.data.member.service;

public interface MemberService {

    /**
     * @param id
     * @return
     */
    Boolean checkIsPresentId(String id);

    /**
     * @param email
     * @return
     */
    Boolean checkIsPresentEmail(String email);

    /**
     * @param phoneNum
     * @return
     */
    Boolean checkIsPresentPhoneNum(String phoneNum);

    /**
     * @return
     */
    Boolean signUp();
}
