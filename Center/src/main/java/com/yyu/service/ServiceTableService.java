package com.yyu.service;

import com.yyu.domain.ServiceDO;

import java.util.List;
import java.util.Map;


public interface ServiceTableService {
	
	List<ServiceDO> get();
	
	List<ServiceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ServiceDO serviceDO);

	int update(ServiceDO serviceDO);
	
	int remove(Long id);

}
