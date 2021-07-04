package org.voucher.voucherservice.data.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.voucher.voucherservice.data.enums.ActivityType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
public class VoucherJournal {
    @Id
    @GeneratedValue
    private Long id;
    @JoinColumn( nullable = false)
    private Long voucherId;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime timeStamp;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    public VoucherJournal() {
    }

    public VoucherJournal(Long voucherId, ActivityType activityType) {
        this.voucherId = voucherId;
        this.activityType = activityType;
    }

    public Long getId() {
        return id;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public ActivityType getActivityType() {
        return activityType;
    }
}
