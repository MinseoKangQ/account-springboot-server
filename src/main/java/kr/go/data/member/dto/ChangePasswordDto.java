package kr.go.data.member.dto;

import lombok.Getter;

public class ChangePasswordDto {

    @Getter
    public static class Req {
        private String userId;
        private String pw1;
        private String pw2;
    }
}
