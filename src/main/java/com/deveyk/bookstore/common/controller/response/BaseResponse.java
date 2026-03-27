package com.deveyk.bookstore.common.controller.response;

import com.deveyk.bookstore.common.util.BsRandomUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class BaseResponse<T> {

    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();
    @Builder.Default
    private String code = BsRandomUtil.generateUUID();
    private HttpStatus httpStatus;
    private Boolean isSuccess;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T response;

    // 200
    public static final BaseResponse<Void> SUCCESS = BaseResponse.<Void>builder()
            .httpStatus(HttpStatus.OK)
            .isSuccess(true)
            .build();

    // 400
    public static final BaseResponse<Void> BAD_REQUEST = BaseResponse.<Void>builder()
            .httpStatus(HttpStatus.BAD_REQUEST)
            .isSuccess(false)
            .build();

    // 500
    public static final BaseResponse<Void> ERROR = BaseResponse.<Void>builder()
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .isSuccess(false)
            .build();

    public static <T> BaseResponse<T> of(final T response) {
        return BaseResponse.<T>builder()
                .httpStatus(HttpStatus.OK)
                .isSuccess(true)
                .response(response)
                .build();
    }

    public static <T> BaseResponse<T> of(HttpStatus status, boolean isSuccess, String message, final T response) {
        return BaseResponse.<T>builder()
                .httpStatus(status)
                .isSuccess(isSuccess)
                .message(message)
                .response(response)
                .build();
    }
}
