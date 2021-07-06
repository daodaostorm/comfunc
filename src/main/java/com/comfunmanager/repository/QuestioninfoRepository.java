package com.comfunmanager.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.comfunmanager.beandata.ClientUser;
import com.comfunmanager.beandata.Questioninfo;
import com.comfunmanager.beandata.Serverinfo;
import com.comfunmanager.beandata.Taskhistoryinfo;


/**
 * 表Repository定义
 *
 */
@RestResource(exported = false)

public interface QuestioninfoRepository extends PagingAndSortingRepository<Questioninfo, Long> {

	Page<Questioninfo> findByIdGreaterThan(Long startid, Pageable p);

	Collection<Questioninfo> findByUserid(String userid);

	Collection<Questioninfo> findByQuestionid(String questionid);
	
}
