package com.example.guanWang.mapper;


import com.example.guanWang.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UmsAdminMapper {
    long countByExample(UmsAdminExample example);

    int deleteByExample(UmsAdminExample example);

    int deleteByPrimaryKey(Long id);

    int deleteBatch(@Param("idList") List<Long> idList);

    int insert(UmsAdmin record);

    int insertSelective(UmsAdmin record);

    List<UmsAdmin> selectByExample(UmsAdminExample example);

    List<UmsAdmin> selectByQuery(@Param("keyword") String keyword, @Param("depId") String depId);

    List<String> getAllUserPhone();

    int saveBatch(@Param("list") List<UmsAdmin> list);

    List<DepUser> details(@Param("depId") Long depId);

    Long getUserByPhone(@Param("leadPhone") String leadPhone);

    UmsAdmin selectByPrimaryKey(Long id);

    String getUserNameById(Long id);

    List<UserNameAndId> getAllUserName();

    int updateByExampleSelective(@Param("record") UmsAdmin record, @Param("example") UmsAdminExample example);

    int updateByExample(@Param("record") UmsAdmin record, @Param("example") UmsAdminExample example);

    int updateByPrimaryKeySelective(UmsAdminShow record);

    int updateByPrimaryKey(UmsAdmin record);
}