package org.voucher.voucherservice.data.domain;

import java.time.LocalDateTime;

public class CreateXTimesUseVoucherRequest {
    private String voucherCode;
    private Long maxUse;
    private LocalDateTime expirationDateTime;
    private Double eurValue;

    public CreateXTimesUseVoucherRequest() {
    }

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

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public void setMaxUse(Long maxUse) {
        this.maxUse = maxUse;
    }

    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public void setEurValue(Double eurValue) {
        this.eurValue = eurValue;
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
