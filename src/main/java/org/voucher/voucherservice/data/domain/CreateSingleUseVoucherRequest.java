package org.voucher.voucherservice.data.domain;

import java.time.LocalDateTime;

public class CreateSingleUseVoucherRequest {
    private String voucherCode;
    private LocalDateTime expirationDateTime;
    private Double eurValue;

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

    @Override
    public String toString() {
        return "SingleUseVoucherRequest{" +
                "voucherCode='" + voucherCode + '\'' +
                ", expirationDateTime=" + expirationDateTime +
                ", eurValue=" + eurValue +
                '}';
    }
}
