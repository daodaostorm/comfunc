package com.comfunmanager.service;

import com.comfunmanager.beandata.Sms;

public interface SendSMSService {

	String send(String mobile);

	Sms findByUsernameAndVcode(String mobile, String vcode);

	void saveSmsConfig(String username, String password);

	Boolean checkSmsAccountStatus(String account, String password);

}
