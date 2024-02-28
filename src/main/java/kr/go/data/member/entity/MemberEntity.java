package kr.go.data.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Member")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_phone_num")
    private String phoneNum;

    @Builder
    public MemberEntity(String userId, String password, String email, String phoneNum) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
    }
}
