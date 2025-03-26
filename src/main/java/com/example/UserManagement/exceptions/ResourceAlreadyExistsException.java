

package com.example.UserManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// This annotation indicates that an occurrence of this exception will result in an HTTP 409 response
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}