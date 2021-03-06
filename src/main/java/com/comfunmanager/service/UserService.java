package com.comfunmanager.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.comfunmanager.beandata.ResetPasswordForm;
import com.comfunmanager.beandata.User;
import com.comfunmanager.beandata.UserCreateForm;

/**
 * 用户服务网接口定义
 * 
 * @author angkor
 *
 */
public interface UserService {

	User create(UserCreateForm form);

	User getUserByUsername(String username);

	Object uploadImage(MultipartFile file, HttpServletRequest request);

	User getCurrentUser();

	String getCurrentUsername();

	Object listAllUsers(Pageable p);

	Object getUserList(int current, int rowCount, String searchPhrase);

	User resetPassword(ResetPasswordForm form);

}
