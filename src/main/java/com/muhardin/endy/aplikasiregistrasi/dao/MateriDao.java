package com.muhardin.endy.aplikasiregistrasi.dao;

import com.muhardin.endy.aplikasiregistrasi.entity.Materi;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MateriDao extends PagingAndSortingRepository<Materi, String> {
}
