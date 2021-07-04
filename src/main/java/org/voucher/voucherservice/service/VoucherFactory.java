package org.voucher.voucherservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.voucher.voucherservice.data.entity.Voucher;
import org.voucher.voucherservice.data.enums.RedemptionType;

import java.time.LocalDateTime;

public final class VoucherFactory {
    public static final Logger LOGGER = LoggerFactory.getLogger(VoucherFactory.class);

    private VoucherFactory(){
    }

    public static Voucher createSingleUseVoucher(String name, LocalDateTime expirationTime, Double eurValue){
        return new Voucher(name, RedemptionType.SINGLE,expirationTime, 1L, eurValue);
    }
}
