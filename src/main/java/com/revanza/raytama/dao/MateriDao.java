package com.revanza.raytama.dao;

import com.revanza.raytama.entity.Materi;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MateriDao extends PagingAndSortingRepository<Materi, String> {
}
