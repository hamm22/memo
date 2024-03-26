package com.ham.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {

	// 상수(final) : 변하지 않는 수
	// 상수는 대문자로 표현해야함 (-> FILE_UPLOAD_PATH)
	public final static String FILE_UPLOAD_PATH = "D:\\springProject\\upload\\memo";
	
	// 파일 객체를 전달받고,
	// 특정 위치에 파일을 저장하고.
	// 클라이언트가 접근할 수 있는 url문자열을 돌려준다.
	public static String saveFile(int userId, MultipartFile file) { // static 멤버변수 사용불가능(객체 생성없이 사용)
		
		if(file == null) {
			return null; // 파일 null일 수 있게함
		}
		
		// 같은 이름의 파일 처리
		// 디렉토리(폴더)를 만들어서 파일 저장
		// 로그인한 사용자 userId를 디렉토리 이름에 추가
		// 현재시간 정보를 디렉토리 이름에 추가
		// UNIX TIME : 1970년 1월 1일 부터 흐른 시간을 milli second (1/1000) 로 표현한 시간
		// ex) 3_523975832

		String directoryName = "/" + userId + "_" + System.currentTimeMillis();

		// 디렉토리 생성
		String directoryPath = FILE_UPLOAD_PATH + directoryName;

		File directory = new File(directoryPath);
		if (!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null; // 정상적으로 수행하지 못함

		}

		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename(); // 실제 파일이름 가져옴

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			// 파일 저장 실패
			return null;
		}
		
		// 저장된 파일 클라이언트에서 접근할 수 있는 url 문자열을 리턴
		// 파일 저장 경로 D:\\springProject\\upload\\memo/3_23195209/test.png
		// url 규칙 : /images/3_23195209/test.png
		return "/images" + directoryName + "/" + file.getOriginalFilename();

	}
}
