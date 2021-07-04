package org.voucher.voucherservice.exception;

import org.voucher.voucherservice.data.enums.ErrorCode;

public class VoucherAlreadyRedeemedException extends AbstractException {
    protected VoucherAlreadyRedeemedException(String voucherCode) {
        super(ErrorCode.VSEC_005, String.format("Voucher '%s' has been already redeemed"));
    }
}
