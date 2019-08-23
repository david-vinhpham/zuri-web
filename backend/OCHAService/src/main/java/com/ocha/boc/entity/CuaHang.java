package com.ocha.boc.entity;

import com.ocha.boc.enums.DanhMucMatHangType;
import com.ocha.boc.enums.MoHinhKinhDoanhType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = CuaHang.COLLECTION_NAME)
public class CuaHang {

    public static final String COLLECTION_NAME = "cuahang";

    private String id;

    private String moHinhKinhDoanhType;

    private String danhMucMatHangType;

    private String cuaHangName;

    private String phone;

    private String address;

    private String managerName;

    private String managerPhone;

    private String managerEmail;

    private String createdDate;

    private String lastModifiedDate;

    private String userId;



}
