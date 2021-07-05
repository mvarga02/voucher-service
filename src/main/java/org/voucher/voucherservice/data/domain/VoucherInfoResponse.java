package org.voucher.voucherservice.data.domain;

import org.voucher.voucherservice.data.entity.Voucher;
import org.voucher.voucherservice.data.entity.VoucherJournal;

import java.util.List;

public class VoucherInfoResponse {
    private Voucher voucher;
    private List<VoucherJournal> journal;

    public VoucherInfoResponse() {
    }

    public VoucherInfoResponse(Voucher voucher, List<VoucherJournal> journal) {
        this.voucher = voucher;
        this.journal = journal;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public List<VoucherJournal> getJournal() {
        return journal;
    }

    public void setJournal(List<VoucherJournal> journal) {
        this.journal = journal;
    }
}
