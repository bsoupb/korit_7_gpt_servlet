package com.korit.servlet_study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
    private int status;
    private String message;
    private T body;
    // 와일드 카드
    public static <T> ResponseDto<T> success(T body) {
        return new ResponseDto<>(200, "success", body);
    }

    public static <T> ResponseDto<T> fail(T body) {
        return new ResponseDto<>(400, "fail", body);
    }

    public static <T> ResponseDto<T> forbidden(T body) {
        return new ResponseDto<>(403, "Forbidden", body);
    }
}
