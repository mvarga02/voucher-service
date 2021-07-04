package org.voucher.voucherservice.exception;

import org.voucher.voucherservice.data.enums.ErrorCode;

public class VoucherInvalidDataException extends AbstractException {
    public VoucherInvalidDataException(String input) {
        super(ErrorCode.VSEC_001, String.format("Entered input is invalid: %s", input));
    }
}
