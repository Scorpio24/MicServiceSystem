package com.bootdo.system.service;

import com.bootdo.system.domain.EvtLogDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-31 12:30:13
 */
public interface EvtLogService {
	
	EvtLogDO get(Long logNr);
	
	List<EvtLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EvtLogDO evtLog);
	
	int update(EvtLogDO evtLog);
	
	int remove(Long logNr);
	
	int batchRemove(Long[] logNrs);
}
