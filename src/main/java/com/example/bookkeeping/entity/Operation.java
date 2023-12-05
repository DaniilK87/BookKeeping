package com.example.bookkeeping.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "operations")
@Data
public class Operation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private String date;

    @Column(name = "sum")
    private Integer sum;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "balance_id")
    private Balance balance;
}
