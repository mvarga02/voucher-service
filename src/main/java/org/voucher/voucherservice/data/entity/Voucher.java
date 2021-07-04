package org.voucher.voucherservice.data.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.voucher.voucherservice.data.enums.RedemptionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Voucher {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true, length = 128)
    private String code;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RedemptionType redemptionType;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdTime;
    @Column(nullable = false)
    private LocalDateTime expirationTime;
    @Column(nullable = false)
    private Long useCounter;
    @Column(nullable = false)
    private Long maxUse;
    @Column(nullable = false)
    private Double eurValue;

    public Voucher() {
    }

    public Voucher(String code, RedemptionType redemptionType, LocalDateTime expirationTime, Long maxUse, Double eurValue) {
        this.code = code;
        this.redemptionType = redemptionType;
        this.expirationTime = expirationTime;
        this.useCounter = 0L;
        this.maxUse = maxUse;
        this.eurValue = eurValue;
    }

    public boolean checkValidity(){
        if(LocalDateTime.now().isAfter(this.expirationTime)){
            return false;
        }
        if (this.redemptionType==RedemptionType.SINGLE){
            return useCounter<=1;
        }
        if (this.redemptionType==RedemptionType.X_TIMES){
            return useCounter<=maxUse;
        }
        return true;
    }

    public void useVoucher(){
        this.useCounter++;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String name) {
        this.code = name;
    }

    public RedemptionType getRedemptionType() {
        return redemptionType;
    }

    public void setRedemptionType(RedemptionType redemptionType) {
        this.redemptionType = redemptionType;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Long getUseCounter() {
        return useCounter;
    }

    public void setUseCounter(Long useCounter) {
        this.useCounter = useCounter;
    }

    public Long getMaxUse() {
        return maxUse;
    }

    public void setMaxUse(Long maxUse) {
        this.maxUse = maxUse;
    }

    public Double getEurValue() {
        return eurValue;
    }

    public void setEurValue(Double eurValue) {
        this.eurValue = eurValue;
    }
}
