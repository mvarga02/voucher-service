package org.voucher.voucherservice.exception;

import org.voucher.voucherservice.data.enums.ErrorCode;

public abstract class AbstractException extends Exception{
    private final ErrorCode errorCode;
    private final String message;

    protected AbstractException(ErrorCode errorCode, String message){
        super(message);
        this.errorCode=errorCode;
        this.message=message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
