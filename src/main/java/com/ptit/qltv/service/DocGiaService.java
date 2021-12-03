package com.ptit.qltv.service;

import com.ptit.qltv.dto.DocGiaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocGiaService {

    Page<DocGiaDto> getDocGia(Long thoiGianMuonFrom, Long thoiGianMuonTo, Pageable pageable);

}
