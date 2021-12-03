package com.ptit.qltv.service.impl;

import com.ptit.qltv.dto.DocGiaDto;
import com.ptit.qltv.dto.PhieuMuonDto;
import com.ptit.qltv.dto.mapper.Mapper;
import com.ptit.qltv.entity.DocGia;
import com.ptit.qltv.entity.PhieuMuon;
import com.ptit.qltv.repository.DocGiaRepository;
import com.ptit.qltv.repository.PhieuMuonRepository;
import com.ptit.qltv.service.DocGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class DocGiaServiceImpl implements DocGiaService {


    @Autowired
    private DocGiaRepository docGiaRepository;

    @Autowired
    private PhieuMuonRepository phieuMuonRepository;

    @Override
    public Page<DocGiaDto> getDocGia(Long thoiGianMuonFrom, Long thoiGianMuonTo, Pageable pageable) {
        Page<DocGia> docGiaPage = docGiaRepository.getListDocGia(Instant.ofEpochSecond(thoiGianMuonFrom), Instant.ofEpochSecond(thoiGianMuonTo), pageable);
        Page<DocGiaDto> docGiaDtoPage = Mapper.toPage(docGiaPage, DocGiaDto.class, pageable);
        for (DocGiaDto docGiaDto : docGiaDtoPage) {
            List<PhieuMuon> phieuMuonList = phieuMuonRepository
                    .findAllByThoiGianMuonAfterAndThoiGianMuonBeforeAndAndDocGiaId(Instant.ofEpochSecond(thoiGianMuonFrom), Instant.ofEpochSecond(thoiGianMuonTo), docGiaDto.getId());
            docGiaDto.setPhieuMuon(Mapper.toList(phieuMuonList, PhieuMuonDto.class));
        }
        return docGiaDtoPage;
    }
}
