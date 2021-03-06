package org.voucher.voucherservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.voucher.voucherservice.data.domain.UseVoucherRequest;
import org.voucher.voucherservice.exception.VoucherAlreadyRedeemedException;
import org.voucher.voucherservice.exception.VoucherExpiredException;
import org.voucher.voucherservice.exception.VoucherInvalidDataException;
import org.voucher.voucherservice.exception.VoucherNotFoundException;
import org.voucher.voucherservice.service.VoucherService;

@RestController
@RequestMapping("api/client")
public class ClientController {
    private final VoucherService voucherService;

    public ClientController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @PutMapping(
            value = "redemption"
            , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> useVoucher(@RequestBody UseVoucherRequest request) throws VoucherNotFoundException, VoucherAlreadyRedeemedException, VoucherInvalidDataException, VoucherExpiredException {
        return new ResponseEntity<>(voucherService.useVoucher(request.getVoucherCode()), HttpStatus.OK);
    }

}
