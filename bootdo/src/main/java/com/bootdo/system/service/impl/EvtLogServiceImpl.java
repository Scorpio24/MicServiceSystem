package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.EvtLogDao;
import com.bootdo.system.domain.EvtLogDO;
import com.bootdo.system.service.EvtLogService;



@Service
public class EvtLogServiceImpl implements EvtLogService {
	@Autowired
	private EvtLogDao evtLogDao;
	
	@Override
	public EvtLogDO get(Long logNr){
		return evtLogDao.get(logNr);
	}
	
	@Override
	public List<EvtLogDO> list(Map<String, Object> map){
		return evtLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return evtLogDao.count(map);
	}
	
	@Override
	public int save(EvtLogDO evtLog){
		return evtLogDao.save(evtLog);
	}
	
	@Override
	public int update(EvtLogDO evtLog){
		return evtLogDao.update(evtLog);
	}
	
	@Override
	public int remove(Long logNr){
		return evtLogDao.remove(logNr);
	}
	
	@Override
	public int batchRemove(Long[] logNrs){
		return evtLogDao.batchRemove(logNrs);
	}
	
}
