package com.revanza.raytama.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "kode")})
public class Materi {

    @Id @Column(length = 36)
    @GeneratedValue(generator = "uuid" )
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty @Size(min = 3, max = 100)
    private String kode;

    @NotNull
    @NotEmpty @Size(min = 3, max = 100)
    private String nama;

    @NotNull @Min(10000)
    private BigDecimal biaya;
}
