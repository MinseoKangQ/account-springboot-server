package kr.go.data.member.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.go.data.domain.MemberEntity;
import lombok.*;

public class CreateMemberDto {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req {
        @NotEmpty(message = "비어있을 수 없습니다.")
        @NotNull(message = "필수값입니다.")
        private String userId;

        @NotEmpty(message = "비어있을 수 없습니다.")
        @NotNull(message = "필수값입니다.")
        private String password;

        @NotEmpty(message = "비어있을 수 없습니다.")
        @NotNull(message = "필수값입니다.")
        private String email;

        @NotEmpty(message = "비어있을 수 없습니다.")
        @NotNull(message = "필수값입니다.")
        private String phone;

        public MemberEntity toEntity() {
            return MemberEntity.builder()
                    .email(this.email)
                    .userId(this.userId)
                    .phone(this.phone)
                    .password(this.password)
                    .build();
        }
    }
}
