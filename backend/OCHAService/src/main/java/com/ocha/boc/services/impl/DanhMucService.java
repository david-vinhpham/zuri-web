package com.ocha.boc.services.impl;

import com.ocha.boc.base.AbstractResponse;
import com.ocha.boc.dto.DanhMucDTO;
import com.ocha.boc.entity.DanhMuc;
import com.ocha.boc.repository.DanhMucRepository;
import com.ocha.boc.request.DanhMucRequest;
import com.ocha.boc.request.DanhMucUpdateRequest;
import com.ocha.boc.response.DanhMucResponse;
import com.ocha.boc.util.CommonConstants;
import com.ocha.boc.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class DanhMucService {

    private static final String NUMBER_ONE = "1";

    @Autowired
    private DanhMucRepository danhMucRepository;


    public DanhMucResponse createNewDanhMuc(DanhMucRequest request) {
        DanhMucResponse response = new DanhMucResponse();
        try {
            response.setMessage(CommonConstants.CREATE_NEW_DANH_MUC_FAIL);
            response.setSuccess(Boolean.FALSE);
            if (!Objects.isNull(request)) {
                if (!danhMucRepository.existsByName(request.getName())) {
                    DanhMuc danhMuc = new DanhMuc();
                    //Find max DanhMucId Value
                    Optional<DanhMuc> temp = danhMucRepository.findTopByOrderByCreatedDateDesc();
                    if (temp.isPresent()) {
                        int danhMucIdMaxValue = Integer.parseInt(temp.get().getDanhMucId());
                        danhMuc.setDanhMucId(Integer.toString((danhMucIdMaxValue + 1)));
                    } else {
                        //init first record in DB
                        danhMuc.setDanhMucId(NUMBER_ONE);
                    }
                    danhMuc.setCuaHangId(request.getCuaHangId());
                    danhMuc.setAbbreviations(request.getAbbreviations());
                    danhMuc.setName(request.getName());
                    danhMuc.setCreatedDate(DateUtils.getCurrentDateAndTime());
                    danhMucRepository.save(danhMuc);
                    response.setSuccess(Boolean.TRUE);
                    response.setMessage(CommonConstants.STR_SUCCESS_STATUS);
                    response.setObject(new DanhMucDTO(danhMuc));
                } else {
                    response.setMessage(CommonConstants.DANH_MUC_IS_EXISTED);
                }
            }
        } catch (Exception e) {
            log.error("Error when createNewDanhMuc: {}", e);
        }
        return response;
    }

    @CachePut(value = "danhmuc", key = "{#request.cuaHangId, #request.danhMucId}")
    public DanhMuc updateDanhMuc(DanhMucUpdateRequest request) {
        return danhMucRepository.findDanhMucByDanhMucIdAndCuaHangId(request.getDanhMucId(),
                            request.getCuaHangId()).map(danhMuc -> {
            if (StringUtils.isNotEmpty(request.getCuaHangId())) {
                danhMuc.setCuaHangId(request.getCuaHangId());
            }
            if (StringUtils.isNotEmpty(request.getAbbreviations())) {
                danhMuc.setAbbreviations(request.getAbbreviations());
            }
            if (StringUtils.isNotEmpty(request.getName())) {
                danhMuc.setName(request.getName());
            }
            danhMuc.setLastModifiedDate(DateUtils.getCurrentDateAndTime());
            return danhMucRepository.save(danhMuc);
        }).orElse(new DanhMuc());
    }

    @Cacheable(value = "danhmuc", key = "{#cuaHangId,#id}")
    public DanhMuc findDanhMucByDanhMucId(String id, String cuaHangId) {
        return danhMucRepository.findDanhMucByDanhMucIdAndCuaHangId(id, cuaHangId).orElse(new DanhMuc());
    }

    public DanhMucResponse getAllDanhMuc(String cuaHangId) {
        DanhMucResponse response = new DanhMucResponse();
        try {
            response.setMessage(CommonConstants.GET_ALL_DANH_MUC_FAIL);
            response.setSuccess(Boolean.FALSE);
            List<DanhMuc> listDanhMuc = danhMucRepository.findAllByCuaHangId(cuaHangId);
            if (CollectionUtils.isNotEmpty(listDanhMuc)) {
                List<DanhMucDTO> danhMucDTOList = new ArrayList<>();
                for (DanhMuc danhMuc : listDanhMuc) {
                    DanhMucDTO temp = new DanhMucDTO(danhMuc);
                    danhMucDTOList.add(temp);
                }
                response.setSuccess(Boolean.TRUE);
                response.setMessage(CommonConstants.STR_SUCCESS_STATUS);
                response.setObjects(danhMucDTOList);
                response.setTotalResultCount((long) danhMucDTOList.size());
            }
        } catch (Exception e) {
            log.error("Error when getAllDanhMuc: {}", e);
        }
        return response;
    }

    @CacheEvict(value = "danhmuc", key = "{#cuaHangId,#id}")
    public AbstractResponse deleteDanhMucByDanhMucId(String id, String cuaHangId) {
        AbstractResponse response = new AbstractResponse();
        try {
            response.setMessage(CommonConstants.DELETE_DANH_MUC_BY_DANH_MUC_ID_FAIL);
            response.setSuccess(Boolean.FALSE);
            if (StringUtils.isNotEmpty(id)) {
                if (danhMucRepository.existsByDanhMucIdAndCuaHangId(id, cuaHangId)) {
                    Optional<DanhMuc> optDanhMuc = danhMucRepository.findDanhMucByDanhMucIdAndCuaHangId(id, cuaHangId);
                    danhMucRepository.delete(optDanhMuc.get());
                    response.setSuccess(Boolean.TRUE);
                    response.setMessage(CommonConstants.STR_SUCCESS_STATUS);
                } else {
                    response.setMessage(CommonConstants.DANH_MUC_NAME_IS_NULL);
                }
            }
        } catch (Exception e) {
            log.error("Error when deleteDanhMucById: {}", e);
        }
        return response;
    }

}
