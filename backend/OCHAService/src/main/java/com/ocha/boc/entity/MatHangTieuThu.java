package com.ocha.boc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class MatHangTieuThu {

    private String matHangName;

    private String danhMucId;

    private String bangGiaName;

    private int quantity;

    private BigDecimal unitPrice;

//    private String giamGiaName;
//
//    private BigDecimal giamGiaPercentage;
//
//    private BigDecimal giamGiaDiscountAmount;
}
