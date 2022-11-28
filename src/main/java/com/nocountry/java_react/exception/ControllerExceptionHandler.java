package com.nocountry.java_react.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nocountry.java_react.commons.enums.EExceptionMessage;
import com.nocountry.java_react.dto.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String typeException, String message, Exception exception) {
        return new ErrorResponse(httpStatus.value(), typeException, message, exception.getMessage());
    }

    private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String typeException, String message, List<String> moreInfo) {
        return new ErrorResponse(httpStatus.value(), typeException, message, moreInfo);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyExistException.class)
    protected ResponseEntity<ErrorResponse> handlerEmailAlreadyExistException(EmailAlreadyExistException exception) {
        ErrorResponse errorResponse;
        HttpStatus status;
        String typeException = "EMAIL EXCEPTION";
        String message = "EMAIL ALREADY EXIST EXCEPTION";
        status = HttpStatus.INTERNAL_SERVER_ERROR;

        errorResponse = buildErrorResponse(status,
                typeException,
                message,
                exception);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = GlobalException.class)
    protected ResponseEntity<ErrorResponse> handlerGlobalException(GlobalException exception) {
        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "GLOBAL EXCEPTION";
        if (exception.getMessage().equals(EExceptionMessage.ID_NOT_FOUND.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.ID_NOT_FOUND.toString(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.RESPONSE_WRONG_DATA.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.RESPONSE_WRONG_DATA.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = PhotographerException.class)
    protected ResponseEntity<ErrorResponse> handlerPhotographerException(PhotographerException exception) {
        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "PHOTOGRAPHER EXCEPTION";
        if (exception.getMessage().equals(EExceptionMessage.PHOTOGRAPHER_NOT_FOUND.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.PHOTOGRAPHER_NOT_FOUND.toString(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_PHOTOGRAPHERS_IS_EMPTY.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.OK, typeException,
                    EExceptionMessage.THE_LIST_OF_PHOTOGRAPHERS_IS_EMPTY.toString(),
                    exception);
            status = HttpStatus.OK;
        }
        if (exception.getMessage().equals(EExceptionMessage.PASSWORDS_DO_NOT_MATCH.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.PASSWORDS_DO_NOT_MATCH.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.WRONG_PASSWORD.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.WRONG_PASSWORD.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.OLD_PASSWORD_DOES_NOT_MATCH.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.OLD_PASSWORD_DOES_NOT_MATCH.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.NEW_PASSWORDS_DO_NOT_MATCH.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.NEW_PASSWORDS_DO_NOT_MATCH.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = BuyerException.class)
    protected ResponseEntity<ErrorResponse> handlerBuyerException(BuyerException exception) {
        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "BUYER EXCEPTION";
        if (exception.getMessage().equals(EExceptionMessage.BUYER_NOT_FOUND.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.BUYER_NOT_FOUND.toString(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_LIST_OF_BUYERS_IS_EMPTY.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.OK, typeException,
                    EExceptionMessage.THE_LIST_OF_BUYERS_IS_EMPTY.toString(),
                    exception);
            status = HttpStatus.OK;
        }
        if (exception.getMessage().equals(EExceptionMessage.PASSWORDS_DO_NOT_MATCH.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.PASSWORDS_DO_NOT_MATCH.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.WRONG_PASSWORD.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.WRONG_PASSWORD.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.OLD_PASSWORD_DOES_NOT_MATCH.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.OLD_PASSWORD_DOES_NOT_MATCH.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.NEW_PASSWORDS_DO_NOT_MATCH.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.NEW_PASSWORDS_DO_NOT_MATCH.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = PhotoException.class)
    protected ResponseEntity<ErrorResponse> handlerPhotoException(PhotoException exception) {
        ErrorResponse errorResponse = null;
        HttpStatus status = null;
        String typeException = "PHOTO EXCEPTION";
        if (exception.getMessage().equals(EExceptionMessage.PHOTO_NOT_FOUND.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, typeException,
                    EExceptionMessage.PHOTO_NOT_FOUND.toString(),
                    exception);
            status = HttpStatus.NOT_FOUND;
        }
        if (exception.getMessage().equals(EExceptionMessage.REQUEST_WRONG_DATA.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.REQUEST_WRONG_DATA.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_FOLDER_CANNOT_BE_INITIALIZED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.THE_FOLDER_CANNOT_BE_INITIALIZED.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (exception.getMessage().equals(EExceptionMessage.THE_PHOTO_CANNOT_BE_SAVED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, typeException,
                    EExceptionMessage.THE_PHOTO_CANNOT_BE_SAVED.toString(),
                    exception);
            status = HttpStatus.BAD_REQUEST;
        }
        if (exception.getMessage().equals(EExceptionMessage.PHOTO_DELETED.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.OK, typeException,
                    EExceptionMessage.PHOTO_DELETED.toString(),
                    exception);
            status = HttpStatus.OK;
        }
        if (exception.getMessage().equals(EExceptionMessage.ERROR_DELETING_PHOTO.toString())) {
            errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, typeException,
                    EExceptionMessage.ERROR_DELETING_PHOTO.toString(),
                    exception);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        assert status != null;
        return new ResponseEntity<>(errorResponse, status);
    }
}