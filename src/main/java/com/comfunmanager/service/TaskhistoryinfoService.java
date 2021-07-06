package com.comfunmanager.service;

import java.util.Collection;

import com.comfunmanager.beandata.Taskhistoryinfo;


/**
 * 接口
 *
 */
public interface TaskhistoryinfoService {


	Collection<Taskhistoryinfo> findByTaskid(String taskid);
	
	void AddTaskInfo(Taskhistoryinfo taskinfo);
	
	void deleteByTaskid(String taskid);
	
	void deleteById(Long id);
}
