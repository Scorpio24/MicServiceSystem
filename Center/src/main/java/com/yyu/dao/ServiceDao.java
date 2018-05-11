package com.yyu.dao;

import com.yyu.domain.ServiceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ServiceDao {

	ServiceDO get(Long id);

	@Select("select * from service_tables where serviceName = #{serviceName}")
	ServiceDO getByServiceName(String serviceName);
	
	List<ServiceDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(ServiceDO serviceDO);
	
	int update(ServiceDO serviceDO);
	
	int remove(Long id);

}
