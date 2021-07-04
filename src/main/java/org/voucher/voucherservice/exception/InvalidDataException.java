package org.voucher.voucherservice.exception;

import org.voucher.voucherservice.data.enums.ErrorCode;

public class InvalidDataException extends AbstractException{
    protected InvalidDataException(String input) {
        super(ErrorCode.VSEC_001, String.format("Entered input is invalid: %s",input));
    }
}
