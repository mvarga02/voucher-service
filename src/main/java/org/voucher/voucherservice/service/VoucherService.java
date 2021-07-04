package org.voucher.voucherservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.voucher.voucherservice.data.entity.Voucher;
import org.voucher.voucherservice.data.entity.VoucherJournal;
import org.voucher.voucherservice.data.enums.ActivityType;
import org.voucher.voucherservice.data.enums.StatusType;
import org.voucher.voucherservice.data.repository.VoucherJournalRepository;
import org.voucher.voucherservice.data.repository.VoucherRepository;
import org.voucher.voucherservice.exception.VoucherAlreadyRedeemedException;
import org.voucher.voucherservice.exception.VoucherCodeNotUniqueException;
import org.voucher.voucherservice.exception.VoucherExpiredException;
import org.voucher.voucherservice.exception.VoucherInvalidDataException;
import org.voucher.voucherservice.exception.VoucherNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherService.class);

    private final VoucherRepository voucherRepository;
    private final VoucherJournalRepository journalRepository;

    public VoucherService(VoucherRepository voucherRepository, VoucherJournalRepository journalRepository) {
        this.voucherRepository = voucherRepository;
        this.journalRepository = journalRepository;
    }

    public Voucher getVoucher(String code) throws VoucherInvalidDataException, VoucherNotFoundException {
        if (code == null || code.isBlank()) {
            LOGGER.warn("Voucher code invalid: '{}'", code);
            throw new VoucherInvalidDataException(String.format("Voucher code: '%s'", code));
        }
        LOGGER.info("Getting voucher '{}' from DB", code);
        Optional<Voucher> temp = voucherRepository.findVoucherByCode(code);
        if (temp.isEmpty()) {
            LOGGER.warn("Voucher '{}' cannot be found in DB", code);
            throw new VoucherNotFoundException(code);
        }
        LOGGER.info("Voucher found: {}", temp.get());
        return temp.get();
    }

    public List<VoucherJournal> getVoucherJournal(long voucherId) {
        Optional<List<VoucherJournal>> list =
                journalRepository.findAllByVoucherIdOrderByTimeStamp(voucherId);
        return list.orElse(Collections.emptyList());
    }

    public Voucher saveVoucher(Voucher voucher) throws VoucherCodeNotUniqueException {
        Optional<Voucher> temp = voucherRepository.findVoucherByCode(voucher.getCode());
        if (temp.isPresent()) {
            LOGGER.warn("Voucher code '{}' NOT unique", voucher.getCode());
            throw new VoucherCodeNotUniqueException(voucher.getCode());
        }
        LOGGER.info("Saving voucher to db: {}", voucher);
        journalRepository.save(new VoucherJournal(voucher.getId(), ActivityType.CREATED));
        return voucherRepository.save(voucher);
    }

    public Voucher deleteVoucher(String code) throws VoucherNotFoundException, VoucherInvalidDataException {
        Voucher voucher = getVoucher(code);
        LOGGER.info("Deleting voucher: {}", voucher);
        voucher.setStatusType(StatusType.DELETED);
        voucherRepository.save(voucher);
        journalRepository.save(new VoucherJournal(voucher.getId(), ActivityType.DELETED));
        return voucher;
    }

    public Voucher useVoucher(String code) throws VoucherNotFoundException, VoucherInvalidDataException, VoucherAlreadyRedeemedException, VoucherExpiredException {
        Voucher voucher = getVoucher(code);
        if (!voucher.checkValidity()) {
            voucher.setStatusType(StatusType.INVALID);
            voucherRepository.save(voucher);
            LOGGER.warn("Voucher '{}' is invalid", voucher);
            throw new VoucherAlreadyRedeemedException(voucher.getCode());
        }
        if (!voucher.checkExpiration()) {
            voucher.setStatusType(StatusType.INVALID);
            voucherRepository.save(voucher);
            LOGGER.warn("Voucher '{}' is expired", voucher);
            throw new VoucherExpiredException(voucher.getExpirationTime());
        }
        LOGGER.info("Using voucher: {}", voucher);
        voucher.useVoucher();
        voucherRepository.save(voucher);
        journalRepository.save(new VoucherJournal(voucher.getId(), ActivityType.USED));
        return voucher;
    }
}
