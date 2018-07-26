package com.metacube.sageclarity.predictable.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metacube.sageclarity.predictable.entity.DemoEntity;
import com.metacube.sageclarity.predictable.vo.DemoEntityVO;
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
}
