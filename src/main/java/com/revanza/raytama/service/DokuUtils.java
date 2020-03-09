package com.revanza.raytama.service;

import java.text.DecimalFormat;

import com.revanza.raytama.service.dto.request.DokuHostedNotifyDTO;
import com.revanza.raytama.service.dto.request.DokuHostedRedirectDTO;
import com.revanza.raytama.service.dto.request.DokuHostedRequestDTO;

import org.apache.commons.codec.digest.DigestUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * DokuUtils
 */
@Slf4j
public class DokuUtils {

    private DokuUtils(){

    }
    
    private static DecimalFormat dokuDecimalFormat = new DecimalFormat("0.00");

    public static String createWords(DokuHostedRequestDTO request, String sharedKey) {
        return createWords(request.getAMOUNT(), request.getMALLID(), request.getTRANSIDMERCHANT(), request.getCURRENCY(), sharedKey);
    }

    public static String createWords(String amount, String mallId, String transIdMerchant, String currency, String sharedKey) {
        String wordsComponent = "";
        wordsComponent = wordsComponent.concat(amount).concat(mallId).concat(sharedKey).concat(transIdMerchant);
        if(!currency.equals("360")){
            wordsComponent = wordsComponent.concat(currency);
        }
        log.info(wordsComponent);
        log.info(DigestUtils.sha1Hex(wordsComponent));
        return DigestUtils.sha1Hex(wordsComponent);
    }

    public static String createWordsCheckStatus(String mallId, String transIdMerchant, String currency, String sharedKey) {
        String wordsComponent = "";
        wordsComponent = wordsComponent.concat(mallId).concat(sharedKey).concat(transIdMerchant);
        if(!currency.equals("360")){
            wordsComponent = wordsComponent.concat(currency);
        }
        log.info(wordsComponent);
        log.info(DigestUtils.sha1Hex(wordsComponent));
        return DigestUtils.sha1Hex(wordsComponent);
    }

    public static Boolean verifyNotifyWords(DokuHostedNotifyDTO request, String mallId, String sharedKey){
        
        String wordsComponent = "".concat(new DecimalFormat("0.00").format(request.getAMOUNT())).concat(mallId).concat(sharedKey).concat(request.getTRANSIDMERCHANT()).concat(request.getRESULTMSG()).concat(request.getVERIFYSTATUS());
        if(request.getCURRENCY()!=null && !request.getCURRENCY().equals("360")){
            wordsComponent = wordsComponent.concat(request.getCURRENCY());
        }

        log.info(wordsComponent);
        log.info(DigestUtils.sha1Hex(wordsComponent));
        return DigestUtils.sha1Hex(wordsComponent).equals(request.getWORDS());

    }

    public static Boolean verifyRedirectWords(DokuHostedRedirectDTO request, String mallId, String sharedKey){
        String wordsComponent = "".concat(dokuDecimalFormat.format(request.getAMOUNT())).concat(mallId).concat(sharedKey).concat(request.getTRANSIDMERCHANT()).concat(request.getRESULTMSG()!=null?request.getRESULTMSG():"").concat("NA");
        if(request.getCURRENCY()!=null && !request.getCURRENCY().equals("360")){
            wordsComponent = wordsComponent.concat(request.getCURRENCY());
        }

        log.info(wordsComponent);
        log.info(DigestUtils.sha1Hex(wordsComponent));
        return DigestUtils.sha1Hex(wordsComponent).equals(request.getWORDS());

    }
}