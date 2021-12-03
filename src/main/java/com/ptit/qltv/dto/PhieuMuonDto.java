package com.ptit.qltv.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ptit.qltv.entity.DocGia;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PhieuMuonDto {

    private Integer id;

    private Instant thoiGianMuon;

    private Instant hanTra;

    private boolean trangThai;

    Set<SachDto> sachMuon;

    @JsonGetter("thoi_gian_muon")
    public Object getThoiGianMuon() {
        try {
            return thoiGianMuon.getEpochSecond();
        } catch (Exception e) {
            return null;
        }
    }

    @JsonGetter("han_tra")
    public Object getHanTra() {
        try {
            return hanTra.getEpochSecond();
        } catch (Exception e) {
            return null;
        }
    }
}
