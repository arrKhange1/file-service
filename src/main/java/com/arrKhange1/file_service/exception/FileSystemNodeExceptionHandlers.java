package com.arrKhange1.file_service.exception;

import com.arrKhange1.file_service.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class FileSystemNodeExceptionHandlers extends ResponseEntityExceptionHandler  {
    @ExceptionHandler(GeneralFileSystemNodeException.class)
    public ResponseEntity<?> handleGeneralFileSystemNodeException(GeneralFileSystemNodeException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDTO(exception.getMessage(), new HashMap<>()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NodeNotFoundException.class)
    public ResponseEntity<?> handleNodeNotFound(NodeNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDTO(exception.getMessage(), new HashMap<>()), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> details = new HashMap<>();
        ex.getFieldErrors().forEach(fieldError -> {
            details.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return new ResponseEntity<>(new ErrorDTO(ex.getMessage(), details), HttpStatus.BAD_REQUEST);
    }
}
