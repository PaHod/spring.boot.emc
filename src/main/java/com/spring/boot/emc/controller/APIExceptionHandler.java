package com.spring.boot.emc.controller;

import com.spring.boot.emc.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class APIExceptionHandler extends AbstractErrorController {

    private static final String ERROR_PATH = "/error";
    private final ErrorAttributes errorAttributes;

    @Autowired
    public APIExceptionHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(path = ERROR_PATH)
    public ResponseEntity<?> handleError(HttpServletRequest request) {
        HttpStatus status = getStatus(request);

        ErrorAttributeOptions opt = ErrorAttributeOptions.defaults();
        Map<String, Object> errors = getErrorAttributes(request, opt);
        getApiException(request).ifPresent(apiError -> {
            errors.put("message", apiError.getMessage());
            errors.put("internalCode", apiError.internalCode());
        });
        // don't expose exception!
        errors.remove("exception");

        return ResponseEntity.status(status).body(errors);
    }

    private Optional<APIException> getApiException(HttpServletRequest request) {
        WebRequest webRequest = new ServletWebRequest(request);
        Throwable throwable = errorAttributes.getError(webRequest);
        if (throwable instanceof APIException) {
            APIException exception = (APIException) throwable;
            return Optional.of(exception);
        }

        return Optional.empty();
    }
}