package org.voucher.voucherservice.exception;

import org.voucher.voucherservice.data.enums.ErrorCode;

import java.time.LocalDateTime;

public class VoucherExpiredException extends AbstractException{
    public VoucherExpiredException(LocalDateTime expiredTime) {
        super(ErrorCode.VSEC_004, String.format("Voucher expired at: %s, current time: %s",expiredTime,LocalDateTime.now()));
    }
}
