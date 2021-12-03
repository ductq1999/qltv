package com.ptit.qltv.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DocGiaDto {

    private Integer id;

    private String ma;

    private String ten;

    private Instant ngaySinh;

    private String diaChi;

    private String soDienThoai;

    private String maVach;

    private Integer soSachDaMuon;

    private List<PhieuMuonDto> phieuMuon;

    @JsonGetter("ngay_sinh")
    public Object getNgaySinh() {
        try {
            return ngaySinh.getEpochSecond();
        } catch (Exception e) {
            return null;
        }
    }

}
