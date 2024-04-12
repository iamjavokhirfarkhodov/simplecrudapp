package com.springdatajdbcdemo.util;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResultMessage {
    private String message;
    private Integer statusCode;
    private Object data;

    public static ResponseEntity<ResultMessage> getResultMessage(HttpStatus status, Object data) {
        return ResponseEntity
                .status(status)
                .body(ResultMessage.builder().message(status.getReasonPhrase()).statusCode(status.value()).data(data).build());
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", object=" + data +
                '}';
    }
}
