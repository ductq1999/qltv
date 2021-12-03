package com.ptit.qltv.repository;

import com.ptit.qltv.entity.PhieuMuon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PhieuMuonRepository extends JpaRepository<PhieuMuon, Integer> {


    List<PhieuMuon> findAllByThoiGianMuonAfterAndThoiGianMuonBeforeAndAndDocGiaId(Instant thoiGianMuonFrom, Instant thoiGianMuonTo, Integer docGiaId);
}
