package com.revanza.raytama.service.dto.request;

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
public class DokuHostedRedirectDTO {    
    private BigDecimal AMOUNT;
    private String TRANSIDMERCHANT;
    private String WORDS;
    private String STATUSCODE;
    private String RESPONSECODE;
    private String PAYMENTCHANNEL;
    private String SESSIONID;
    private String PAYMENTCODE;
    private String CURRENCY;
    private String PURCHASECURRENCY;
    private String RESULTMSG;
}