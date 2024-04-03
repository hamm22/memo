package com.ham.memo.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ham.memo.common.FileManager;
import com.ham.memo.post.domain.Post;
import com.ham.memo.post.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public int addPost(int userId, String title, String contents, MultipartFile file) {
		
		// 파일을 특정 위치에 저장한다.
		String imagePath = FileManager.saveFile(userId, file);
		
		return postRepository.insertPost(userId, title, contents, imagePath);
	}
	
	public List<Post> getPostList(int userId) {
		
		return postRepository.selectPostList(userId);
	}
	
	public Post getPost(int id) {
		return postRepository.selectPost(id);
	}
	
	// update
	public int updatePost(int id, String title, String contents) {
		return postRepository.updatePost(id, title, contents);
	}
	
	// delete
	public int deletePost(int id) {
		
		// 삭제 대상의 이미지 경로를 얻어 오기 위해
		// id를 기반으로 메모 정보를 얻어온다.
		Post post = postRepository.selectPost(id);
		
		// /image/
		FileManager.removeFile(post.getImagePath());
		
		return postRepository.deletePost(id);
	}
}
