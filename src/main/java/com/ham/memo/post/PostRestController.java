package com.ham.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ham.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/create")
	public Map<String, String> createMemo(
						@RequestParam("title") String title
						, @RequestParam("contents") String contents
						, HttpSession session) {
		
		// 세션에 저장된 userId 값을 얻어온다.
		// 로그인한 사용자의 primary key를 얻기 위해
		// 로그인에서 session의 값을 저장했기 때문에 얻어올 수 있음
		// Object라서 Integer로 해줘야함
		// Upcasting, downcasting
		int userId = (Integer)session.getAttribute("userId"); // userId 정수
		
int count = postService.addPost(userId, title, contents);
		
		Map<String, String> resultMap = new HashMap<>();
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}

}