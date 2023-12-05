package com.example.bookkeeping.entity;

import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "grants")
@Data
public class Grants {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private String date;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "sum")
    private Integer sum;

    @Column(name = "transaction_type")
    private String transactionType;
}
