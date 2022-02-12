package com.academy.service;

import java.util.List;

import com.academy.model.UserDetail;

/*
 * Interface for service layer
 */
public interface UserDetailService {
	
	public UserDetail getUserDetail(int id);
	public List<UserDetail> getAllUserDetail();
	public int addUserDetail(UserDetail userDetail);
	public int updateUserDetail(UserDetail userDetail);
	public int deleteUserDetail(int id);
	
}


