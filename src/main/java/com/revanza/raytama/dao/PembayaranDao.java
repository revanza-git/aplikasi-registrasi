package com.revanza.raytama.dao;

import com.revanza.raytama.entity.Pembayaran;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PembayaranDao
 */
public interface PembayaranDao extends PagingAndSortingRepository<Pembayaran, String> {

    
}