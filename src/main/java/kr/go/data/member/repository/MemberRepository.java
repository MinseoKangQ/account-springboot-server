package kr.go.data.member.repository;

import java.util.Optional;
import kr.go.data.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    /**
     * 회원가입 시 아이디 중복 검사, 로그인, 로그아웃에 사용
     * @param id
     * @return
     */
    Optional<MemberEntity> findById(String id);

    /**
     * 회원가입 시 이메일 중복 검사에 사용
     * @param email
     * @return
     */
    Optional<MemberEntity> findByEmail(String email);

    /**
     * 회원가입 시 핸드폰 번호 중복 검사에 사용
     * @param phoneNum
     * @return
     */
    Optional<MemberEntity> findByPhoneNum(String phoneNum);

}
