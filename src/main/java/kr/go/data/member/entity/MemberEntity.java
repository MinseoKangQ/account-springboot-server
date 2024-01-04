package kr.go.data.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "Member")
@Table(name = "Member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

    @Id @GeneratedValue
    private Long no;

    @Column(name = "member_id")
    private String id;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_phone_num")
    private String phoneNum;

    @Builder
    public MemberEntity(String id, String password, String name, String email, String phoneNum) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }
}
