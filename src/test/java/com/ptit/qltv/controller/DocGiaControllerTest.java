package com.ptit.qltv.controller;

import com.ptit.qltv.entity.DauSach;
import com.ptit.qltv.entity.DocGia;
import com.ptit.qltv.entity.PhieuMuon;
import com.ptit.qltv.entity.Sach;
import com.ptit.qltv.repository.DocGiaRepository;
import com.ptit.qltv.repository.PhieuMuonRepository;
import com.ptit.qltv.service.DocGiaService;
import com.ptit.qltv.service.impl.DocGiaServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

;

@RunWith(SpringRunner.class)
@WebMvcTest(DocGiaController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DocGiaControllerTest {


    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @TestConfiguration
    public static class ServiceTestConfiguration {
        @Bean
        DocGiaService docGiaService() {
            return new DocGiaServiceImpl();
        }
    }

    @Autowired
    private MockMvc mockMvc;

    //repository
    @MockBean
    private DocGiaRepository docGiaRepository;

    @MockBean
    private PhieuMuonRepository phieuMuonRepository;

    @Test
    public void testThongKeDocGiaWhenStartDateBeforeEndDate() throws Exception {
        Pageable pageable = PageRequest.of(0, 1);
        List<DocGia> docGias = new ArrayList<>();
        List<PhieuMuon> phieuMuonList = new ArrayList<>();
        Set<Sach> sachSet = new HashSet<>();
        DauSach dauSach = new DauSach(1, "A", null, 10, 10000F, null);
        Sach sach = new Sach(1, "SACH001", "TOAN", "DUCTQ", 2015, 10000F, dauSach);
        sachSet.add(sach);
        DocGia docGia = new DocGia(1, "001", "duc", null, "Ha Noi", "0329347115", "123456789", 1);
        PhieuMuon phieuMuon = new PhieuMuon(1, Instant.ofEpochSecond(100L), Instant.ofEpochSecond(100000L), null, true, 1, sachSet);
        docGias.add(docGia);
        phieuMuonList.add(phieuMuon);
        Page<DocGia> docGiaPage = new PageImpl<>(docGias);
        given(docGiaRepository.getListDocGia(Instant.ofEpochSecond(1L), Instant.ofEpochSecond(10000000000L), pageable)).willReturn(docGiaPage);
        for (DocGia docGia1: docGiaPage) {
            given(phieuMuonRepository.findAllByThoiGianMuonAfterAndThoiGianMuonBeforeAndAndDocGiaId(Instant.ofEpochSecond(1L), Instant.ofEpochSecond(10000000000L), docGia1.getId())).willReturn(phieuMuonList);
        }
        mockMvc.perform(get("/doc-gia/thong-ke-doc-gia?thoi_gian_muon_tu=1&thoi_gian_muon_den=10000000000&page=0&size=1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].ma").value("001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].ten").value("duc"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].dia_chi").value("Ha Noi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].so_dien_thoai").value("0329347115"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].ma_vach").value("123456789"));
    }

    @Test
    public void testThongKeDocGiaWhenStartDateAfterEndDate() throws Exception {
        Pageable pageable = PageRequest.of(0, 1);
        List<DocGia> docGias = new ArrayList<>();
        List<PhieuMuon> phieuMuonList = new ArrayList<>();
        Set<Sach> sachSet = new HashSet<>();
        Sach sach = new Sach(1, "SACH001", "TOAN", "DUCTQ", 2015, 10000F, null);
        sachSet.add(sach);
        DocGia docGia = new DocGia(1, "001", "duc", null, "Ha Noi", "0329347115", "123456789", 1);

        PhieuMuon phieuMuon = new PhieuMuon(1, Instant.ofEpochSecond(1000L),  Instant.ofEpochSecond(100000L), null, true, 1, sachSet);
        docGias.add(docGia);
        phieuMuonList.add(phieuMuon);
        Page<DocGia> docGiaPage = new PageImpl<>(docGias);
        given(docGiaRepository.getListDocGia(Instant.ofEpochSecond(10000000000L), Instant.ofEpochSecond(1L), pageable)).willReturn(docGiaPage);
        for (DocGia docGia1: docGiaPage) {
            given(phieuMuonRepository.findAllByThoiGianMuonAfterAndThoiGianMuonBeforeAndAndDocGiaId(Instant.ofEpochSecond(10000000000L), Instant.ofEpochSecond(1L), docGia1.getId())).willReturn(phieuMuonList);
        }
        mockMvc.perform(get("/doc-gia/thong-ke-doc-gia?thoi_gian_muon_tu=10000000000&thoi_gian_muon_den=1&page=0&size=1"))
                .andExpect(status().isOk());
    }

}
