package com.example.guanWang.mapper;

import com.example.guanWang.model.Hospital;
import com.example.guanWang.model.HospitalCity;

import java.util.List;

public interface HospitalMapper {
    int delete(String id);

    int update(Hospital hospital1);

    List<Hospital> list(String  keyword);

    int add( Hospital hospital1);

    List<HospitalCity> getCityHospitalList();

    List<Hospital> cityHospitalList(String city);
}
