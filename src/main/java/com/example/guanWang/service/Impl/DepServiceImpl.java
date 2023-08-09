package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.dto.DepParam;
import com.example.guanWang.mapper.DepMapper;
import com.example.guanWang.mapper.UmsAdminMapper;
import com.example.guanWang.mapper.UmsAdminRoleRelationMapper;
import com.example.guanWang.model.*;
import com.example.guanWang.service.DepService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class DepServiceImpl implements DepService {

    @Autowired
    private DepMapper depMapper;

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Override
    public Dep add(DepParam depParam) {
        Dep dep=new Dep();
        BeanUtils.copyProperties(depParam, dep);
        Long depId= IdGenerator.nextId();
        dep.setId(depId);
        dep.setCreateTime(new Date());
        depMapper.insert(dep);
        String leadPhone=dep.getLeadPhone();
        Long userId=umsAdminMapper.getUserByPhone(leadPhone);
        if(userId==null||userId==0){  //用户表中没有该用户
            //添加用户
            UmsAdmin umsAdmin=new UmsAdmin();
            Long newUserId= Long.valueOf(String.valueOf(new Random().nextLong()).substring(1,8));
            umsAdmin.setId(newUserId);
            umsAdmin.setUsername(leadPhone);
            umsAdmin.setPassword(passwordEncoder.encode("123456"));
            umsAdmin.setDepId(depId);
            umsAdmin.setCreateTime(new Date());
            umsAdmin.setDuty("部门经理");
            umsAdmin.setNickName(dep.getLeadName());
            umsAdmin.setPhoneNumber(leadPhone);
            umsAdmin.setStatus(1);
            umsAdminMapper.insertSelective(umsAdmin);
            //分配超管角色
            UmsAdminRoleRelation umsAdminRoleRelation= new UmsAdminRoleRelation();
            umsAdminRoleRelation.setRoleId(Long.valueOf(5));
            umsAdminRoleRelation.setAdminId(newUserId);
            umsAdminRoleRelationMapper.insertSelective(umsAdminRoleRelation);
        }
        return dep;
    }

    @Override
    public List<Dep> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dep> depList= depMapper.list(keyword);
        return depList;
    }

    @Override
    public List<DepIdToName> getDepIdToName() {
        return depMapper.getDepIdToName();
    }

    @Override
    public Dep getDepByDeptId(Long deptId) {
      return depMapper.getDepByDeptId(deptId);
    }

    @Override
    public List<DepUser> details(Long depId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<DepUser> depUsers= umsAdminMapper.details(depId);
        return depUsers;
    }

    @Override
    public int update(Long id, Dep dep) {
        dep.setId(id);
        return depMapper.update(dep);
    }

    @Override
    public int delete(Long id){
        return depMapper.delete(id);
    }
    @Override
    public List<depUserRelation> selectUserRelation(){
        return  depMapper.selectUserRelation();
    }
}
