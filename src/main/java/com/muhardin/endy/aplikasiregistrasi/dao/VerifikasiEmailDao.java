package com.muhardin.endy.aplikasiregistrasi.dao;

import com.muhardin.endy.aplikasiregistrasi.entity.VerifikasiEmail;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VerifikasiEmailDao extends PagingAndSortingRepository<VerifikasiEmail, String> {
    VerifikasiEmail findByToken(String token);
}
