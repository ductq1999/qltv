package com.ptit.qltv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phieu_muon")
public class PhieuMuon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "thoi_gian_muon", nullable = false)
    private Instant thoiGianMuon;

    @Column(name = "han_tra", nullable = false)
    private Instant hanTra;

    @Column(name = "ngay_tra")
    private Instant ngayTra;

    @Column(name = "trang_thai", nullable = false)
    private boolean trangThai;

    @Column(name = "doc_gia_id", nullable = false)
    private Integer docGiaId;

    @ManyToMany
    @JoinTable(name = "phieu_muon_sach",
            joinColumns = @JoinColumn(name = "phieu_muon_id"),
            inverseJoinColumns = @JoinColumn(name = "sach_id"))
    Set<Sach> sachMuon;
}
