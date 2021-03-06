package org.voucher.voucherservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.voucher.voucherservice.data.domain.CreateMultiUseVoucherRequest;
import org.voucher.voucherservice.data.domain.CreateSingleUseVoucherRequest;
import org.voucher.voucherservice.data.domain.CreateXTimesUseVoucherRequest;
import org.voucher.voucherservice.data.domain.VoucherInfoResponse;
import org.voucher.voucherservice.data.entity.Voucher;
import org.voucher.voucherservice.data.entity.VoucherJournal;
import org.voucher.voucherservice.data.factory.VoucherFactory;
import org.voucher.voucherservice.exception.VoucherCodeNotUniqueException;
import org.voucher.voucherservice.exception.VoucherInvalidDataException;
import org.voucher.voucherservice.exception.VoucherNotFoundException;
import org.voucher.voucherservice.service.VoucherService;

import java.util.List;

@RestController
@RequestMapping("/api/management")
public class ManagementController {
    private final VoucherService voucherService;

    public ManagementController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping(
            value = "voucher/{code}"
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getVoucherInfo(@PathVariable String code) throws VoucherNotFoundException, VoucherInvalidDataException {
        Voucher voucher = voucherService.getVoucher(code);
        List<VoucherJournal> journal = voucherService.getVoucherJournal(voucher.getId());
        return new ResponseEntity<>(new VoucherInfoResponse(voucher, journal), HttpStatus.OK);
    }

    @PostMapping(
            value = "create/single"
            , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSingleUseVoucher(@RequestBody CreateSingleUseVoucherRequest request) throws VoucherInvalidDataException, VoucherCodeNotUniqueException {
        Voucher voucher =
                voucherService.saveVoucher(
                        VoucherFactory
                                .createSingleUseVoucher(
                                        request.getVoucherCode()
                                        , request.getExpirationDateTime()
                                        , request.getEurValue()));
        return new ResponseEntity<>(voucher, HttpStatus.CREATED);
    }

    @PostMapping(
            value = "create/multi"
            , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMultiUseVoucher(@RequestBody CreateMultiUseVoucherRequest request) throws VoucherInvalidDataException, VoucherCodeNotUniqueException {
        Voucher voucher =
                voucherService.saveVoucher(
                        VoucherFactory
                                .createMultiUseVoucher(
                                        request.getVoucherCode()
                                        , request.getExpirationDateTime()
                                        , request.getEurValue()));
        return new ResponseEntity<>(voucher, HttpStatus.CREATED);
    }

    @PostMapping(
            value = "create/x-times"
            , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createXTimesUseVoucher(@RequestBody CreateXTimesUseVoucherRequest request) throws VoucherInvalidDataException, VoucherCodeNotUniqueException {
        Voucher voucher =
                voucherService.saveVoucher(
                        VoucherFactory
                                .createXTimesUseVoucher(
                                        request.getVoucherCode()
                                        , request.getMaxUse()
                                        , request.getExpirationDateTime()
                                        , request.getEurValue()));
        return new ResponseEntity<>(voucher, HttpStatus.CREATED);
    }

    @DeleteMapping(
            value = "voucher/{code}")
    public ResponseEntity<?> deleteVoucher(@PathVariable String code) throws VoucherNotFoundException, VoucherInvalidDataException {
        Voucher voucher = voucherService.deleteVoucher(code);
        return new ResponseEntity<>(voucher, HttpStatus.OK);
    }
}
