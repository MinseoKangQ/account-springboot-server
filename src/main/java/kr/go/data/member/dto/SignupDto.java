package kr.go.data.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
    private String id;
    private String password;
    private String name;
    private String email;
    private String phoneNum;
}
