package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.UUIDUtil;
import com.example.guanWang.mapper.HospitalMapper;
import com.example.guanWang.model.Hospital;
import com.example.guanWang.model.HospitalCity;
import com.example.guanWang.service.HospitalService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalMapper hospitalMapper;

    @Override
    public int delete(String id) {
        return hospitalMapper.delete(id);
    }

    @Override
    public int update(String id, Hospital hospital1) {
        hospital1.setId(id);
        return hospitalMapper.update(hospital1);
    }

    @Override
    public List<Hospital> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Hospital> hospitals = hospitalMapper.list(keyword);
        return hospitals;
    }

    @Override
    public Hospital add(Hospital hospital1) {
        hospital1.setId(UUIDUtil.getUUID());
        hospitalMapper.add(hospital1);
        return hospital1;
    }

    @Override
    public List<HospitalCity> getCityHospitalList() {
        return hospitalMapper.getCityHospitalList();
    }

    @Override
    public List<Hospital> cityHospitalList(String city, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Hospital> hospitals = hospitalMapper.cityHospitalList(city);
        return hospitals;
    }
}
