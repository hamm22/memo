package com.ham.memo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ham.memo.common.EncrytUtils;
import com.ham.memo.user.domain.User;
import com.ham.memo.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public int addUser(String loginId ,String password, String name, String email) {
		
		String encryptPassword = EncrytUtils.md5(password);
		
		return userRepository.insertUser(loginId, encryptPassword, name, email);
	}
	
	// 사용자 정보 리턴
	public User getUserByLoginIdAndPassword(String loginId,String password){
		String encryptPassword = EncrytUtils.md5(password);
		return userRepository.selectUserByLoginIdAndPassword(loginId, encryptPassword);
	}
	
}