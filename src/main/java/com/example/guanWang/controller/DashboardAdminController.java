package com.example.guanWang.controller;

import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.model.HospitalCity;
import com.example.guanWang.model.depUserRelation;
import com.example.guanWang.service.DepService;
import com.example.guanWang.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 后台管理仪表盘
 * @Author: Naccl
 * @Date: 2020-10-08
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardAdminController {

	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private DepService depService;

	@GetMapping
	public CommonResult<Object> dashboard() {
		List<HospitalCity> cityVisitorList = hospitalService.getCityHospitalList();
		List<depUserRelation> depUserRelationList= depService.selectUserRelation();

		Map<String, Object> map = new HashMap<>(16);
		map.put("cityHospital", cityVisitorList);
		map.put("depUserRelationList", depUserRelationList);
		return CommonResult.success(map, "获取成功");
	}
}
