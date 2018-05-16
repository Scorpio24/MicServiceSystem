package com.yyu.dao;

import com.yyu.domain.ServiceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ServiceDao {

	List<ServiceDO> get();

	@Select("select * from service_tables where serviceName = #{serviceName}")
	ServiceDO getByServiceName(String serviceName);
	
	List<ServiceDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(ServiceDO serviceDO);
	
	int update(ServiceDO serviceDO);
	
	int remove(Long id);

}
