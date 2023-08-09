package com.example.guanWang.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

public class SolutionSeriesModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String seriesName;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    private Integer seriesLevel;

    private Date createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSeriesLevel() {
        return seriesLevel;
    }

    public void setSeriesLevel(Integer seriesLevel) {
        this.seriesLevel = seriesLevel;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "SolutionSeriesModel{" +
                "id=" + id +
                ", seriesName='" + seriesName + '\'' +
                ", parentId=" + parentId +
                ", seriesLevel=" + seriesLevel +
                ", createdTime=" + createdTime +
                '}';
    }
}
