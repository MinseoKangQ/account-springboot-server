package kr.go.data.member.service;

import kr.go.data.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Boolean checkIsPresentId(String id) {
        return null;
    }

    @Override
    public Boolean checkIsPresentEmail(String email) {
        return null;
    }

    @Override
    public Boolean checkIsPresentPhoneNum(String phoneNum) {
        return null;
    }

    @Override
    public Boolean signUp() {
        return null;
    }

}
