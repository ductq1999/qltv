package com.ptit.qltv.controller;

import com.ptit.qltv.dto.DocGiaDto;
import com.ptit.qltv.service.DocGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doc-gia")
public class DocGiaController {

    @Autowired
    private DocGiaService docGiaService;

    @GetMapping(value = "/thong-ke-doc-gia")
    @ResponseBody
    public ResponseEntity<Page<DocGiaDto>> thongKeDocGia(@RequestParam(value = "thoi_gian_muon_tu") Long thoiGianMuonTu,
                                                         @RequestParam(value = "thoi_gian_muon_den") Long thoiGianMuonDen,
                                                         Pageable pageable) {
        Page<DocGiaDto> docGiaDtoPage = docGiaService.getDocGia(thoiGianMuonTu, thoiGianMuonDen, pageable);
        return ResponseEntity.ok(docGiaDtoPage);
    }
}
