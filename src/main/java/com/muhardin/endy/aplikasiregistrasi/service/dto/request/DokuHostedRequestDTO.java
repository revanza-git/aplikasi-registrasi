package com.muhardin.endy.aplikasiregistrasi.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DokuHostedRequest
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DokuHostedRequestDTO {
    private String MALLID;
    private String CHAINMERCHANT;
    private String AMOUNT;
    private String PURCHASEAMOUNT;
    private String TRANSIDMERCHANT;
    private String WORDS;
    private String REQUESTDATETIME;
    private String CURRENCY;
    private String PURCHASECURRENCY;
    private String SESSIONID;
    private String NAME;
    private String EMAIL;
    private String BASKET;
    private String PAYMENTCHANNEL; 
}