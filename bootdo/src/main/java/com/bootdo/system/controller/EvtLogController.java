package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.EvtLogDO;
import com.bootdo.system.service.EvtLogService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-31 12:30:13
 */
 
@Controller
@RequestMapping("/system/evtLog")
public class EvtLogController {
	@Autowired
	private EvtLogService evtLogService;
	
	@GetMapping()
	@RequiresPermissions("system:evtLog:evtLog")
	String EvtLog(){
	    return "system/evtLog/evtLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:evtLog:evtLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EvtLogDO> evtLogList = evtLogService.list(query);
		int total = evtLogService.count(query);
		PageUtils pageUtils = new PageUtils(evtLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:evtLog:add")
	String add(){
	    return "system/evtLog/add";
	}

	@GetMapping("/edit/{logNr}")
	@RequiresPermissions("system:evtLog:edit")
	String edit(@PathVariable("logNr") Long logNr,Model model){
		EvtLogDO evtLog = evtLogService.get(logNr);
		model.addAttribute("evtLog", evtLog);
	    return "system/evtLog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:evtLog:add")
	public R save( EvtLogDO evtLog){
		if(evtLogService.save(evtLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:evtLog:edit")
	public R update( EvtLogDO evtLog){
		evtLogService.update(evtLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:evtLog:remove")
	public R remove( Long logNr){
		if(evtLogService.remove(logNr)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:evtLog:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] logNrs){
		evtLogService.batchRemove(logNrs);
		return R.ok();
	}
	
}
