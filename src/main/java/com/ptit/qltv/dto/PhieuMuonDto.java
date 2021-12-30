package com.ptit.qltv.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PhieuMuonDto {

    private Integer id;

    private Instant thoiGianMuon;

    private Instant hanTra;

    private Instant ngayTra;

    private boolean trangThai;

    private float tienPhat;

    Set<SachDto> sachMuon;

    public float getTienPhat() {
        Instant now = Instant.now();
        if (now.isBefore(hanTra)) {
            return 0F;
        } else if (now.isAfter(hanTra) && ngayTra == null) {
            return (float) ((now.getEpochSecond()-hanTra.getEpochSecond())/(3.6*24));
        }
        return (float) ((ngayTra.getEpochSecond()-hanTra.getEpochSecond())/(3.6*24));
    }

    @JsonGetter("thoi_gian_muon")
    public Object getThoiGianMuon() {
        try {
            return Date.from(thoiGianMuon);
        } catch (Exception e) {
            return null;
        }
    }

    @JsonGetter("han_tra")
    public Object getHanTra() {
        try {
            return Date.from(hanTra);
        } catch (Exception e) {
            return null;
        }
    }

    @JsonGetter("ngay_tra")
    public Object getNgayTra() {
        try {
            return Date.from(ngayTra);
        } catch (Exception e) {
            return null;
        }
    }
}
