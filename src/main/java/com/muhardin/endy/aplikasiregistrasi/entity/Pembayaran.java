package com.muhardin.endy.aplikasiregistrasi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class Pembayaran {
    @Id @Column(length = 36)
    @GeneratedValue(generator = "uuid" )
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_tagihan")
    private Tagihan tagihan;

    @NotNull
    private LocalDateTime waktuPembayaran;

    @NotEmpty
    private String referensi;
}
