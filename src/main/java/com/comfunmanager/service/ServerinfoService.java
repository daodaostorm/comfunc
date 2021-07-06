package com.comfunmanager.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.comfunmanager.beandata.Serverinfo;


/**
 * 接口
 *
 */
public interface ServerinfoService {


	Page<Serverinfo> getServerinfoList(Long startid, Pageable p);
	
	Collection<Serverinfo> findByServerid(String serverid);
	
	Collection<Serverinfo> findByServerhost(String serverhost);
	
	void deleteByServerhost(String serverhost);
	
	void deleteById(Long id);
	
	void UpdateInfo(Serverinfo serverinfo);

}
