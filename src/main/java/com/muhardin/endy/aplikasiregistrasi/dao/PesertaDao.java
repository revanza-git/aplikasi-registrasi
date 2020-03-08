package com.muhardin.endy.aplikasiregistrasi.dao;

import com.muhardin.endy.aplikasiregistrasi.entity.Peserta;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PesertaDao extends PagingAndSortingRepository<Peserta, String> {
    Peserta findByEmail(String username);
}
