package kr.go.data.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import kr.go.data.member.entity.MemberEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignupReq {
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

        @Builder
        public SignupReq(String id, String password, String name, String email, String phoneNum) {
            this.id = id;
            this.password = password;
            this.name = name;
            this.email = email;
            this.phoneNum = phoneNum;
        }

        public MemberEntity toEntity() {
            return MemberEntity.builder()
                    .id(this.id)
                    .password(this.password)
                    .name(this.name)
                    .email(this.email)
                    .phoneNum(this.phoneNum)
                    .build();
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LoginReq {

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LoginRes {

    }
}
