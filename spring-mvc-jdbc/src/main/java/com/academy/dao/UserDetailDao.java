package com.academy.dao;

import java.util.List;

import com.academy.model.UserDetail;

public interface UserDetailDao {
	
	public UserDetail getUserDetail(int id);
	public List<UserDetail> getAllUserDetail();
	public int addUserDetail(UserDetail userDetail);
	public int updateUserDetail(UserDetail userDetail);
	public int deleteUserDetail(int id);
}