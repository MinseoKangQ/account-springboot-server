package kr.go.data.member.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class LoginDto {

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
    }

}
