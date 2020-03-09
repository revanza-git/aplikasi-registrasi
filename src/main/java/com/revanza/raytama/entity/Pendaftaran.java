package com.revanza.raytama.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Pendaftaran {
    @Id @Column(length = 36)
    @GeneratedValue(generator = "uuid" )
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_peserta")
    private Peserta peserta;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_materi")
    private Materi materi;

    @NotNull
    private Boolean sudahBayar = false;

}
