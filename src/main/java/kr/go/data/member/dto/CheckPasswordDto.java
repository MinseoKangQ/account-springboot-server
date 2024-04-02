package kr.go.data.member.dto;

import lombok.Getter;

public class CheckPasswordDto {

    @Getter
    public static class Req {
        private String userId;
        private String pw;
    }
}
