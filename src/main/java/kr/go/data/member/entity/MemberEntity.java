package kr.go.data.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Member")
@Table(name = "Member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
