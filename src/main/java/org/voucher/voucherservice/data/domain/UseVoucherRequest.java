package org.voucher.voucherservice.data.domain;

public class UseVoucherRequest {
    String voucherCode;

    public UseVoucherRequest(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getVoucherCode() {
        return voucherCode;
    }
}
