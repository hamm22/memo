package com.ham.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ham.memo.user.service.UserService;

@RestController // @Controller + @ResponseBody
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/join")
	public Map<String, String> Join(
			@RequestParam("loginId")String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			,@RequestParam("email") String email){
		
		int count = userService.addUser(loginId, password, name, email);
	
		Map<String, String> resultMap = new HashMap<>();
		if(count == 1) {
			resultMap.put("result", "successs");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
}
