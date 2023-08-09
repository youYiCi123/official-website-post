package com.example.guanWang.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OssTokenVO {
    private String region;
    private String accessKeyId;
    private String accessKeySecret;
    private String stsToken;
    private String bucket;
}
