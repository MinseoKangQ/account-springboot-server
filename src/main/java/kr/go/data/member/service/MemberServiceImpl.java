package kr.go.data.member.service;

import java.util.Optional;

import kr.go.data.member.dto.MemberDto;
import kr.go.data.member.dto.MemberDto.*;
import kr.go.data.member.entity.MemberEntity;
import kr.go.data.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Boolean checkIsAvailableId(String id) {
        Optional<MemberEntity> gotMember = memberRepository.findById(id);
        if(gotMember.isEmpty()) return true;
        else return false;
    }

    @Override
    public Boolean checkIsAvailableEmail(String email) {
        Optional<MemberEntity> gotMember = memberRepository.findByEmail(email);
        if(gotMember.isEmpty()) return true;
        else return false;
    }

    @Override
    public Boolean checkIsAvailablePhoneNum(String phoneNum) {
        Optional<MemberEntity> gotMember = memberRepository.findByPhoneNum(phoneNum);
        if(gotMember.isEmpty()) return true;
        else return false;
    }

    @Override
    public Boolean signUp(MemberDto.SignupReq dto) {
        MemberEntity gotEntity = dto.toEntity();
        memberRepository.save(gotEntity);
        return true;
    }
}
