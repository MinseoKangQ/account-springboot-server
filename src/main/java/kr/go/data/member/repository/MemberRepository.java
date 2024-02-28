package kr.go.data.member.repository;

import java.util.Optional;
import kr.go.data.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByEmail(String email);
    Optional<MemberEntity> findByPhone(String phone);
    Optional<MemberEntity> findByUserId(String userId);
}
