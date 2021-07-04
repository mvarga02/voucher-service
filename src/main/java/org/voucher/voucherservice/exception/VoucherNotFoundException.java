package org.voucher.voucherservice.exception;

import org.voucher.voucherservice.data.enums.ErrorCode;

public class VoucherNotFoundException extends AbstractException{
    public VoucherNotFoundException(String voucherCode) {
        super(ErrorCode.VSEC_002, String.format("Voucher '%s' cannot be found",voucherCode));
    }
}
