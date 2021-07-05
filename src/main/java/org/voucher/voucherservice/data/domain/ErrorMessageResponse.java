package org.voucher.voucherservice.data.domain;

import org.voucher.voucherservice.data.enums.ErrorCode;

public class ErrorMessageResponse {
    private ErrorCode errorCode;
    private String message;

    public ErrorMessageResponse() {
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
