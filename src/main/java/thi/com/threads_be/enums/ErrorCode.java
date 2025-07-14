package thi.com.threads_be.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCAUGHT_EXCEPTION(9999, "Uncaught exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERNAL_SERVER_ERROR(9998, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(9997, "Unauthorized", HttpStatus.UNAUTHORIZED),
    INVALID_KEY(9996, "Invalid key", HttpStatus.BAD_REQUEST),
    FORBIDDEN(9995, "Forbidden", HttpStatus.FORBIDDEN);

    int code;
    String message;
    HttpStatus status;
}
