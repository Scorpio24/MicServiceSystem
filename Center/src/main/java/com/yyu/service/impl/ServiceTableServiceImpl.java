package com.yyu.service.impl;

import com.yyu.dao.ServiceDao;
import com.yyu.domain.ServiceDO;
import com.yyu.service.ServiceTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class ServiceTableServiceImpl implements ServiceTableService {

	@Autowired
	private ServiceDao serviceDao;

	@Override
	public List<ServiceDO> get(){
		return serviceDao.get();
	}

	@Override
	public List<ServiceDO> list(Map<String, Object> map){
		return serviceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return serviceDao.count(map);
	}
	
	@Override
	public int save(ServiceDO serviceDO){
		return serviceDao.save(serviceDO);
	}
	
	@Override
	public int update(ServiceDO serviceDO){
		return serviceDao.update(serviceDO);
	}
	
	@Override
	@Transactional
	public int remove(Long id){
		return serviceDao.remove(id);
	}

}
