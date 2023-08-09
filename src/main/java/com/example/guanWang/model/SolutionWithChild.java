package com.example.guanWang.model;

import java.util.List;

public class SolutionWithChild extends SolutionSeriesModel {

    private List<SolutionSeriesModel> children;

    public List<SolutionSeriesModel> getChildren() {
        return children;
    }

    public void setChildren(List<SolutionSeriesModel> children) {
        this.children = children;
    }
}
