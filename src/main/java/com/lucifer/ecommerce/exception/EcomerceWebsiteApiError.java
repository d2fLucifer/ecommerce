package com.lucifer.ecommerce.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class EcomerceWebsiteApiError extends RuntimeException {
    private HttpStatus status;
    private String message;

    public EcomerceWebsiteApiError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public EcomerceWebsiteApiError(HttpStatus status, String message, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

}
