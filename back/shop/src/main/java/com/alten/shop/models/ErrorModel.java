package com.alten.shop.models;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorModel {

    private Date timestamp;

    private HttpStatus httpCode;

    private String error;

    private String message;
}
