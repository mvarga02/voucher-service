package org.voucher.voucherservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.voucher.voucherservice.data.domain.CreateMultiUseVoucherRequest;
import org.voucher.voucherservice.data.domain.CreateSingleUseVoucherRequest;
import org.voucher.voucherservice.data.domain.CreateXTimesUseVoucherRequest;

@RestController
@RequestMapping("/api/management")
public class ManagementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagementController.class);

    @RequestMapping(
            value = "create/single"
            ,method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSingleUseVoucher(@RequestBody CreateSingleUseVoucherRequest request){

    }

    @RequestMapping(
            value = "create/multi"
            ,method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMultiUseVoucher(@RequestBody CreateMultiUseVoucherRequest request){

    }

    @RequestMapping(
            value = "create/x-times"
            ,method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createXTimesUseVoucher(@RequestBody CreateXTimesUseVoucherRequest request){

    }
}
