package org.voucher.voucherservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.voucher.voucherservice.data.repository.VoucherJournalRepository;
import org.voucher.voucherservice.data.repository.VoucherRepository;

@Service
public class VoucherService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherService.class);

    private final VoucherRepository voucherRepository;
    private final VoucherJournalRepository journalRepository;

    public VoucherService(VoucherRepository voucherRepository, VoucherJournalRepository journalRepository) {
        this.voucherRepository = voucherRepository;
        this.journalRepository = journalRepository;
    }


}
