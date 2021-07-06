package com.comfunmanager.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comfunmanager.beandata.Questioninfo;
import com.comfunmanager.message.Message;
import com.comfunmanager.service.QuestioninfoService;
import com.comfunmanager.utils.DateTimeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = "/api/v1", name = "QuestionInfoAPI")
@Api(description = "QuestionInfoAPI")

public class QuestionInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionInfoController.class);
	private Message message = new Message();

	private QuestioninfoService questioninfoService;
	
	@Autowired
	public QuestionInfoController(QuestioninfoService service) {

		questioninfoService = service;
	}

	@RequestMapping(value = "question/addnew", method = RequestMethod.POST)
	@ApiOperation(value = "addnew", notes = "addnew")
	public ResponseEntity<Message> addNew(@RequestBody Map params) {

		Questioninfo info = new Questioninfo();
		JSONObject obj = JSONObject.fromObject(params);
		String userid = obj.getString("userid"); 
		info.setUserid(userid);
		if (obj.has("questionname") && !obj.getString("questionname").equals("")) {
			info.setQuestionname(obj.getString("questionname"));
		} else {
			Collection<Questioninfo> resultinfo = questioninfoService.findByUserid(userid);
			if (resultinfo == null) {
				info.setQuestionname("问题" + "1");
			} else {
				info.setQuestionname("问题" + (resultinfo.size() + 1));
			}
		}
		info.setAnswerneg(obj.getString("answerneg"));
		info.setAnswerpos(obj.getString("answerpos"));
		info.setStatus("1");
		info.setQuestionid(DateTimeUtils.createNowTimeId());
		info.setUpdatetime(DateTimeUtils.getNowDateTime());

		questioninfoService.AddQuestionInfo(info);
		
		message.setMsg(0, "ok", info);
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "question/getall", method = RequestMethod.POST)
	@ApiOperation(value = "getall", notes = "getall")
	public ResponseEntity<Message> getAll(@RequestBody Map params) {

		String strUserid = params.get("userid").toString();
		LOGGER.info("strUserid: " + strUserid);
		Collection<Questioninfo> resultinfo = questioninfoService.findByUserid(strUserid);

		message.setMsg(0, "ok", resultinfo);
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "question/editone", method = RequestMethod.POST)
	@ApiOperation(value = "editone", notes = "editone")
	public ResponseEntity<Message> editOne(@RequestBody Map params) {

		Questioninfo info = null;
		JSONObject obj = JSONObject.fromObject(params);
		String strQuestionid = obj.getString("questionid"); 

		Collection<Questioninfo> resultinfo = questioninfoService.findByQuestionid(strQuestionid);
		
		if (resultinfo != null && resultinfo.size() > 0) {
			info = (Questioninfo) resultinfo.toArray()[0];
			
		} else {
			info = new Questioninfo();
			info.setQuestionid(DateTimeUtils.createNowTimeId());
		}
		
		if (obj.has("questionname") && !obj.getString("questionname").equals("")) {
			info.setQuestionname(obj.getString("questionname"));
		} else {
			info.setQuestionname("问题");
		}
		info.setAnswerneg(obj.getString("answerneg"));
		info.setAnswerpos(obj.getString("answerpos"));
		info.setStatus("1");
		info.setUpdatetime(DateTimeUtils.getNowDateTime());

		questioninfoService.AddQuestionInfo(info);
		
		message.setMsg(0, "ok", info);
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value = "question/remove", method = RequestMethod.POST)
	@ApiOperation(value = "remove", notes = "remove")
	public ResponseEntity<Message> removeOne(@RequestBody Map params) {

		String strQuestionid = params.get("questionid").toString();

		questioninfoService.deleteByQuestionid(strQuestionid);

		message.setMsg(0, "ok", "ok");
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
}
