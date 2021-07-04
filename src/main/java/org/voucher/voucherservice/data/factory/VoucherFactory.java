package org.voucher.voucherservice.data.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.voucher.voucherservice.data.entity.Voucher;
import org.voucher.voucherservice.data.enums.RedemptionType;
import org.voucher.voucherservice.exception.VoucherInvalidDataException;

import java.time.LocalDateTime;

public final class VoucherFactory {
    public static final Logger LOGGER = LoggerFactory.getLogger(VoucherFactory.class);

    private VoucherFactory() {
    }

    public static Voucher createSingleUseVoucher(String code, LocalDateTime expirationTime, Double eurValue) throws VoucherInvalidDataException {
        if (code == null || code.isBlank()
                || expirationTime == null || expirationTime.isBefore(LocalDateTime.now())
                || eurValue == null || eurValue <= 0) {
            throw new VoucherInvalidDataException(String.format("code: '%s', expirationDate: '%s', EUR value: '%s'", code, expirationTime, eurValue));
        }
        return new Voucher(code, RedemptionType.SINGLE, expirationTime, 1L, eurValue);
    }

    public static Voucher createMultiUseVoucher(String code, LocalDateTime expirationTime, Double eurValue) throws VoucherInvalidDataException {
        if (code == null || code.isBlank()
                || expirationTime == null || expirationTime.isBefore(LocalDateTime.now())
                || eurValue == null || eurValue <= 0) {
            throw new VoucherInvalidDataException(String.format("code: '%s', expirationDate: '%s', EUR value: '%s'", code, expirationTime, eurValue));
        }
        return new Voucher(code, RedemptionType.MULTI, expirationTime, Long.MAX_VALUE, eurValue);
    }

    public static Voucher createXTimesUseVoucher(String code, Long maxUse, LocalDateTime expirationTime, Double eurValue) throws VoucherInvalidDataException {
        if (code == null || code.isBlank()
                || maxUse == null || maxUse <= 0
                || expirationTime == null || expirationTime.isBefore(LocalDateTime.now())
                || eurValue == null || eurValue <= 0) {
            throw new VoucherInvalidDataException(String.format("code: '%s', max use: '%s', expirationDate: '%s', EUR value: '%s'", code, maxUse, expirationTime, eurValue));
        }
        return new Voucher(code, RedemptionType.X_TIMES, expirationTime, maxUse, eurValue);
    }
}
