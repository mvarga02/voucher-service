package org.voucher.voucherservice.exception;

import org.voucher.voucherservice.data.enums.ErrorCode;

public abstract class AbstractException extends Exception{
    private ErrorCode errorCode;
    private String message;

    protected AbstractException(ErrorCode errorCode, String message){
        super(message);
        this.errorCode=errorCode;
        this.message=message;
    }
}
