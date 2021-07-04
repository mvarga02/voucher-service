package org.voucher.voucherservice.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.voucher.voucherservice.data.entity.Voucher;

import java.util.Optional;

@Repository
public interface VoucherRepository extends CrudRepository<Voucher, Long> {
     Optional<Voucher> findVoucherByCode(String code);
}
