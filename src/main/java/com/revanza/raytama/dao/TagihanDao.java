package com.revanza.raytama.dao;

import com.revanza.raytama.entity.Tagihan;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagihanDao extends PagingAndSortingRepository<Tagihan, String> {

    Tagihan findByNomorInvoice(String nomorInvoice);
}
