package com.comfunmanager.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.comfunmanager.bean.ServerInfoData;
import com.comfunmanager.beandata.Serverinfo;
import com.comfunmanager.beandata.Taskhistoryinfo;
import com.comfunmanager.service.ServerinfoService;
import com.comfunmanager.service.TaskhistoryinfoService;

/**
 *
 * @author Administrator
 *
 */
public class ServerinfoUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerinfoUtils.class);
	
	private ArrayList<ServerInfoData> mServerinfolist;

	private static ServerinfoUtils mServerinfoUtils;
	private int mWeight;

	private ServerinfoUtils(ServerinfoService serverinfoService) {

		LOGGER.info("ServerinfoUtils");
		
		if (mServerinfolist == null) {
			mServerinfolist = new ArrayList<ServerInfoData>();
		}
		initdata(serverinfoService);
	}

	public static ServerinfoUtils getInstance(ServerinfoService serverinfoService) {

		if (mServerinfoUtils == null) {
			LOGGER.info("getInstance");
			synchronized (ServerinfoUtils.class) {
				if (mServerinfoUtils == null) {
					mServerinfoUtils = new ServerinfoUtils(serverinfoService);
				}
			}

		}

		return mServerinfoUtils;
	}

	private void initdata(ServerinfoService serverinfoService) {
		
		LOGGER.info("initdata ");
		
		mServerinfolist.clear();
		Pageable pageable = new PageRequest(0, 20);

		Page<Serverinfo> page = serverinfoService.getServerinfoList(0L, pageable);
		List<Serverinfo> list = page.getContent();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ServerInfoData infodata = new ServerInfoData();
				infodata.setServerhost(list.get(i).getServerhost());
				infodata.setServerid(list.get(i).getServerid());
				infodata.setServerport(list.get(i).getServerport());
				infodata.setTaskid("");
				infodata.setWeight(0);
				mServerinfolist.add(infodata);
			}
		}
		mWeight = 1;

	}

	public void UpdateServiceList(Serverinfo info) {
		
		boolean isExist = false;
		
		for (int i = 0; i < mServerinfolist.size(); i++) {
			if (info.getServerhost().equals(mServerinfolist.get(i).getServerhost())) {
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			ServerInfoData infodata = new ServerInfoData();
			infodata.setServerhost(info.getServerhost());
			infodata.setServerid(info.getServerid());
			infodata.setServerport(info.getServerport());
			infodata.setTaskid("");
			infodata.setWeight(0);
			mServerinfolist.add(infodata);
		}
	}
	
	public ServerInfoData getServerInfo(TaskhistoryinfoService taskService, String taskid) {
		ServerInfoData serverinfo = null;
		
		serverinfo = checkServerInfo(taskService, taskid);
		if (serverinfo != null) {
			return serverinfo;
		}
		
		int findCount = 0;
		for (int i = 0; i < mServerinfolist.size(); i++) {
			if (i == 0) {
				serverinfo = mServerinfolist.get(i);
				findCount = i;
			} else {
				if (mServerinfolist.get(i).getWeight() < serverinfo.getWeight()) {
					serverinfo = mServerinfolist.get(i);
					findCount = i;
				}
			}
		}
		mWeight += 1;
		mServerinfolist.get(findCount).setTaskid(taskid);
		mServerinfolist.get(findCount).setWeight(mWeight);

		Taskhistoryinfo taskinfo = new Taskhistoryinfo();
		taskinfo.setServerhost(serverinfo.getServerhost());
		taskinfo.setServerport(serverinfo.getServerport());
		taskinfo.setTaskid(taskid);
		taskinfo.setTaskstatus("");
		taskinfo.setUpdatetime(DateTimeUtils.getNowDateTime());
		taskService.AddTaskInfo(taskinfo);
		return serverinfo;
	}

	public ServerInfoData checkServerInfo(TaskhistoryinfoService taskService, String taskid) {
		ServerInfoData serverinfo = null;
		
		Collection<Taskhistoryinfo> infolist = taskService.findByTaskid(taskid);
		
		for (Taskhistoryinfo taskinfo : infolist) {
			if (taskid.equals(taskinfo.getTaskid())) {
				serverinfo = new ServerInfoData();
				serverinfo.setServerhost(taskinfo.getServerhost());
				serverinfo.setServerport(taskinfo.getServerport());
				serverinfo.setTaskid(taskid);
			}
		}

		return serverinfo;
	}

}