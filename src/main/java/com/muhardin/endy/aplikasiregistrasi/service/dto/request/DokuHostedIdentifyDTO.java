package com.muhardin.endy.aplikasiregistrasi.service.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DokuHostedNotifyDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DokuHostedIdentifyDTO {
    private BigDecimal AMOUNT;
    private String TRANSIDMERCHANT;
    private String PAYMENTCHANNEL;
    private String SESSIONID;
}