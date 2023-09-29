package com.mayuratech.api.service;

import java.util.List;

import com.mayuratech.api.entity.UserDetails;

public interface UserService {
	public UserDetails updateUser(UserDetails user,long id);
	public UserDetails getUserById(long id);
	public List<UserDetails> getAllUsers();
	//public Object getUsersByFormat(String format);
}
