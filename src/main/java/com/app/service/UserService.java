package com.app.service;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.model.dto.UserMobileDTO;
import com.app.model.dto.UserPassDTO;

public interface UserService {

	public User registerNewUser(User user) throws UserException ;
	
	public User updateMobileNumber(UserMobileDTO userMobileDTO) throws UserException;
	
	public User updatePassword(UserPassDTO userPassDTO) throws UserException;
}
