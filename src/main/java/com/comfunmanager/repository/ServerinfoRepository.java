package com.comfunmanager.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.comfunmanager.beandata.Serverinfo;


/**
 * 表Repository定义
 *
 */
@RestResource(exported = false)

public interface ServerinfoRepository extends PagingAndSortingRepository<Serverinfo, Long> {

	Page<Serverinfo> findByIdGreaterThan(Long startid, Pageable p);

	Collection<Serverinfo> findByCreatetime(String createtime);
	Collection<Serverinfo> findByServerid(String serverid);
	Collection<Serverinfo> findByServerhost(String serverhost);

}
