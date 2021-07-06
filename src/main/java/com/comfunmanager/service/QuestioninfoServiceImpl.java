package com.comfunmanager.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comfunmanager.beandata.Questioninfo;
import com.comfunmanager.repository.QuestioninfoRepository;


/**
 *
 */
@Service
public class QuestioninfoServiceImpl implements QuestioninfoService {

	private QuestioninfoRepository repository;

	/**
	 * TaskhistoryinfoRepository
	 * 
	 * @param taskhistoryinfoRepository
	 */
	@Autowired
	public QuestioninfoServiceImpl(QuestioninfoRepository repository) {
		this.repository = repository;
	}


	@Override
	public Collection<Questioninfo> findByUserid(String userid) {
		// TODO Auto-generated method stub
		Collection<Questioninfo> info = repository.findByUserid(userid);
		
		return info;
	}


	@Override
	public void AddQuestionInfo(Questioninfo info) {
		// TODO Auto-generated method stub
		repository.save(info);
	}


	@Override
	public Collection<Questioninfo> findByQuestionid(String questionid) {
		// TODO Auto-generated method stub
		
		Collection<Questioninfo> info = repository.findByQuestionid(questionid);
		
		return info;
	}


	@Override
	public void deleteByQuestionid(String questionid) {
		// TODO Auto-generated method stub
		
		Collection<Questioninfo> infos = repository.findByQuestionid(questionid);
		
		if (infos != null && infos.size() > 0) {
			for (Questioninfo serverinfo : infos ) {
				repository.delete(serverinfo.getId());
			}
		}
		
		
	}
	
	
}
