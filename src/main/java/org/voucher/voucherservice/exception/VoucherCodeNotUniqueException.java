package org.voucher.voucherservice.exception;

import org.voucher.voucherservice.data.enums.ErrorCode;

public class VoucherCodeNotUniqueException extends AbstractException{
    public VoucherCodeNotUniqueException(String invalidVoucherCode) {
        super(ErrorCode.VSEC_003, String.format("Voucher code '%s' is not unique",invalidVoucherCode));
    }
}
