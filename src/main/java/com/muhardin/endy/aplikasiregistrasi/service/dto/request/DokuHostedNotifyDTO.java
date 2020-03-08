package com.muhardin.endy.aplikasiregistrasi.service.dto.request;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DokuHostedNotifyDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DokuHostedNotifyDTO {
    private BigDecimal AMOUNT;
    private String TRANSIDMERCHANT;
    private String WORDS;
    private String STATUSTYPE;
    private String RESPONSECODE;
    private String APPROVALCODE;
    private String RESULTMSG;
    private String PAYMENTCHANNEL;
    private String PAYMENTCODE;
    private String SESSIONID;
    private String BANK;
    private String MCN;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date PAYMENTDATEANDTIME;
    private String VERIFYID;
    private Integer VERIFYSCORE;
    private String VERIFYSTATUS;
    private String CURRENCY;
    private String PURCHASECURRENCY;
    private String BRAND;
    private String CHNAME;
    private String THREEDSECURESTATUS;
    private String LIABILITY;
    private String EDUSTATUS;
    private String CUSTOMERID;
    private String TOKENID;    
}