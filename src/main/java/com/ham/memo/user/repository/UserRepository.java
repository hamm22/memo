package com.ham.memo.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ham.memo.user.domain.User;

@Mapper
public interface UserRepository {
	
	public int insertUser(@Param("loginId")String loginId
			,@Param("password")String password
			,@Param("name")String name
			,@Param("email")String email);
	
	// 한행을 위한 값이여서 entity만들음
	public User selectUserByLoginIdAndPassword(@Param("loginId")String loginId
												,@Param("password")String password);
}