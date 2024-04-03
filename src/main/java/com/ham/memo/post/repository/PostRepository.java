package com.ham.memo.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ham.memo.post.domain.Post;

@Mapper
public interface PostRepository {
	
	public int insertPost(@Param("userId")int userId
			, @Param("title") String title
			, @Param("contents") String contents
			, @Param("imagePath") String imagePath);
	
	public List<Post> selectPostList(@Param("userId") int userId);
	
	public Post selectPost(@Param("id") int id); // pk를 기반으로 조회
	
	// 업데이는 int
	public int updatePost(@Param("id") int id
				, @Param("title") String title
				, @Param("contents") String contents);
	
	// delete
	public int deletePost(@Param("id") int id);
}
