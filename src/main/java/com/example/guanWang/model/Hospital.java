package com.example.guanWang.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Hospital implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String hospitalName;
    private String directorName;
    private String directorPhone;
    private String serviceName;
    private String equipmentModel;
    private String receiverRegion;
    private String receiverCity;
    private String receiverProvince;
    private String receiverDetailAddress;
}
