package com.comfunmanager.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.comfunmanager.beandata.ClientUser;
import com.comfunmanager.beandata.Serverinfo;
import com.comfunmanager.beandata.Taskhistoryinfo;


/**
 * 表Repository定义
 *
 */
@RestResource(exported = false)

public interface TaskhistoryinfoRepository extends PagingAndSortingRepository<Taskhistoryinfo, Long> {

	Page<Taskhistoryinfo> findByIdGreaterThan(Long startid, Pageable p);

	Collection<Taskhistoryinfo> findByTaskid(String taskid);

}
