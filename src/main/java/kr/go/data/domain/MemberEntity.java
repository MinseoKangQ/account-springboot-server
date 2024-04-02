package kr.go.data.domain;

import jakarta.persistence.*;
import kr.go.data.member.dto.ChangePasswordDto;
import kr.go.data.member.dto.CreateMemberDto;
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

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Builder
    public MemberEntity(String userId, String password, String email, String phone) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public void changePassword(String pw) {
        this.password = pw;
    }

}
