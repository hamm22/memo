package com.ham.memo.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ham.memo.post.domain.Post;

@Mapper
public interface PostRepository {
	
	public int insertPost(@Param("userId")int userId
			, @Param("title") String title
			, @Param("contents") String contents);
	
	public List<Post> selectPostList(@Param("userId") int userId);
	
	public Post selectPost(@Param("id") int id);
}