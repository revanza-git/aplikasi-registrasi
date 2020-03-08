package com.muhardin.endy.aplikasiregistrasi.dao;

import com.muhardin.endy.aplikasiregistrasi.entity.Pendaftaran;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PendaftaranDao extends PagingAndSortingRepository<Pendaftaran, String> {
}
