package com.metacube.sageclarity.predictable.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metacube.sageclarity.predictable.entity.*;
import com.metacube.sageclarity.predictable.enums.UserRoleEnum;
import com.metacube.sageclarity.predictable.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResponseHelper {
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(ResponseHelper.class);

	public static List<DemoEntityVO> getDemoEntityVOList(List<DemoEntity> demoEntities){
		List<DemoEntityVO> voList = new ArrayList<>();
		for(DemoEntity obj:demoEntities){
			voList.add(new DemoEntityVO(obj));
		}
		return voList;
	}

	public static List<RoleVO> getRoleVOList(List<Role> roles){
		List<RoleVO> roleVOS = new ArrayList<>();
		for(Role role:roles){
			if(!role.getRole().equals(UserRoleEnum.SUPER_ADMIN)){
				roleVOS.add(new RoleVO(role));
			}
		}
		return roleVOS;
	}

	public static  List<UserVO> getUserVOList(List<User> users){
		List<UserVO> userVOs = new ArrayList<>();
		for(User user : users){
			userVOs.add(new UserVO(user));
		}
		return userVOs;
	}

	public static  List<CompanyVO> getCompanyVOList(List<Company> companies){
		List<CompanyVO> companyVOS = new ArrayList<>();
		for(Company company : companies){
			companyVOS.add(new CompanyVO(company));
		}
		return companyVOS;
	}

	public static  List<ProductVO> getProductVOList(List<Product> products){
		List<ProductVO> productsVOs = new ArrayList<>();
		for(Product product : products){
			productsVOs.add(new ProductVO(product));
		}
		return productsVOs;
	}

	public static  List<ProductionScheduleMasterVO> getScheduleVOList(List<ProductionScheduleMaster> schedules){
		List<ProductionScheduleMasterVO> scheduleVOs = new ArrayList<>();
		for(ProductionScheduleMaster schedule : schedules){
			scheduleVOs.add(new ProductionScheduleMasterVO(schedule));
		}
		return scheduleVOs;
	}
}
