package com.comfunmanager.controller;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comfunmanager.bean.ServerInfoData;
import com.comfunmanager.beandata.Serverinfo;
import com.comfunmanager.beandata.Taskhistoryinfo;
import com.comfunmanager.message.Message;
import com.comfunmanager.service.ServerinfoService;
import com.comfunmanager.service.TaskhistoryinfoService;
import com.comfunmanager.utils.DateTimeUtils;
import com.comfunmanager.utils.ServerinfoUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", name = "ServerinfoAPI")
@Api(description = "ServerinfoAPI")

public class ServerinfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerinfoController.class);
	private Message message = new Message();

	private ServerinfoService serverinfoService;
	private TaskhistoryinfoService taskhistoryinfoService;

	@Autowired
	public ServerinfoController(ServerinfoService serverinfoService, TaskhistoryinfoService taskhistoryinfoService) {
		this.serverinfoService = serverinfoService;
		this.taskhistoryinfoService = taskhistoryinfoService;
	}

	
	@GetMapping(value = "serverinfo/getaddressinfoa")
	@ApiOperation(value = "获取地址", notes = "获取地址信息")
	public ResponseEntity<Message> serverinfoGetA(@RequestParam String taskid) {

		String strTaskid = taskid;
		LOGGER.info("get addressA: " + strTaskid);

		ServerInfoData serverdata = ServerinfoUtils.getInstance(serverinfoService).getServerInfo(taskhistoryinfoService,
				strTaskid);

		if (serverdata == null) {
			message.setMsg(-1, "no server", "user exist");
		} else {

			message.setMsg(0, "ok", serverdata);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "serverinfo/getaddressinfo", method = RequestMethod.POST)
	@ApiOperation(value = "获取地址", notes = "获取地址信息")
	public ResponseEntity<Message> serverinfoGet(@RequestBody Map params) {

		String strTaskid = params.get("taskid").toString();
		// String strPass = params.get("password").toString();
		LOGGER.info("get address: " + strTaskid);

		ServerInfoData serverdata = ServerinfoUtils.getInstance(serverinfoService).getServerInfo(taskhistoryinfoService,
				strTaskid);

		if (serverdata == null) {
			message.setMsg(-1, "no server", "user exist");
		} else {

			message.setMsg(0, "ok", serverdata);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "serverinfo/checkaddressinfo", method = RequestMethod.POST)
	@ApiOperation(value = "获取地址", notes = "获取地址信息")
	public ResponseEntity<Message> serverinfoCheck(@RequestBody Map params) {

		String strTaskid = params.get("taskid").toString();
		// String strPass = params.get("password").toString();
		LOGGER.info("check taskid: " + strTaskid);

		ServerInfoData serverdata = ServerinfoUtils.getInstance(serverinfoService)
				.checkServerInfo(taskhistoryinfoService, strTaskid);

		if (serverdata == null) {
			message.setMsg(-1, "no server", "task not exist");
		} else {

			message.setMsg(0, "ok", serverdata);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "serverinfo/addaddressinfo", method = RequestMethod.POST)
	@ApiOperation(value = "添加服务器地址", notes = "添加地址信息")
	public ResponseEntity<Message> serverinfoAdd(@RequestBody Map params) {

		String strServerhost = params.get("serverhost").toString();
		String strServerport = params.get("serverport").toString();
		String strServerstatus = params.get("serverstatus").toString();

		LOGGER.info("add serverhost: " + strServerhost);

		Collection<Serverinfo> serverlist = serverinfoService.findByServerhost(strServerhost);
		if (serverlist != null && serverlist.size() >= 1) {
			message.setMsg(-1, "服务器已存在", "server exist");
		} else {

			Serverinfo serinfo = new Serverinfo();
			
			serinfo.setServerhost(strServerhost);
			serinfo.setServerport(strServerport);
			serinfo.setServerstatus(strServerstatus);
			serinfo.setServerid(DateTimeUtils.createNowTimeId());
			serinfo.setCreatetime(DateTimeUtils.getNowDateTime());
			serinfo.setUpdatetime(DateTimeUtils.getNowDateTime());
			
			serverinfoService.UpdateInfo(serinfo);
			
			ServerinfoUtils.getInstance(serverinfoService).UpdateServiceList(serinfo);


			message.setMsg(0, "ok", serinfo);

		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value = "serverinfo/removeaddressinfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除服务器地址", notes = "删除地址信息")
	public ResponseEntity<Message> serverinfoRemove(@RequestBody Map params) {

		String strServerhost = params.get("serverhost").toString();

		LOGGER.info("remove serverhost: " + strServerhost);

		Collection<Serverinfo> serverlist = serverinfoService.findByServerhost(strServerhost);
		if (serverlist == null || serverlist.size() == 0) {
			message.setMsg(-1, "服务器不存在", "not exist");
		} else {

		
			for (Serverinfo serinfo : serverlist) {
				serverinfoService.deleteById(serinfo.getId());
			}


			message.setMsg(0, "ok", "");

		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value = "serverinfo/removehistoryinfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除History地址", notes = "删除History信息")
	public ResponseEntity<Message> historyinfoRemove(@RequestBody Map params) {

		String strTaskid = params.get("taskid").toString();

		LOGGER.info("remove strTaskid: " + strTaskid);

		Collection<Taskhistoryinfo> serverlist = taskhistoryinfoService.findByTaskid(strTaskid);
		if (serverlist == null || serverlist.size() == 0) {
			message.setMsg(-1, "TaskId不存在", "not exist");
		} else {

		
			for (Taskhistoryinfo serinfo : serverlist) {
				taskhistoryinfoService.deleteById(serinfo.getId());
			}


			message.setMsg(0, "ok", "");

		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value = "serverinfo/test", method = RequestMethod.POST)
	@ApiOperation(value = "测试", notes = "测试")
	public ResponseEntity<Message> forTest(@RequestBody Map params) {

		String errCode = params.get("errCode").toString();
		String errInfo = params.get("errInfo").toString();
		String taskId = params.get("taskId").toString();
		String checkResult = params.get("checkResult").toString();

		LOGGER.info("code: " + errCode + "info: " + errInfo +" taskid: "+ taskId + "result: " + checkResult);

		message.setMsg(0, "ok", "");
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
}
