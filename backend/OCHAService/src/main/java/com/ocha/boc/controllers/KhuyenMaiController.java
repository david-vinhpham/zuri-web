package com.ocha.boc.controllers;

import com.ocha.boc.request.KhuyenMaiRequest;
import com.ocha.boc.request.KhuyenMaiUpdateRequest;
import com.ocha.boc.response.KhuyenMaiResponse;
import com.ocha.boc.services.impl.KhuyenMaiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KhuyenMaiController {

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @ApiOperation(value = "Create new Khuyen Mai")
    @PostMapping("/khuyen-mai")
    public ResponseEntity<KhuyenMaiResponse> createNewKhuyenMai(@RequestBody KhuyenMaiRequest request) {
        KhuyenMaiResponse response = khuyenMaiService.createNewKhuyenMai(request);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Get all Khuyen Mai")
    @GetMapping("/khuyen-mai")
    public ResponseEntity<KhuyenMaiResponse> getAllKhuyenMai() {
        KhuyenMaiResponse response = khuyenMaiService.getAllKhuyenMai();
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Get Khuyen Mai By KhuyenMaiId")
    @GetMapping("/khuyen-mai/{id}")
    public ResponseEntity<KhuyenMaiResponse> getKhuyenMaiByKhuyenMaiId(@PathVariable String id) {
        KhuyenMaiResponse response = khuyenMaiService.getKhuyenMaiByKhuyenMaiId(id);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Delete Khuyen Mai By KhuyenMaiId")
    @DeleteMapping("/khuyen-mai/{id}")
    public ResponseEntity<KhuyenMaiResponse> deleteKhuyenMaiByKhuyenMaiId(@PathVariable String id) {
        KhuyenMaiResponse response = khuyenMaiService.deleteKhuyenMaiByKhuyenMaiId(id);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Update Khuyen Mai")
    @PutMapping("/khuyen-mai")
    public ResponseEntity<KhuyenMaiResponse> updateKhuyenMai(@RequestBody KhuyenMaiUpdateRequest request){
        KhuyenMaiResponse response = khuyenMaiService.updateKhuyenMai(request);
        return ResponseEntity.ok(response);
    }

}
