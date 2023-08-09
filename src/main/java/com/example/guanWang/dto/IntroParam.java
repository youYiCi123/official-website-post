package com.example.guanWang.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class IntroParam {
    @JsonSerialize(using = ToStringSerializer.class)
    private  Long  id;
    private String introduce;
    private String honorPics;
    private String patentPics; //专利相册
}
