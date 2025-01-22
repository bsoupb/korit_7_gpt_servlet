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
    private T data;
    // 와일드 카드
    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(200, "success", data);
    }

    public static <T> ResponseDto<T> fail(T data) {
        return new ResponseDto<>(400, "fail", data);
    }
}
