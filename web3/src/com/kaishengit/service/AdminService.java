package com.kaishengit.service;



import org.apache.commons.codec.digest.DigestUtils;

import com.kaishengit.config.Config;
import com.kaishengit.dao.AdminDao;
import com.kaishengit.entity.Admin;
import com.kaishengit.exception.ServiceException;

public class AdminService {

	private AdminDao adminDao = new AdminDao();
	
	public Admin login(String name,String password) throws ServiceException {
		
		Admin admin = adminDao.findByName(name);
		//password = DigestUtils.md5Hex(password + Config.PASSWORD_SALT);
		
		if(admin != null && admin.getPassword().equals(password)) {
			return admin;
		} else {
			throw new ServiceException("√‹¬ÎªÚ’À∫≈¥ÌŒÛ");
		}
		
	}
}
