package com.ocha.boc.controllers;

import com.ocha.boc.request.AbstractBaoCaoRequest;
import com.ocha.boc.response.DoanhThuTheoDanhMucResponse;
import com.ocha.boc.response.DoanhThuTongQuanResponse;
import com.ocha.boc.response.MatHangBanChayResponse;
import com.ocha.boc.services.impl.BaoCaoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BaoCaoController {

    @Autowired
    private BaoCaoService baoCaoService;

    @ApiOperation("Doanh Thu Tổng Quan")
    @GetMapping("/doanh-thu-tong-quan/{cuaHangId}")
    public ResponseEntity<DoanhThuTongQuanResponse> getDoanhThuTongQuan(@PathVariable(value = "cuaHangId") String cuaHangId){
        log.info("START: get current doanh thu tong quan");
        DoanhThuTongQuanResponse response = baoCaoService.getDoanhThuTongQuan(cuaHangId);
        log.info("END: get current doanh thu tong quan");
        return ResponseEntity.ok(response);
    }

    @ApiOperation("Get Doanh Thu Tong Quan In Range Date")
    @PostMapping("/doanh-thu-tong-quan")
    public ResponseEntity<DoanhThuTongQuanResponse> getDoanhThuTongQuanInRangeDate(@RequestBody AbstractBaoCaoRequest request){
        log.info("START: get doanh thu tong quan from: " + request.getFromDate() + " to: " + request.getToDate());
        DoanhThuTongQuanResponse response = baoCaoService.getDoanhThuTongQuanInRangeDate(request);
        log.info("END: get doanh thu tong quan in range date");
        return ResponseEntity.ok(response);
    }

    @ApiOperation("Doanh Thu Theo Danh Mục")
    @GetMapping("/doanh-thu-theo-danh-muc/{cuaHangId}")
    public ResponseEntity<DoanhThuTheoDanhMucResponse> getDoanhThuTheoDanhMuc(@PathVariable(value = "cuaHangId") String cuaHangId){
        log.info("START: get current doanh thu theo danh muc");
        DoanhThuTheoDanhMucResponse response = baoCaoService.getDoanhThuTheoDanhMuc(cuaHangId);
        log.info("END: get current doanh thu theo danh muc");
        return ResponseEntity.ok(response);
    }

    @ApiOperation("Doanh Thu Theo Danh Mục In Range Date ")
    @PostMapping("/doanh-thu-theo-danh-muc")
    public ResponseEntity<DoanhThuTheoDanhMucResponse> getDoanhThuTheoDanhMucInRangeDate(@RequestBody AbstractBaoCaoRequest request){
        log.info("START: get doanh thu theo danh muc from: " + request.getFromDate() + " to: " + request.getToDate());
        DoanhThuTheoDanhMucResponse response = baoCaoService.getDoanhThuTheoDanhMucInRangeDate(request);
        log.info("END: get doanh thu theo danh muc in range date");
        return ResponseEntity.ok(response);
    }

    @ApiOperation("Mặt Hàng Bán Chạy")
    @GetMapping("/mat-hang-ban-chay/{cuaHangId}")
    public ResponseEntity<MatHangBanChayResponse> getMatHangBanChay(@PathVariable(value = "cuaHangId") String cuaHangId){
        log.info("START: get current mat hang ban chay");
        MatHangBanChayResponse response = baoCaoService.getMatHangBanChay(cuaHangId);
        log.info("END: get current mat hang ban chay");
        return ResponseEntity.ok(response);
    }

    @ApiOperation("Mặt Hàng Bán Chạy In Range Date")
    @PostMapping("/mat-hang-ban-chay")
    public ResponseEntity<MatHangBanChayResponse> getMatHangBanChayInRangeDate(@RequestBody AbstractBaoCaoRequest request){
        log.info("START: get mat hang ban chay from: " + request.getFromDate() + " to: " + request.getToDate());
        MatHangBanChayResponse response = baoCaoService.getMatHangBanChayInRangeDate(request);
        log.info("END: get mat hang ban chay in range date");
        return ResponseEntity.ok(response);
    }

}
