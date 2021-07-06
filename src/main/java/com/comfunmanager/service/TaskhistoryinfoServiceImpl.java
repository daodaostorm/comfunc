package com.comfunmanager.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comfunmanager.beandata.Serverinfo;
import com.comfunmanager.beandata.Taskhistoryinfo;
import com.comfunmanager.repository.ServerinfoRepository;
import com.comfunmanager.repository.TaskhistoryinfoRepository;


/**
 *
 */
@Service
public class TaskhistoryinfoServiceImpl implements TaskhistoryinfoService {

	private TaskhistoryinfoRepository taskhistoryinfoRepository;

	/**
	 * TaskhistoryinfoRepository
	 * 
	 * @param taskhistoryinfoRepository
	 */
	@Autowired
	public TaskhistoryinfoServiceImpl(TaskhistoryinfoRepository taskhistoryinfoRepository) {
		this.taskhistoryinfoRepository = taskhistoryinfoRepository;
	}


	@Override
	public Collection<Taskhistoryinfo> findByTaskid(String taskid) {
		// TODO Auto-generated method stub
		Collection<Taskhistoryinfo> serinfo = taskhistoryinfoRepository.findByTaskid(taskid);
		
		return serinfo;
	}


	@Override
	public void AddTaskInfo(Taskhistoryinfo taskinfo) {
		// TODO Auto-generated method stub
		taskhistoryinfoRepository.save(taskinfo);
	}


	@Override
	public void deleteByTaskid(String taskid) {
		// TODO Auto-generated method stub
		
		Collection<Taskhistoryinfo> infos = taskhistoryinfoRepository.findByTaskid(taskid);
		
		if (infos != null && infos.size() > 0) {
			for (Taskhistoryinfo serverinfo : infos ) {
				taskhistoryinfoRepository.delete(serverinfo.getId());
			}
		}
		
		
	}


	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		taskhistoryinfoRepository.delete(id);
	}
	
	
}
