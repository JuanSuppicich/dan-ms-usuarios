package com.durandsuppicich.danmsusuarios.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ NotFoundException.class })
    @ResponseBody
    public MensajeError notFoundRequest(HttpServletRequest request, Exception exception) {
        return new MensajeError(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ BadRequestExeption.class,
    // org.springframework.dao.DuplicateKeyException.class,
    // org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
    // org.springframework.http.converter.HttpMessageNotReadableException.class,
    // org.springframework.web.bind.MethodArgumentNotValidException.class,
    // org.springframework.web.bind.MissingRequestHeaderException.class,
    // org.springframework.web.bind.MissingServletRequestParameterException.class
    })
    @ResponseBody
    public MensajeError badRequest(HttpServletRequest request, Exception exception) {
        return new MensajeError(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ ForbiddenException.class })
    @ResponseBody
    public MensajeError forbiddenRequest(HttpServletRequest request, Exception exception) {
        return new MensajeError(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ ConflictException.class,
        DataIntegrityViolationException.class
    })

    @ResponseBody
    public MensajeError conflic(HttpServletRequest request, Exception exception) {
        return new MensajeError(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({ UnauthorizedException.class })
    public void unauthorized() {
        // Sin mensaje.
    }

    // No deberia suceder
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public MensajeError fatalErrorUnexpectedException(HttpServletRequest request, Exception exception) {
        return new MensajeError(exception, request.getRequestURI());
    }

}
