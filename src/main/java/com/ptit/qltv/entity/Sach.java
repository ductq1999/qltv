package com.ptit.qltv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "sach")
public class Sach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma", nullable = false)
    private String ma;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "tac_gia", nullable = false)
    private String tacGia;


    @Column(name = "nam_xuat_ban", nullable = false)
    private int namXuatBan;


    @Column(name = "gia_bia", nullable = false)
    private float giaBia;

    @ManyToOne
    @JoinColumn(name = "dau_sach_id", nullable = false)
    private DauSach dauSach;

}
