package org.voucher.voucherservice.data.domain;

import java.time.LocalDateTime;

public class CreateXTimesUseVoucherRequest {
    private String voucherCode;
    private Long maxUse;
    private LocalDateTime expirationDateTime;
    private Double eurValue;

    public CreateXTimesUseVoucherRequest(String voucherCode, Long maxUse, LocalDateTime expirationDateTime, Double eurValue) {
        this.voucherCode = voucherCode;
        this.maxUse = maxUse;
        this.expirationDateTime = expirationDateTime;
        this.eurValue = eurValue;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public Long getMaxUse() {
        return maxUse;
    }

    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public Double getEurValue() {
        return eurValue;
    }

    @Override
    public String toString() {
        return "CreateXTimesUseVoucherRequest{" +
                "voucherCode='" + voucherCode + '\'' +
                ", maxUse=" + maxUse +
                ", expirationDateTime=" + expirationDateTime +
                ", eurValue=" + eurValue +
                '}';
    }
}
