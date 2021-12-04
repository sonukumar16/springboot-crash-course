package com.codebuffer.springboot.crash.course.error.handler;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {

    private HttpStatus status;
    private String message;
    private List<String> details;
}
