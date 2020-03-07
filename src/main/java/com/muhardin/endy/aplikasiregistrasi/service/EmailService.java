package com.muhardin.endy.aplikasiregistrasi.service;

public interface EmailService {
    void kirimEmail(String from, String to, String subject, String content);
}
