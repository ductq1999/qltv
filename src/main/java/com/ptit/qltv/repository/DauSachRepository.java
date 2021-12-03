package com.ptit.qltv.repository;

import com.ptit.qltv.entity.DauSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DauSachRepository extends JpaRepository<DauSach, Integer> {


}
