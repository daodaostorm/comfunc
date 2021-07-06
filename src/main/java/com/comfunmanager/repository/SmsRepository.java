package com.comfunmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.comfunmanager.beandata.Sms;

public interface SmsRepository extends CrudRepository<Sms, Long> {
	
	Sms findFirstByMobileAndVcodeOrderByExpiredDatetimeDesc(String mobile, String vcode);

}
