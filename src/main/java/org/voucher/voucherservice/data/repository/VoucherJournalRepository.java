package org.voucher.voucherservice.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.voucher.voucherservice.data.entity.VoucherJournal;

public interface VoucherJournalRepository extends CrudRepository<VoucherJournal, Long> {
}
