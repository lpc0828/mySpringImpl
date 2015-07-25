package com.pengcheng.service;

import com.pengcheng.dao.UserDAO;
import com.pengcheng.model.User;
import com.pengcheng.utils.XmlUtil;

public class UserService {
	
	private UserDAO userDao;
	
	public void addUser(User user) {
		userDao.add(user);
	}
	
	public static void main(String[] args) {
		XmlUtil.parseXml("test.xml");
	}
}
