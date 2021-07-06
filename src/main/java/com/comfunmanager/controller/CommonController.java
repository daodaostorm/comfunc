package com.comfunmanager.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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
import com.comfunmanager.beanrequest.SignResult;
import com.comfunmanager.beanrequest.WxOpenInfoResult;
import com.comfunmanager.message.Message;
import com.comfunmanager.service.ServerinfoService;
import com.comfunmanager.service.TaskhistoryinfoService;
import com.comfunmanager.utils.Constans;
import com.comfunmanager.utils.DateTimeUtils;
import com.comfunmanager.utils.OkHttpUtil;
import com.comfunmanager.utils.ServerinfoUtils;
import com.comfunmanager.utils.SignUtils;
import com.comfunmanager.utils.UtilsJSONChange;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", name = "CommonAPI")
@Api(description = "CommonAPI")

public class CommonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);
	private Message message = new Message();


	@Autowired
	public CommonController(ServerinfoService serverinfoService) {

	}

	@RequestMapping(value = "common/signencode", method = RequestMethod.POST)
	@ApiOperation(value = "encode", notes = "encode")
	public ResponseEntity<Message> signEncode(@RequestBody Map params) {

		String strTest = params.get("istest").toString();
		boolean isTest = true;
		if (strTest.equals("test")) {
			isTest = true;
		} else {
			isTest = false;
		}

		LOGGER.info("isTest: " + isTest);
		String strString = SignUtils.orgCode + DateTimeUtils.getNowSecond();
		LOGGER.info("strString: " + strString);
		String strResult = null;
		try {
			strResult = SignUtils.Encrypt(strString, isTest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (strResult == null || strResult.equals("")) {
			message.setMsg(-1, "calc error", "calc error");
		} else {

			SignResult result = new SignResult();
			
			result.setSign(strResult);

			message.setMsg(0, "ok", result);

		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "common/wxopenid", method = RequestMethod.POST)
	@ApiOperation(value = "encode", notes = "encode")
	public ResponseEntity<Message> wxOpenid(@RequestBody Map params) {

		String strCode = params.get("code").toString();
		String strName = params.get("name").toString();

		String wxUrl = String.format(Constans.wxOpenId_Url, Constans.wxAppid, Constans.wxAppKey, strCode, Constans.wxGrantType);
		
		LOGGER.info("strCode: " + strCode);
		LOGGER.info("strName: " + strName);
		String strResult = null;
		strResult = OkHttpUtil.get(wxUrl, null);
		
		try {
			JSONObject object = new JSONObject(strResult);
			
			if (object.has("errcode")) {
				
				message.setMsg(-2, object.getString("errmsg"), null);
				
				return new ResponseEntity<Message>(message, HttpStatus.OK);
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			message.setMsg(-1, "json error", null);
			
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
		
		WxOpenInfoResult resultinfo = null;
		try {
			resultinfo = (WxOpenInfoResult) UtilsJSONChange.stringToObject(strResult, WxOpenInfoResult.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setMsg(-3, "json error", null);
			
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
		

		message.setMsg(0, "ok", resultinfo);
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value = "common/getinfobycode", method = RequestMethod.POST)
	@ApiOperation(value = "encode", notes = "encode")
	public ResponseEntity<Message> getByCode(@RequestBody Map params) {

		String strCode = params.get("code").toString();
		String strName = params.get("name").toString();

		String wxUrl = String.format(Constans.wxOpenId_Url, Constans.wxAppid, Constans.wxAppKey, strCode, Constans.wxGrantType);
		
		LOGGER.info("strCode: " + strCode);
		LOGGER.info("strName: " + strName);
		String strResult = null;
		strResult = OkHttpUtil.get(wxUrl, null);
		
		try {
			JSONObject object = new JSONObject(strResult);
			
			if (object.has("errcode")) {
				
				message.setMsg(-2, object.getString("errmsg"), null);
				
				return new ResponseEntity<Message>(message, HttpStatus.OK);
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			message.setMsg(-1, "json error", null);
			
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
		
		WxOpenInfoResult resultinfo = null;
		try {
			resultinfo = (WxOpenInfoResult) UtilsJSONChange.stringToObject(strResult, WxOpenInfoResult.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setMsg(-3, "json error", null);
			
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
		

		message.setMsg(0, "ok", resultinfo);
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	
	
}
