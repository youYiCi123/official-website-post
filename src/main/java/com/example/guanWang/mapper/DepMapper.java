package com.example.guanWang.mapper;

import com.example.guanWang.model.Dep;
import com.example.guanWang.model.DepIdToName;
import com.example.guanWang.model.depUserRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepMapper {

    int insert(Dep dep);

    List<Dep> list(@Param("keyword") String keyword);

    List<DepIdToName> getDepIdToName();

    Dep getDepByDeptId(@Param("deptId") Long depId);

    int update(Dep dep);

    int delete(Long id);

    List<depUserRelation> selectUserRelation();

}
