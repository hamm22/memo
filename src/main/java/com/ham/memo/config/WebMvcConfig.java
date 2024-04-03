package com.ham.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ham.memo.common.FileManager;
import com.ham.memo.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/");
	}
	
	// 로그인이 안되어있으면 접근 못하게함
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		PermissionInterceptor interceptor = new PermissionInterceptor();
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**") // 모든페이지
		.excludePathPatterns("/user/logout", "/static/**", "/images/**"); // 인터셉터를 거치지 않고 무시하도록 설정
																		  // static이나 image 같은 파일도 제외시켜주는 것이 좋음
	}

}