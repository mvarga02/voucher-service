package org.voucher.voucherservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.voucher.voucherservice.data.domain.CreateSingleUseVoucherRequest;
import org.voucher.voucherservice.data.entity.Voucher;
import org.voucher.voucherservice.data.entity.VoucherJournal;
import org.voucher.voucherservice.data.enums.ErrorCode;
import org.voucher.voucherservice.data.factory.VoucherFactory;
import org.voucher.voucherservice.data.repository.VoucherJournalRepository;
import org.voucher.voucherservice.data.repository.VoucherRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class ManagementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private VoucherJournalRepository voucherJournalRepository;


    @AfterEach
    public void dbClean() {
        voucherRepository.deleteAll();
        voucherJournalRepository.deleteAll();
    }

    @Test
    public void testSingleVoucherSavedInDb() throws Exception {
        String testVoucherCode = "TEST";
        CreateSingleUseVoucherRequest request =
                new CreateSingleUseVoucherRequest(testVoucherCode, LocalDateTime.now().plusDays(1), 100.0);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/management/create/single")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        Voucher voucher = voucherRepository.findVoucherByCode(testVoucherCode).get();
        Assertions.assertEquals(request.getEurValue(), voucher.getEurValue());

    }

    @Test
    public void testJournalSavedDuringVoucherCreate() throws Exception {
        String testVoucherCode = "TEST";
        CreateSingleUseVoucherRequest request =
                new CreateSingleUseVoucherRequest(testVoucherCode, LocalDateTime.now().plusDays(1), 100.0);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/management/create/single")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
        Voucher voucher = voucherRepository.findVoucherByCode(testVoucherCode).get();
        List<VoucherJournal> journalList = voucherJournalRepository.findAllByVoucherIdOrderByTimeStamp(voucher.getId()).orElseGet(Collections::emptyList);
        Assertions.assertEquals(1, journalList.size());
    }

    @Test
    public void testCreateThrowsExceptionWithNotUniqueVoucherCode() throws Exception {
        String testVoucherCode = "TEST";
        CreateSingleUseVoucherRequest request =
                new CreateSingleUseVoucherRequest(testVoucherCode, LocalDateTime.now().plusDays(1), 100.0);
        voucherRepository.save(VoucherFactory.createSingleUseVoucher(testVoucherCode, LocalDateTime.now().plusDays(1), 100.0));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/management/create/single")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.VSEC_003.toString()))
                .andExpect(jsonPath("$.errorCode").isNotEmpty());


    }

    @Test
    public void testCreateSingleVoucherWithEmptyCodeThrowsInvalidDataException() throws Exception {
        String testVoucherCode = "";
        CreateSingleUseVoucherRequest request =
                new CreateSingleUseVoucherRequest(testVoucherCode, LocalDateTime.now().plusDays(1), 100.0);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/management/create/single")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.VSEC_001.toString()))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    public void testCreateSingleVoucherWithInvalidExpirationDateThrowsInvalidDataException() throws Exception {
        LocalDateTime expDate = LocalDateTime.now().minusDays(1);
        CreateSingleUseVoucherRequest request =
                new CreateSingleUseVoucherRequest("TEST", expDate, 100.0);
        MvcResult mvcResult =
                mockMvc
                        .perform(MockMvcRequestBuilders
                                .post("/api/management/create/single")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(mapper.writeValueAsString(request)))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.errorCode").value(ErrorCode.VSEC_001.toString()))
                        .andExpect(jsonPath("$.message").isNotEmpty())
                        .andReturn();
    }
}
