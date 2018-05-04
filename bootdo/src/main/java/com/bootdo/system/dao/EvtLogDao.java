package com.bootdo.system.dao;

import com.bootdo.system.domain.EvtLogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-31 12:30:13
 */
@Mapper
public interface EvtLogDao {

	EvtLogDO get(Long logNr);
	
	List<EvtLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EvtLogDO evtLog);
	
	int update(EvtLogDO evtLog);
	
	int remove(Long LOG_NR_);
	
	int batchRemove(Long[] logNrs);
}
