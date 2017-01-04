package ua.com.codespace.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.codespace.exception.UserNotFoundException;

@ControllerAdvice(basePackages = "ua.com.codespace.web")
public class WebAppExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public String userNotFound(UserNotFoundException e) {
        return e.getMessage();
    }
}
