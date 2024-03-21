package com.ham.memo.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
	
	@GetMapping("/post/list-view")
	public String memolist() {
		return "/post/list";
	}
}
