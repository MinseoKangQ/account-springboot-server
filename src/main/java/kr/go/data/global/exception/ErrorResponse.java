package kr.go.data.global.exception;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@Builder
public class ErrorResponse {
    private String message;
    private String code;
    private int status;
    private List<FieldError> errors;

    @Getter
    @Builder
    public static class FieldError {
        private String field;
        private String value;
        private String reason;
    }
}
