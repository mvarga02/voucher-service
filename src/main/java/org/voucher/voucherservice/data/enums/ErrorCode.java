package org.voucher.voucherservice.data.enums;

public enum ErrorCode {
    VSEC_001("Invalid input data"),
    VSEC_002("Voucher not found"),
    VSEC_003("Voucher name not unique"),
    VSEC_004("Voucher expired"),
    VSEC_005("Voucher already redeemed"),
    VSEC_006("Voucher deleted");

    private String errorMsg;

    ErrorCode(String errorMsg){
        this.errorMsg=errorMsg;
    }
}
