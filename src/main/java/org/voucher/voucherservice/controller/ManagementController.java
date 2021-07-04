package org.voucher.voucherservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.voucher.voucherservice.data.domain.CreateMultiUseVoucherRequest;
import org.voucher.voucherservice.data.domain.CreateSingleUseVoucherRequest;
import org.voucher.voucherservice.data.domain.CreateXTimesUseVoucherRequest;
import org.voucher.voucherservice.service.VoucherService;

@RestController
@RequestMapping("/api/management")
public class ManagementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagementController.class);

    private final VoucherService voucherService;

    public ManagementController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }
    @GetMapping(
            value = "voucher/{code}")
    public ResponseEntity<?> getVoucherInfo(@PathVariable String voucherCode){

    }

    @PostMapping(
            value = "create/single"
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSingleUseVoucher(@RequestBody CreateSingleUseVoucherRequest request){

    }

    @PostMapping(
            value = "create/multi"
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMultiUseVoucher(@RequestBody CreateMultiUseVoucherRequest request){

    }

    @PostMapping(
            value = "create/x-times"
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createXTimesUseVoucher(@RequestBody CreateXTimesUseVoucherRequest request){

    }

    @DeleteMapping(
            value = "voucher/{code}")
    public ResponseEntity<?> deleteVoucher(@PathVariable String voucherCode){

    }
}
