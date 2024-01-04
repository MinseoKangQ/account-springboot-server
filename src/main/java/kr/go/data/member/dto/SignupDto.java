package kr.go.data.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SignupDto {

    @NotBlank(message = "아이디는 비어있을 수 없습니다.")
    private String id;

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    private String password;

    @NotBlank(message = "이름은 비어있을 수 없습니다.")
    private String name;

    @Email
    @NotBlank(message = "이메일은 비어있을 수 없습니다.")
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "XXX-XXXX-XXXX 형식으로 입력해주세요.")
    @NotBlank(message = "전화번호는 비어있을 수 없습니다.")
    private String phoneNum;

}
