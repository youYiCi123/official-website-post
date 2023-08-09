package com.example.guanWang.mapper;

import com.example.guanWang.model.RoleGroupCount;
import com.example.guanWang.model.UmsRole;
import com.example.guanWang.model.UmsRoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UmsRoleMapper {
    long countByExample(UmsRoleExample example);

    int deleteByExample(UmsRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRole record);

    int insertSelective(UmsRole record);

    List<UmsRole> selectByExample(UmsRoleExample example);

    UmsRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);

    int updateByExample(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);

    int updateByPrimaryKeySelective(UmsRole record);

    int updateByPrimaryKey(UmsRole record);

   int addAdminCount(@Param("roleId") List<Long> roleId);

    int subAdminCount(@Param("roleId") List<Long> roleId);

    int subAdminCountByClass(@Param("RoleGroupCounts") List<RoleGroupCount> RoleGroupCounts);
}