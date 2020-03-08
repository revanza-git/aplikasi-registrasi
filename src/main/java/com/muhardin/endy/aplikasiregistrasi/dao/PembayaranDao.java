package com.muhardin.endy.aplikasiregistrasi.dao;

import com.muhardin.endy.aplikasiregistrasi.entity.Pembayaran;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PembayaranDao
 */
public interface PembayaranDao extends PagingAndSortingRepository<Pembayaran, String> {

    
}