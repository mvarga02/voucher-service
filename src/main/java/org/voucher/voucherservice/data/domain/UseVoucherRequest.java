package org.voucher.voucherservice.data.domain;

public class UseVoucherRequest {
    String voucherCode;

    public UseVoucherRequest() {
    }

    public UseVoucherRequest(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }
}
