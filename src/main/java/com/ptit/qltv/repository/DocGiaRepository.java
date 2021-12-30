package com.ptit.qltv.repository;

import com.ptit.qltv.entity.DocGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface DocGiaRepository extends JpaRepository<DocGia, Integer> {

    @Query(value = "SELECT * FROM doc_gia d " +
            "INNER JOIN phieu_muon p ON p.doc_gia_id = d.id " +
            "WHERE p.thoi_gian_muon > :thoi_gian_muon_from " +
            "AND p.thoi_gian_muon < :thoi_gian_muon_to " +
            "GROUP BY d.id, d.ma, d.ten, d.ngay_sinh, d.dia_chi, d.so_dien_thoai, d.ma_vach, d.so_sach_da_muon " +
            "ORDER BY d.so_sach_da_muon DESC", nativeQuery = true)
    Page<DocGia> getListDocGia(@Param("thoi_gian_muon_from") Instant thoiGianMuonFrom,
                               @Param("thoi_gian_muon_to") Instant thoiGianMuonTo, Pageable pageable);

}
