package com.samanecorp.thymeleaf1.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EntityNotFoundException extends RuntimeException {

    String message;
}
