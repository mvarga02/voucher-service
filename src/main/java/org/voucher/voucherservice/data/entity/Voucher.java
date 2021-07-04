package org.voucher.voucherservice.data.entity;

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
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RedemptionType redemptionType;
    @Column(nullable = false)
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
}
