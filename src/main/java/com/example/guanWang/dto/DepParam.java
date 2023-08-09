package com.example.guanWang.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class DepParam {
    @NotEmpty
    private String depName;

    private String leadName;

    private String leadPhone;

    private String note;
}
