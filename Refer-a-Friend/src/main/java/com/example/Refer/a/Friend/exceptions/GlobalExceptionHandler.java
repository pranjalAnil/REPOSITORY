package com.example.Refer.a.Friend.exceptions;

import com.example.Refer.a.Friend.payloads.APIResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        APIResponse apiResponse = new APIResponse(message, false);
        return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AccessDenied.class)
    public ResponseEntity<APIResponse> accessDenied(AccessDenied ex){
        String message = ex.getMessage();
        APIResponse apiResponse = new APIResponse(message, false);
        return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(CodeGenerationError.class)
    public ResponseEntity<APIResponse> apiResponseResponseEntity(CodeGenerationError ex){
        String message = ex.getMessage();
        APIResponse apiResponse = new APIResponse(message, false);
        return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.CONFLICT);

    }
    @ExceptionHandler(ContactAccessIssue.class)
    public ResponseEntity<APIResponse> contactAccessIssue(ContactAccessIssue ex){
        String message=ex.getMessage();
        APIResponse apiResponse=new APIResponse(message,false);
        return new ResponseEntity<>(apiResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<APIResponse> alreadyExists(AlreadyExists ex){
        String message=ex.getMessage();
        APIResponse apiResponse=new APIResponse(message,false);
        return new ResponseEntity<>(apiResponse,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(WrongConfirmationPassword.class)
    public ResponseEntity<APIResponse> wrongConfirmationPassword(WrongConfirmationPassword ex){
        String message=ex.getMessage();
        APIResponse apiResponse=new APIResponse(message,false);
        return new ResponseEntity<>(apiResponse,HttpStatus.CONFLICT);
    }


    @ExceptionHandler(UnableToAccess.class)
    public ResponseEntity<APIResponse> unableToAccess(UnableToAccess ex){
        String message=ex.getMessage();
        APIResponse apiResponse=new APIResponse(message,false);
        return new ResponseEntity<>(apiResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<APIResponse> dataIntegrity(DataIntegrityViolationException ex){
        String message=ex.getMessage();
        APIResponse apiResponse=new APIResponse(message,false);
        return new ResponseEntity<>(apiResponse,HttpStatus.CONFLICT);
    }
}
