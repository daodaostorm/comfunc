package com.comfunmanager.service;

import java.util.Collection;

import com.comfunmanager.beandata.Questioninfo;


/**
 * 接口
 *
 */
public interface QuestioninfoService {


	Collection<Questioninfo> findByUserid(String userid);
	
	Collection<Questioninfo> findByQuestionid(String questionid);
	
	void AddQuestionInfo(Questioninfo questioninfo);
	
	void deleteByQuestionid(String questionid);
	
}
