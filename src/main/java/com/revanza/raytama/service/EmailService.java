package com.revanza.raytama.service;

public interface EmailService {
    void kirimEmail(String from, String to, String subject, String content);
}
