package org.voucher.voucherservice.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.voucher.voucherservice.data.entity.VoucherJournal;

import java.util.List;
import java.util.Optional;

public interface VoucherJournalRepository extends CrudRepository<VoucherJournal, Long> {
     Optional<List<VoucherJournal>> findAllByVoucherIdOrderByTimeStamp(Long voucherId);
}
