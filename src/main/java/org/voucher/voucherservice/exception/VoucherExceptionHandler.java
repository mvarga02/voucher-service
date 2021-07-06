package org.voucher.voucherservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.voucher.voucherservice.data.domain.ErrorMessageResponse;

@RestControllerAdvice
public class VoucherExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherExceptionHandler.class);
    private static final String LOG_MSG = "{}: {}, in request: {}";

    public VoucherExceptionHandler() {
    }

    @ExceptionHandler(VoucherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleVoucherNotFoundException(AbstractException e, WebRequest request) {
        LOGGER.warn(LOG_MSG, e.getClass(), e.getMessage(), request);
        return createResponseObject(e);
    }

    @ExceptionHandler(value = {VoucherInvalidDataException.class, VoucherCodeNotUniqueException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse handleVoucherInvalidDataException(AbstractException e, WebRequest request) {
        LOGGER.warn(LOG_MSG, e.getClass(), e.getMessage(), request);
        return createResponseObject(e);
    }

    @ExceptionHandler(value = {VoucherAlreadyRedeemedException.class, VoucherExpiredException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessageResponse handleInvalidVoucherExceptions(AbstractException e, WebRequest request) {
        LOGGER.warn(LOG_MSG, e.getClass(), e.getMessage(), request);
        return createResponseObject(e);
    }

    private ErrorMessageResponse createResponseObject(AbstractException e) {
        ErrorMessageResponse response = new ErrorMessageResponse();
        response.setErrorCode(e.getErrorCode());
        response.setMessage(e.getMessage());
        return response;
    }
}
