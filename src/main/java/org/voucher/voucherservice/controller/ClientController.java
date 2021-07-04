package org.voucher.voucherservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.voucher.voucherservice.data.domain.UseVoucherRequest;
import org.voucher.voucherservice.exception.VoucherAlreadyRedeemedException;
import org.voucher.voucherservice.exception.VoucherExpiredException;
import org.voucher.voucherservice.exception.VoucherInvalidDataException;
import org.voucher.voucherservice.exception.VoucherNotFoundException;
import org.voucher.voucherservice.service.VoucherService;

@RestController("api/")
public class ClientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private final VoucherService voucherService;

    public ClientController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @PostMapping(
            value = "redemption"
            , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> useVoucher(@RequestBody UseVoucherRequest request) {
        try {
            return new ResponseEntity<>(voucherService.useVoucher(request.getVoucherCode()), HttpStatus.OK);
        } catch (VoucherNotFoundException | VoucherAlreadyRedeemedException | VoucherInvalidDataException | VoucherExpiredException e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }
    }

}
