package com.pengcheng.dao;

import com.pengcheng.model.User;

public class UserDaoImpl implements UserDAO {

	@Override
	public void add(User user) {
		System.out.println(" user saved! ");
	}

	@Override
	public void delete(User user) {
		System.out.println(" user delete! ");
		
	}

}
