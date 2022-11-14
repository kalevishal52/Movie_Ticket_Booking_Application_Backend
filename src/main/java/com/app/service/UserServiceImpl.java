package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.model.dto.UserMobileDTO;
import com.app.model.dto.UserPassDTO;
import com.app.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User registerNewUser(User user) throws UserException {
		
		if(userRepo.findByMobileNumber(user.getMobileNumber()) != null) 
			throw new UserException("User already registred with mobile number: "+user.getMobileNumber()) ;
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_"+user.getRole());
		
		return userRepo.save(user);
	}

	@Override
	public User updateMobileNumber(UserMobileDTO userMobileDTO) throws UserException {
		
		User currentUser = userRepo.findById(userMobileDTO.getUserId()).orElseThrow(() -> new UserException("Invalid userId "+userMobileDTO.getUserId())) ;
		
		if(bCryptPasswordEncoder.matches(userMobileDTO.getCurrentPassword(), currentUser.getPassword())) {
			currentUser.setMobileNumber(userMobileDTO.getNewMobileNumber());			
		}
		else {
			throw new UserException("Your currentPass is not Matching! ") ;
		}
			
		
		return userRepo.save(currentUser);
	}

	@Override
	public User updatePassword(UserPassDTO userPassDTO) throws UserException {
		
		User currentUser = userRepo.findById(userPassDTO.getUserId()).orElseThrow(() -> new UserException("Invalid userId "+userPassDTO.getUserId())) ;
		
		if(bCryptPasswordEncoder.matches(userPassDTO.getCurrentPassword(), currentUser.getPassword())) {
			
			currentUser.setPassword(bCryptPasswordEncoder.encode(userPassDTO.getNewPassword()));
		}
		else {
			throw new UserException("Your currentPass is not Matching! ") ;
		}

		
		return userRepo.save(currentUser); 
	}

}






