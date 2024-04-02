package kr.go.data.member.dto;

import kr.go.data.domain.MemberEntity;
import lombok.Builder;
import lombok.Getter;

public class DefaultInformationDto {

    @Getter
    public static class Res {

        private String email;
        private String phone;
        private String userId;

        @Builder
        public Res(MemberEntity member) {
            this.email = member.getEmail();
            this.phone = member.getPhone();
            this.userId = member.getUserId();
        }
    }
}
