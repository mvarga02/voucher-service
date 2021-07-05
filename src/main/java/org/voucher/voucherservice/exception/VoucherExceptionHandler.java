package org.voucher.voucherservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.voucher.voucherservice.data.domain.ErrorMessageResponse;

@RestControllerAdvice
public class VoucherExceptionHandler {

    @ExceptionHandler(VoucherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleVoucherNotFoundException(VoucherNotFoundException e, WebRequest request){
        ErrorMessageResponse response = new ErrorMessageResponse();
        response.setErrorCode(e.getErrorCode());
        response.setMessage(e.getMessage());
        return response;
    }
}
