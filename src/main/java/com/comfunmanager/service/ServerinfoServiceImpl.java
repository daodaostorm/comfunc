package com.comfunmanager.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comfunmanager.beandata.Serverinfo;
import com.comfunmanager.repository.ServerinfoRepository;


/**
 *
 */
@Service
public class ServerinfoServiceImpl implements ServerinfoService {


	private ServerinfoRepository serverinfoRepository;

	/**
	 * ServerinfoRepository
	 * 
	 * @param serverinfoRepository
	 */
	@Autowired
	public ServerinfoServiceImpl(ServerinfoRepository serverinfoRepository) {
		this.serverinfoRepository = serverinfoRepository;
	}


	/**
	 * 获取列表
	 */
	@Override
	public Page<Serverinfo> getServerinfoList(Long startid, Pageable p) {
		Page<Serverinfo> user = serverinfoRepository.findByIdGreaterThan(startid, p);
		return user;
	}


	@Override
	public Collection<Serverinfo> findByServerhost(String hostname) {
		Collection<Serverinfo> serinfo = serverinfoRepository.findByServerhost(hostname);
		
		return serinfo;
		
	}


	@Override
	public Collection<Serverinfo> findByServerid(String serverid) {
		// TODO Auto-generated method stub
		Collection<Serverinfo> serinfo = serverinfoRepository.findByServerid(serverid);
		
		return serinfo;
	}


	@Override
	public void UpdateInfo(Serverinfo serverinfo) {
		// TODO Auto-generated method stub
		serverinfoRepository.save(serverinfo);
	}


	@Override
	public void deleteByServerhost(String serverhost) {
		// TODO Auto-generated method stub
		Collection<Serverinfo> infos = serverinfoRepository.findByServerhost(serverhost);
		
		if (infos != null && infos.size() > 0) {
			for (Serverinfo serverinfo : infos ) {
				serverinfoRepository.delete(serverinfo.getId());
			}
		}
		
		
	}


	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		serverinfoRepository.delete(id);
	}
	
	

}
