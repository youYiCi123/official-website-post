package com.example.guanWang.service;



import com.example.guanWang.model.Hospital;
import com.example.guanWang.model.HospitalCity;

import java.util.List;

public interface HospitalService {

    int delete(String id);

    int update(String id, Hospital hospital1);

    List<Hospital> list(String keyword, Integer pageSize, Integer pageNum);

    Hospital  add(Hospital hospital1);

    List<HospitalCity> getCityHospitalList();

    List<Hospital> cityHospitalList(String city, Integer pageSize, Integer pageNum);

}
