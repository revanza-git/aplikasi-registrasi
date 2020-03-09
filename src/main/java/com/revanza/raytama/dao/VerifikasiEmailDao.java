package com.revanza.raytama.dao;

import com.revanza.raytama.entity.VerifikasiEmail;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VerifikasiEmailDao extends PagingAndSortingRepository<VerifikasiEmail, String> {
    VerifikasiEmail findByToken(String token);
}
