package com.ocha.boc.dto;

import com.ocha.boc.base.AbstractEntity;
import com.ocha.boc.entity.DanhMuc;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DanhMucDTO extends AbstractEntity {


    private String abbreviations;

    private String name;

    private String danhMucId;

    private String cuaHangId;

    public DanhMucDTO(DanhMuc danhMuc) {
        this.abbreviations = danhMuc.getAbbreviations();
        this.name = danhMuc.getName();
        this.id = danhMuc.getId();
        this.danhMucId = danhMuc.getDanhMucId();
        this.createdDate = danhMuc.getCreatedDate();
        this.lastModifiedDate = danhMuc.getLastModifiedDate();
        this.cuaHangId = danhMuc.getCuaHangId();
    }
}
