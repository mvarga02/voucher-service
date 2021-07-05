package org.voucher.voucherservice.data.domain;

import java.time.LocalDateTime;

public class CreateSingleUseVoucherRequest {
    private String voucherCode;
    private LocalDateTime expirationDateTime;
    private Double eurValue;

    public CreateSingleUseVoucherRequest() {
    }

    public CreateSingleUseVoucherRequest(String voucherCode, LocalDateTime expirationDateTime, Double eurValue) {
        this.voucherCode = voucherCode;
        this.expirationDateTime = expirationDateTime;
        this.eurValue = eurValue;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public Double getEurValue() {
        return eurValue;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public void setEurValue(Double eurValue) {
        this.eurValue = eurValue;
    }

    @Override
    public String toString() {
        return "SingleUseVoucherRequest{" +
                "voucherCode='" + voucherCode + '\'' +
                ", expirationDateTime=" + expirationDateTime +
                ", eurValue=" + eurValue +
                '}';
    }
}
