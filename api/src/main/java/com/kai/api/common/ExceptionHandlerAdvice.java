package com.kai.api.common;

import com.kai.api.common.exception.ExistsException;
import com.kai.api.common.exception.NotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;
@RestControllerAdvice
public class ExceptionHandlerAdvice {

         /**
         * 使用校验bean的方式校验RequestParam,需要声明该类
         */
        @Bean
        public MethodValidationPostProcessor methodValidationPostProcessor() {
            return new MethodValidationPostProcessor();
        }

        /**
         * 其他异常情况处理
         */
        @ExceptionHandler(value = {Exception.class})
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public BaseResponseBody handleAllException(Exception ex) {
            BaseResponseBody responseBody = new BaseResponseBody();
            responseBody.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseBody.setMessage(ex.getMessage() == null ? ex.toString() : ex.getMessage());
            return responseBody;
        }

        @ExceptionHandler(value = {ExistsException.class})
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public BaseResponseBody handleExistsException(ExistsException ex) {
            BaseResponseBody responseBody = new BaseResponseBody();
            responseBody.setCode(HttpStatus.BAD_REQUEST.value());
            responseBody.setMessage(ex.getMessage());
            return responseBody;
        }

        @ExceptionHandler(value = {NotFoundException.class})
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public BaseResponseBody handleNotFoundException(NotFoundException ex) {
            BaseResponseBody responseBody = new BaseResponseBody();
            responseBody.setCode(HttpStatus.NOT_FOUND.value());
            responseBody.setMessage(ex.getMessage());
            return responseBody;
        }

        @ExceptionHandler(value = {AccessDeniedException.class})
        @ResponseStatus(HttpStatus.FORBIDDEN)
        public BaseResponseBody handleAccessDeniedException(AccessDeniedException ex) {
            BaseResponseBody responseBody = new BaseResponseBody();
            responseBody.setCode(HttpStatus.FORBIDDEN.value());
            responseBody.setMessage(ex.getMessage());
            return responseBody;
        }

        /**
         * 不符合业务逻辑要求
         */
        @ExceptionHandler({IllegalArgumentException.class})
        public BaseResponseBody badRequestException(IllegalArgumentException exception) {
            BaseResponseBody<Object> responseBody = new BaseResponseBody<>();
            responseBody.setCode(HttpStatus.BAD_REQUEST.value());
            responseBody.setMessage(exception.getMessage());
            return responseBody;
        }

        /**
         * SpringMvc校验bean失败
         */
        @ExceptionHandler
        public BaseResponseBody badRequestException(MethodArgumentNotValidException exception) {
            BaseResponseBody<Object> responseBody = new BaseResponseBody<>();
            responseBody.setCode(HttpStatus.BAD_REQUEST.value());
            final FieldError fieldError = exception.getBindingResult().getFieldError();
            if (fieldError == null) {
                final ObjectError globalError = exception.getBindingResult().getGlobalError();
                responseBody.setMessage(globalError.getDefaultMessage());
            } else {
                responseBody.setMessage(Objects.requireNonNull(fieldError).getDefaultMessage());
            }
            return responseBody;
        }

        /**
         * SpringMvc校验RequestParam失败
         */
        @ExceptionHandler
        public BaseResponseBody badRequestException(ValidationException exception) {
            BaseResponseBody<Object> responseBody = new BaseResponseBody<>();
            responseBody.setCode(HttpStatus.BAD_REQUEST.value());
            responseBody.setMessage(exception.getMessage());
            return responseBody;
        }


}