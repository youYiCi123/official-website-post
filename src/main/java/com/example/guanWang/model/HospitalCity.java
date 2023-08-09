package com.example.guanWang.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class HospitalCity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String city;
    private int count;
}
