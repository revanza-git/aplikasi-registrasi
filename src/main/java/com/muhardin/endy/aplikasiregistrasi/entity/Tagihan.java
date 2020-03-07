package com.muhardin.endy.aplikasiregistrasi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "nomorInvoice")})
public class Tagihan {
    @Id @Column(length = 36)
    @GeneratedValue(generator = "uuid" )
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_pendaftaran")
    private Pendaftaran pendaftaran;

    @NotNull
    @NotEmpty
    private String nomorInvoice;

    @NotNull
    @NotEmpty
    private String bank;

    @NotNull
    @NotEmpty
    private String nomorRekening;

    @NotNull
    @NotEmpty
    private String namaRekening;

    private String keterangan;

    @NotNull
    private LocalDateTime tanggalPembuatan = LocalDateTime.now();

    @NotNull
    private LocalDateTime tanggalKadaluarsa = LocalDateTime.now().plusMonths(1);

    @NotNull
    private Boolean lunas = false;
}
