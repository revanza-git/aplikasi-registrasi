package com.muhardin.endy.aplikasiregistrasi.service;

import com.muhardin.endy.aplikasiregistrasi.dao.PesertaDao;
import com.muhardin.endy.aplikasiregistrasi.dao.VerifikasiEmailDao;
import com.muhardin.endy.aplikasiregistrasi.entity.Peserta;
import com.muhardin.endy.aplikasiregistrasi.entity.VerifikasiEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service @Transactional
public class RegistrationService {

    @Value("${token.expiry.days}")
    private Integer tokenExpiryDays;

    @Autowired private PesertaDao pesertaDao;
    @Autowired private VerifikasiEmailDao verifikasiEmailDao;

    public void registrasiPesertaBaru(Peserta p) {
        VerifikasiEmail ve = new VerifikasiEmail();
        ve.setPeserta(p);
        ve.setToken(UUID.randomUUID().toString());
        ve.setExpire(LocalDateTime.now().plusDays(tokenExpiryDays));

        pesertaDao.save(p);
        verifikasiEmailDao.save(ve);
    }

    public void verifikasiToken(String token) {
        VerifikasiEmail v = verifikasiEmailDao.findByToken(token);
        if (v != null) {
            Peserta p = v.getPeserta();
            p.setEmailTerverifikasi(true);
            verifikasiEmailDao.delete(v);
        }
    }
}
